package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class HomeScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton btnExit;
	private JButton btnLoadGame;
	private JButton btnNewGame;
	private JButton btnOptions;
	private JLabel labelTitle;

	private int sizeX = 5;
	private int sizeY = 5;
	private int mines = 5;

	public HomeScreen() {
		initComponents();
	}

	public HomeScreen(int xSize, int ySize, int mines) {
		this.sizeX = xSize;
		this.sizeY = ySize;
		this.mines = mines;
		setSize(new Dimension(150, 300));
		initComponents();
	}

	private void initComponents() {

		labelTitle = new JLabel();
		btnNewGame = new JButton();
		btnLoadGame = new JButton();
		btnOptions = new JButton();
		btnExit = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		labelTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setText("Sapper");

		btnNewGame.setText("New game");
		btnLoadGame.setText("Load game");
		btnOptions.setText("Options");
		btnExit.setText("Exit");

		JPanel homeScreenPanel = new JPanel();
		homeScreenPanel.setLayout(new GridLayout(5, 1, 1, 1));
		homeScreenPanel.add(labelTitle);
		homeScreenPanel.add(btnNewGame);
		homeScreenPanel.add(btnLoadGame);
		homeScreenPanel.add(btnOptions);
		homeScreenPanel.add(btnExit);
		getContentPane().add(homeScreenPanel);
		pack();

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("btn load");
			}
		});
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Thread gui = new Thread(new GuiThreed(), "Gui Thread");
				// gui.start();
				startGame();
			}
		});

		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("btn options");
				showOptions();
			}
		});

	}

	private void startGame() {
		SapperGui saper = new SapperGui(sizeX, sizeY, mines);
		saper.setLocation(this.getLocationOnScreen());
		saper.setVisible(true);
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
}
