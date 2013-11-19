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
			contentOfField(x, y, status);

		}

		public void contentOfField(int x, int y, MineNumberWinLose status) {
			switch (status) {
			case ZERO:
				fieldZero(x, y);
				buttons[x][y].setText("0");
				break;
			case ONE:
				buttons[x][y].setText("1");
				break;
			case TWO:
				buttons[x][y].setText("2");
				break;
			case THREE:
				buttons[x][y].setText("3");
				break;
			case FOUR:
				buttons[x][y].setText("4");
				break;
			case FIVE:
				buttons[x][y].setText("5");
				break;
			case SIX:
				buttons[x][y].setText("6");
				break;
			case SEVEN:
				buttons[x][y].setText("7");
				break;
			case EIGHT:
				buttons[x][y].setText("8");
				break;
			case MINE:
				buttons[x][y].setText("M");
				loseEvent();
				break;
			case WIN:
				buttons[x][y].setText("W");
				break;
			case FLAG:
				buttons[x][y].setText("F");
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

			if (up >= 0 && left >= 0 && down < sizeX && right < sizeY) {

				MineNumberWinLose field_up = bridge.checkMine(x - 1, y);
				MineNumberWinLose field_right = bridge.checkMine(x, y + 1);
				MineNumberWinLose field_down = bridge.checkMine(x + 1, y);
				MineNumberWinLose field_left = bridge.checkMine(x, y - 1);
				MineNumberWinLose field_up_right = bridge.checkMine(x - 1, y + 1);
				MineNumberWinLose field_up_left = bridge.checkMine(x - 1, y - 1);
				MineNumberWinLose field_down_left = bridge.checkMine(x + 1, y - 1);
				MineNumberWinLose field_down_right = bridge.checkMine(x + 1, y + 1);

				if (up >= 0) {
					contentOfField(up, y, field_up);
				}

				if (down < sizeX) {
					contentOfField(down, y, field_down);
				}
				if (right < sizeY) {
					contentOfField(x, right, field_right);
				}

				if (left >= 0) {
					contentOfField(x, left, field_left);
				}

				if (right < sizeY && down < sizeX) {
					contentOfField(down, right, field_down_right);
				}

				if (left >= 0 && up >= 0) {
					contentOfField(up, left, field_up_left);
				}

				if (left >= 0 && down < sizeX) {
					contentOfField(down, left, field_down_left);
				}

				if (up >= 0 && right < sizeY) {
					contentOfField(up, right, field_up_right);
				}

			}
		}

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

		public void mouseEntered(MouseEvent arg0) {
		}

		public void mouseExited(MouseEvent arg0) {
		}

		public void mousePressed(MouseEvent arg0) {
		}

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
				buttons[posX][posY].addMouseListener(new ButtonListener(posX,
						posY));
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
