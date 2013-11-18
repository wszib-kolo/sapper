package sapper;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import sapper.Bridge.MineNumberWinLose;

public class SapperGui extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton[][] buttons;
	private MineNumberWinLose status;
	private int sizeX, sizeY, mines;
	private Bridge bridge;

	private class ButtonListener implements MouseListener {
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

		private void FieldFlaged(JButton button) {
			boolean flagSetted = bridge.changeFieldFlagStatus(x, y);
			if (flagSetted == true) {
				button.setText("F");
			} else {
				button.setText("");
			}
		}

		private void FieldClick(JButton button) {
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

		@Override
		public void mouseClicked(MouseEvent arg0) {
			JButton button = (JButton) arg0.getSource();
			if (button.isEnabled() == true) {
				if (arg0.getButton() == MouseEvent.BUTTON3) {
					FieldFlaged(button);
				} else {
					FieldClick(button);
				}
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
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
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(sizeX, sizeY, 1, 1));
		// Buttons creating
		buttons = new JButton[sizeX][sizeY];
		for (int posX = 0; posX < sizeX; posX++) {
			for (int posY = 0; posY < sizeY; posY++) {
				buttons[posX][posY] = new JButton("");
				buttons[posX][posY].addMouseListener(new ButtonListener(posX, posY));
				buttons[posX][posY].setFont(new Font("Serif", Font.PLAIN, 10));
				buttons[posX][posY].setMargin(new Insets(0, 0, 0, 0));
				panel.add(buttons[posX][posY]);
			}
		}
		// Window settings
		setTitle("Sapper");
		setSize((sizeY) * 25 + 6, (sizeX) * 25 + 7);
		setResizable(true);
	}

}
