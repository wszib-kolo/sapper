package sapper;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sapper.Bridge.MineNumberWinLose;

public class SapperGui extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JButton[][] buttons;
	private MineNumberWinLose status;
	private int sizeX, sizeY, mines;
	private Bridge bridge;
	private Icon zeroBomb,oneBomb,twoBombs,threeBombs,fourBombs,fiveBombs,sixBombs,sevenBombs,eightBombs, bomb,flag,flagedBomb,win, explode,clean,badflaged;
	
	private class ButtonListener implements MouseListener {
		
		int x, y;

		public ButtonListener(int posX, int posY) {
			x = posX;
			y = posY;
		}
		
		private void setFieldLabelImage(Icon fieldIcon, int posX, int posY) {
				panel.remove((sizeY) * posX + posY);
				
				JLabel fieldImageLabel = new JLabel(fieldIcon);
				panel.add(fieldImageLabel, (sizeY) * posX + posY);
				
				fieldImageLabel.setBorder(BorderFactory.createDashedBorder(Color.black));
				panel.updateUI();
		}

		private void setFieldButtonImage(Icon fieldIcon, int posX, int posY) {
			buttons[posX][posY].setIcon(fieldIcon);
		}
		
		private void winEvent(){
			setFieldLabelImage(win, x, y);	
		}
		
		private void loseEvent() {
			for (int xFieldPos = 0; xFieldPos < sizeX; xFieldPos++) {
				for (int yFieldPos = 0; yFieldPos < sizeY; yFieldPos++) {
					if (bridge.checkMine(xFieldPos, yFieldPos) == MineNumberWinLose.FLAG) {
						bridge.changeFieldFlagStatus(xFieldPos, yFieldPos);
						if (bridge.checkMine(xFieldPos, yFieldPos) == MineNumberWinLose.MINE) {
							setFieldLabelImage(flagedBomb, xFieldPos, yFieldPos);
						} else {
							setFieldLabelImage(badflaged, xFieldPos, yFieldPos);
						}
					} else if (bridge.checkMine(xFieldPos, yFieldPos) == MineNumberWinLose.MINE) {
						setFieldLabelImage(bomb, xFieldPos, yFieldPos);
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
			} else {
				setFieldButtonImage(clean, x, y);
			}
		}

		private void FieldClick(JButton button) {
			status = bridge.checkMine(x, y);
			switch (status) {
			case ZERO:
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
					FieldFlaged();
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
		sizeX = 10;
		sizeY = 10;
		mines = 15;
		bridge = new Bridge(sizeX, sizeY, mines);
		initUI();
	}
	
	private void loadIcons(){
		String prefix = "icons/";
		try {
			zeroBomb = new ImageIcon(ImageIO.read(new File(prefix+"0.png")));
			oneBomb = new ImageIcon(ImageIO.read(new File(prefix+"1.png")));
			twoBombs = new ImageIcon(ImageIO.read(new File(prefix+"2.png")));
			threeBombs = new ImageIcon(ImageIO.read(new File(prefix+"3.png")));
			fourBombs = new ImageIcon(ImageIO.read(new File(prefix+"4.png")));
			fiveBombs = new ImageIcon(ImageIO.read(new File(prefix+"5.png")));
			sixBombs = new ImageIcon(ImageIO.read(new File(prefix+"6.png")));
			sevenBombs = new ImageIcon(ImageIO.read(new File(prefix+"7.png")));
			eightBombs = new ImageIcon(ImageIO.read(new File(prefix+"8.png")));
			flag = new ImageIcon(ImageIO.read(new File(prefix+"flag.png")));
			explode = new ImageIcon(ImageIO.read(new File(prefix+"expl.png")));
			bomb = new ImageIcon(ImageIO.read(new File(prefix+"bomb.png")));
			flagedBomb = new ImageIcon(ImageIO.read(new File(prefix+"bombflag.png")));
			win = new ImageIcon(ImageIO.read(new File(prefix+"win.png")));
			clean = new ImageIcon(ImageIO.read(new File(prefix+"clean.png")));
			badflaged = new ImageIcon(ImageIO.read(new File(prefix+"badflag.png")));
		} catch (IOException ex) {
		}
	}
	
	private void initUI() {
		loadIcons();
		// Panel creating
		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(sizeX, sizeY, 1, 1));
		
		// Buttons creating
		buttons = new JButton[sizeX][sizeY];
		for (int posX = 0; posX < sizeX; posX++) {
			for (int posY = 0; posY < sizeY; posY++) {
				buttons[posX][posY] = new JButton();
				buttons[posX][posY].addMouseListener(new ButtonListener(posX, posY));
				buttons[posX][posY].setMargin(new Insets(0, 0, 0, 0));
				panel.add(buttons[posX][posY]);
			}
		}
		
		// Window settings
		setTitle("Sapper");
		setSize((sizeY) * 32 + 6, (sizeX) * 32 + 7);
		setResizable(true);
	}

}
