package sapper;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import sapper.Bridge.MineNumberWinLose;

public class SapperGui extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JButton[][] buttons;
	private MineNumberWinLose status;
	private int sizeX, sizeY, mines;
	private Bridge bridge;

	class ButtonListener implements ActionListener {
		int x, y;

		public ButtonListener(int posX, int posY) {
			x = posX;
			y = posY;
		}
		
		public void loseEvent(){
			for(int x=0; x<sizeX;x++){
				for(int y=0;y<sizeY;y++){
					if(bridge.checkMine(x, y) == MineNumberWinLose.FLAG){
						bridge.changeFieldFlagStatus(x, y);
						if(bridge.checkMine(x, y) == MineNumberWinLose.LOSE){
							buttons[x][y].setText("F");
						}
						else {
							buttons[x][y].setText("X");
						}
					}
					if(bridge.checkMine(x, y) == MineNumberWinLose.LOSE){
						buttons[x][y].setText("M");
					}
					buttons[x][y].setEnabled(false);
				}
			}
		}

		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
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
			case LOSE:
				button.setText("M");
				loseEvent();
				break;
			case WIN:
				button.setText("W");
				break;
			default:
				break;
			}
			button.setEnabled(false);
		}
	}

	public SapperGui() {
		sizeX = 15;
		sizeY = 10;
		mines = 10;
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
				buttons[posX][posY].addActionListener(new ButtonListener(posX, posY));
				panel.add(buttons[posX][posY]);
			}
		}
		// Window settings
		setTitle("Sapper");
		setSize((sizeY) * 43 + 6, (sizeX) * 43 + 7);
		setResizable(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SapperGui saper = new SapperGui();
				saper.setVisible(true);
			}
		});
	}
}
