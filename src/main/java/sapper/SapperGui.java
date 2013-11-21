package sapper;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import sapper.Bridge.MineNumberWinLose;

public class SapperGui extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton[][] buttons;
	private MineNumberWinLose status;
	private int sizeX, sizeY, mines;
	private static Bridge bridge;
	private JLabel minesCounter;
	private static JLabel timeCounter;
	private int flags;

	class ButtonListener implements MouseListener {
		int x, y;

		public ButtonListener(int posX, int posY) {
			x = posX;
			y = posY;
		}

		public void loseEvent() {
			for (int x = 0; x < sizeX; x++) {
				for (int y = 0; y < sizeY; y++) {
					if (bridge.checkMine(x, y) == MineNumberWinLose.FLAG) {
						bridge.changeFieldFlagStatus(x, y);
						if (bridge.checkMine(x, y) == MineNumberWinLose.MINE) {
							buttons[x][y].setText("F");
						} else {
							buttons[x][y].setText("X");
						}
					} else if (bridge.checkMine(x, y) == MineNumberWinLose.MINE) {
						buttons[x][y].setText("M");
					}
					buttons[x][y].setEnabled(false);
				}
			}
		}

		private void fieldFlaged(JButton button) {
			boolean flagSetted = bridge.changeFieldFlagStatus(x, y);

			if (flagSetted == true) {
				button.setText("F");
				flags++;
				calculateMines(mines, flags);
			} else {
				button.setText("");
				flags--;
				calculateMines(mines, flags);
			}
		}

		private void fieldClick(JButton button) {
			status = bridge.checkMine(x, y);
			switch (status) {
			case ZERO:
				button.setText("0");
				break;
			case ONE:
				button.setText("1");
				break;
			case TWO:
				button.setText("2");
				break;
			case THREE:
				button.setText("3");
				break;
			case FOUR:
				button.setText("4");
				break;
			case FIVE:
				button.setText("5");
				break;
			case SIX:
				button.setText("6");
				break;
			case SEVEN:
				button.setText("7");
				break;
			case EIGHT:
				button.setText("8");
				break;
			case MINE:
				button.setText("M");
				loseEvent();
				break;
			case WIN:
				button.setText("W");
				break;
			case FLAG:
				button.setText("F");
				return;
			default:
				break;
			}
			button.setEnabled(false);
		}

		public void mouseClicked(MouseEvent arg0) {
			JButton button = (JButton) arg0.getSource();
			if (button.isEnabled() == true) {
				if (arg0.getButton() == MouseEvent.BUTTON3) {
					fieldFlaged(button);
				} else {
					fieldClick(button);
				}
			}
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	private void calculateMines(int mines, int flags) {
		int numberOfMines = 0;
		numberOfMines = mines - flags;

		minesCounter.setText("Pozostało min: " + String.valueOf(numberOfMines));
	}

	private static String updateTimeCounter() {
		long timeStart = bridge.giveTimeStart();
		long timeNow = (System.currentTimeMillis()) / 1000;
		long timeOfGame = timeNow - timeStart;
		
		return String.valueOf(timeOfGame);
	}

	public SapperGui() {
		sizeX = 15;
		sizeY = 10;
		mines = 25;
		bridge = new Bridge(sizeX, sizeY, mines);
		initUI();
	}

	private void initUI() {
		// Panel creating
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel);

		// Top panel creating
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());
		panel.add(top, BorderLayout.CENTER);

		// Grid layout for the board creating
		JPanel boardInTopPanel = new JPanel();
		boardInTopPanel.setLayout(new GridLayout(sizeX, sizeY, 1, 1));
		top.add(boardInTopPanel);

		// Buttons creating
		buttons = new JButton[sizeX][sizeY];
		for (int posX = 0; posX < sizeX; posX++) {
			for (int posY = 0; posY < sizeY; posY++) {
				buttons[posX][posY] = new JButton("");
				buttons[posX][posY].addMouseListener(new ButtonListener(posX,
						posY));
				boardInTopPanel.add(buttons[posX][posY]);
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
		timeCounter = new JLabel("Czas gry: " + updateTimeCounter() + " sekund");
		countersInBottomPanel.add(timeCounter);

		// Window settings
		setTitle("Sapper");
		setSize((sizeY) * 43 + 6, (sizeX) * 43 + 7);
		setResizable(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SapperGui saper = new SapperGui();
				saper.setVisible(true);
			}
		});
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			timeCounter.setText("Czas gry: " + updateTimeCounter() + " sekund");
		}
	}
}