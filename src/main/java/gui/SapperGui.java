package gui;

import static gui.Icons.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import sapper.Board;
import sapper.BoardAndCounter;
import sapper.MineNumberWinLose;
import controller.Bridge;
import controller.Load;
import controller.Save;

public class SapperGui extends JFrame {
	private static transient Logger logger = Logger.getLogger(SapperGui.class.getName());
	private static final long serialVersionUID = 1L;
	private int sizeX = 10;
	private int sizeY = 10;
	private int mines = 10;

	private JPanel panel, gamePanel;
	private JMenuBar menuBar;
	private JButton[][] buttons;
	private MineNumberWinLose status;
	private static Bridge bridge;
	private JLabel minesCounter;
	private static JLabel timeCounter;
	private int flags;
	private static CounterGUI counter;

	public static Bridge getBridge() {
		return bridge;
	}

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

			fieldImageLabel.setBorder(BorderFactory.createDashedBorder(Color.black));
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

		private void fieldFlagged() {
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
			logger.debug("Field Clicked");
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
				MineNumberWinLose field_down_right = bridge.checkMine(x + 1, y + 1);
				contentOfField(down, right, field_down_right);
			}

			if (left >= 0 && up >= 0) {
				MineNumberWinLose field_up_left = bridge.checkMine(x - 1, y - 1);
				contentOfField(up, left, field_up_left);
			}

			if (left >= 0 && down < sizeX) {
				MineNumberWinLose field_down_left = bridge.checkMine(x + 1, y - 1);
				contentOfField(down, left, field_down_left);
			}

			if (up >= 0 && right < sizeY) {
				MineNumberWinLose field_up_right = bridge.checkMine(x - 1, y + 1);
				contentOfField(up, right, field_up_right);
			}
		}

		public void mouseClicked(MouseEvent arg0) {
			JButton button = (JButton) arg0.getSource();
			if (button.isEnabled() == true) {
				if (arg0.getButton() == MouseEvent.BUTTON3) {
					fieldFlagged();
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

	public SapperGui(int xSize, int ySize, int mines) {
		this.sizeX = xSize;
		this.sizeY = ySize;
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

	public SapperGui(Bridge loadedBridge) {
		bridge = loadedBridge;
		initUI();
		counterStart();
	}

	private void startGame() {
		SapperGui sapper = new SapperGui(sizeX, sizeY, mines);
		sapper.setLocation(this.getLocationOnScreen());
		sapper.setVisible(true);
		this.setVisible(false);
	}

	private void startGame(SapperGui loadedSapperGui) {
		SapperGui sapper = loadedSapperGui;
		sapper.setLocation(this.getLocationOnScreen());
		sapper.setVisible(true);
		this.setVisible(false);
	}

	private void showOptions() {
		GuiOptions options = new GuiOptions(sizeX, sizeY, mines);
		options.setLocation(this.getLocationOnScreen());
		options.setVisible(true);
		this.setVisible(false);
	}

	private void closeWindow() {
		this.setVisible(false);
	}

	private void initUI() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel);

		JPanel top = new JPanel();
		panel.add(top, BorderLayout.NORTH);
		top.setLayout(new GridLayout(1, 0));

		menuBar = new JMenuBar();
		top.add(menuBar);

		JMenu gameMenu = new JMenu("Gra");
		menuBar.add(gameMenu);

		JMenuItem newGameItem = new JMenuItem("Nowa gra");
		newGameItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});
		gameMenu.add(newGameItem);

		JMenuItem loadGameItem = new JMenuItem("Wczytaj grę");
		gameMenu.add(loadGameItem);
		loadGameItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SapperGui sapperGui = loadGame();
				startGame(sapperGui);
			}
		});

		JMenuItem optionsItem = new JMenuItem("Opcje");
		optionsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showOptions();
			}
		});
		gameMenu.add(optionsItem);

		JMenuItem saveGameItem = new JMenuItem("Zapisz");
		gameMenu.add(saveGameItem);
		saveGameItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveGame();
			}
		});

		gameMenu.addSeparator();

		JMenuItem exitItem = new JMenuItem("Wyjdź");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		gameMenu.add(exitItem);

		JMenu helpMenu = new JMenu("Pomoc");
		menuBar.add(helpMenu);

		JMenuItem authorsItem = new JMenuItem("O autorach");
		final String authors = "Koło programistów WSZiB\nwww.wszib.edu.pl";
		authorsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, authors, "O autorach",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		helpMenu.add(authorsItem);

		gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(sizeX, sizeY, 1, 1));
		panel.add(gamePanel);

		buttons = new JButton[sizeX][sizeY];

		for (int posX = 0; posX < sizeX; posX++) {
			for (int posY = 0; posY < sizeY; posY++) {
				buttons[posX][posY] = new JButton();
				buttons[posX][posY].addMouseListener(new ButtonListener(posX, posY));
				buttons[posX][posY].setPreferredSize(new Dimension(40, 40));
				gamePanel.add(buttons[posX][posY]);
			}
		}

		JPanel bottom = new JPanel();
		panel.add(bottom, BorderLayout.SOUTH);

		JPanel countersInBottomPanel = new JPanel();
		countersInBottomPanel.setLayout(new GridLayout(0, 2));
		bottom.add(countersInBottomPanel);

		minesCounter = new JLabel("Pozostało min: " + String.valueOf(mines));
		countersInBottomPanel.add(minesCounter);

		timeCounter = new JLabel("Czas gry: 0 sekund");
		countersInBottomPanel.add(timeCounter);

		setWindow();
	}

	public void setWindow() {
		setTitle("Sapper");
		setMinimumSize(new Dimension(240, 240));
		pack();
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

	public static void saveGame() {
		Save save = new Save(bridge.getBoard(), counter.getBridge().getCounter());
		save.saveToFile();
	}

	public static SapperGui loadGame() {
		BoardAndCounter boardAndCounter = Load.loadFromFile();
		Bridge bridge = new Bridge(boardAndCounter);
		SapperGui sapperGui = new SapperGui(bridge);
		return sapperGui;
	}

}
