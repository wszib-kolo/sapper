package gui;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.Bridge;
import sapper.MineNumberWinLose;
import static gui.Icons.*;

public class SapperGui extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panel, gamePanel;
	private JButton[][] buttons;
	private MineNumberWinLose status;
	private int sizeX, sizeY, mines;
	private static Bridge bridge;
	private JLabel minesCounter;
	private static JLabel timeCounter;
	private int flags;
	private CounterGUI counter;

	private class ButtonListener implements MouseListener {
		int x, y;

		public ButtonListener(int posX, int posY) {
			x = posX;
			y = posY;
		}

		private void setFieldLabelImage(Icon fieldIcon, int posX, int posY) {
			gamePanel.remove((sizeY) * posX + posY);

			JLabel fieldImageLabel = new JLabel(fieldIcon);
			gamePanel.add(fieldImageLabel, (sizeY) * posX + posY);

			fieldImageLabel.setBorder(BorderFactory
					.createDashedBorder(Color.black));
			gamePanel.updateUI();
		}

		private void setFieldButtonImage(Icon fieldIcon, int posX, int posY) {
			buttons[posX][posY].setIcon(fieldIcon);
		}

		private void winEvent() {
			setFieldLabelImage(win, x, y);
			counter.stop();
		}

		private void loseEvent() {
			counter.stop();
			for (int xFieldPos = 0; xFieldPos < sizeX; xFieldPos++) {
				for (int yFieldPos = 0; yFieldPos < sizeY; yFieldPos++) {
					switch (bridge.checkMine(xFieldPos, yFieldPos)) {
					case FLAGGEDMINE:
						setFieldLabelImage(flaggedBomb, xFieldPos, yFieldPos);
						break;
					case MINE:
						setFieldLabelImage(bomb, xFieldPos, yFieldPos);
						break;
					case FLAG:
						setFieldLabelImage(badFlagged, xFieldPos, yFieldPos);
						break;
					}
					buttons[xFieldPos][yFieldPos].setEnabled(false);
				}
			}
			setFieldLabelImage(explode, x, y);
		}

		private void FieldFlaged() {
			boolean flagSetted = bridge.changeFieldFlagStatus(x, y);
			if (flagSetted == true) {
				setFieldButtonImage(flag, x, y);
				flags++;
				calculateMines(mines, flags);
			} else {
				setFieldButtonImage(clean, x, y);
				flags--;
				calculateMines(mines, flags);
			}
		}

		private void fieldClick(JButton button) {
			status = bridge.checkMine(x, y);
			contentOfField(x, y, status);
		}

		public void contentOfField(int x, int y, MineNumberWinLose status) {
			switch (status) {
			case ZERO:
				fieldZero(x, y);
				setFieldLabelImage(zeroBomb, x, y);
				break;
			case ONE:
				setFieldLabelImage(oneBomb, x, y);
				break;
			case TWO:
				setFieldLabelImage(twoBombs, x, y);
				break;
			case THREE:
				setFieldLabelImage(threeBombs, x, y);
				break;
			case FOUR:
				setFieldLabelImage(fourBombs, x, y);
				break;
			case FIVE:
				setFieldLabelImage(fiveBombs, x, y);
				break;
			case SIX:
				setFieldLabelImage(sixBombs, x, y);
				break;
			case SEVEN:
				setFieldLabelImage(sevenBombs, x, y);
				break;
			case EIGHT:
				setFieldLabelImage(eightBombs, x, y);
				break;
			case MINE:
				loseEvent();
				break;
			case WIN:
				winEvent();
				break;
			case FLAG:
				setFieldButtonImage(flag, x, y);
				return;
			case FLAGGEDMINE:
				setFieldButtonImage(flag, x, y);
				return;
			default:
				break;
			}
			buttons[x][y].setEnabled(false);
		}

		public void fieldZero(int x, int y) {

			int up = x - 1;
			int left = y - 1;
			int right = y + 1;
			int down = x + 1;

			if (up >= 0) {
				MineNumberWinLose field_up = bridge.checkMine(x - 1, y);
				contentOfField(up, y, field_up);
			}

			if (down < sizeX) {
				MineNumberWinLose field_down = bridge.checkMine(x + 1, y);
				contentOfField(down, y, field_down);
			}
			if (right < sizeY) {
				MineNumberWinLose field_right = bridge.checkMine(x, y + 1);
				contentOfField(x, right, field_right);
			}

			if (left >= 0) {
				MineNumberWinLose field_left = bridge.checkMine(x, y - 1);
				contentOfField(x, left, field_left);
			}

			if (right < sizeY && down < sizeX) {
				MineNumberWinLose field_down_right = bridge.checkMine(x + 1,
						y + 1);
				contentOfField(down, right, field_down_right);
			}

			if (left >= 0 && up >= 0) {
				MineNumberWinLose field_up_left = bridge
						.checkMine(x - 1, y - 1);
				contentOfField(up, left, field_up_left);
			}

			if (left >= 0 && down < sizeX) {
				MineNumberWinLose field_down_left = bridge.checkMine(x + 1,
						y - 1);
				contentOfField(down, left, field_down_left);
			}

			if (up >= 0 && right < sizeY) {
				MineNumberWinLose field_up_right = bridge.checkMine(x - 1,
						y + 1);
				contentOfField(up, right, field_up_right);
			}
		}

		public void mouseClicked(MouseEvent arg0) {
			JButton button = (JButton) arg0.getSource();
			if (button.isEnabled() == true) {
				if (arg0.getButton() == MouseEvent.BUTTON3) {
					FieldFlaged();
				} else {
					fieldClick(button);
				}
			}
		}

		public void mouseEntered(MouseEvent arg0) {
		}

		public void mouseExited(MouseEvent arg0) {
		}

		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
		}
	}

	public SapperGui(int sizeX, int sizeY, int mines) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.mines = mines;
		bridge = new Bridge(sizeX, sizeY, this.mines);
		initUI();
		counterStart();
	}

	public SapperGui() {
		bridge = new Bridge(sizeX, sizeY, mines);
		initUI();
		counterStart();
	}

	private void initUI() {
		// Panel creating
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel);

		// Grid layout for the board creating
		gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(sizeX, sizeY, 1, 1));
		panel.add(gamePanel);

		// Buttons creating
		buttons = new JButton[sizeX][sizeY];
		for (int posX = 0; posX < sizeX; posX++) {
			for (int posY = 0; posY < sizeY; posY++) {
				buttons[posX][posY] = new JButton();
				buttons[posX][posY].addMouseListener(new ButtonListener(posX,
						posY));
				gamePanel.add(buttons[posX][posY]);
			}
		}

		// Bottom panel creating
		JPanel bottom = new JPanel();
		panel.add(bottom, BorderLayout.SOUTH);

		// Grid layout for counters creating
		JPanel countersInBottomPanel = new JPanel();
		countersInBottomPanel.setLayout(new GridLayout(0, 2));
		bottom.add(countersInBottomPanel);

		// Mines counter creating
		minesCounter = new JLabel("Pozostało min: " + String.valueOf(mines));
		countersInBottomPanel.add(minesCounter);

		// Time counter creating
		timeCounter = new JLabel("Czas gry: 0 sekund");
		countersInBottomPanel.add(timeCounter);

		// Window settings
		setTitle("Sapper");
		setSize((sizeY) * 32 + 6, (sizeX) * 32 + 7);
		setResizable(true);
	}

	private void calculateMines(int mines, int flags) {
		int numberOfMines = 0;
		numberOfMines = mines - flags;

		minesCounter.setText("Pozostało min: " + String.valueOf(numberOfMines));
	}

	private void counterStart() {
		counter = new CounterGUI(timeCounter, bridge);
		counter.start();
	}
}