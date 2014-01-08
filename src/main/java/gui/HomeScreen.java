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

import sapper.GameOptions;

public class HomeScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton btnExit;
	private JButton btnLoadGame;
	private JButton btnNewGame;
	private JButton btnOptions;
	private JLabel labelTitle;

	public int sizeX = 5, sizeY = 5, numberOfMines = 5;
	
	GameOptions gameOptions;
	
	public HomeScreen(int sizeX, int sizeY, int numberOfMines) {
		
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.numberOfMines = numberOfMines;
		//gameOptions = new GameOptions(sizeX, sizeY, numberOfMines);
		setSize(new Dimension(150, 300));
		initComponents();
		
	}
	
	public HomeScreen(){
		initComponents();
	}
	

	public void initComponents() {

		labelTitle = new JLabel();
		btnNewGame = new JButton();
		btnLoadGame = new JButton();
		btnOptions = new JButton();
		btnExit = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		labelTitle.setFont(new java.awt.Font("Tahoma", 0, 24));
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setText("Sapper");

		btnNewGame.setText("New game");
		btnLoadGame.setText("Load game");
		btnOptions.setText("Options");
		btnExit.setText("Exit");

		JPanel homeScreenPanel = new JPanel();
		homeScreenPanel.setLayout(new GridLayout(5, 3, 30, 10));
		homeScreenPanel.add(labelTitle);
		homeScreenPanel.add(btnNewGame);
		homeScreenPanel.add(btnLoadGame);
		homeScreenPanel.add(btnOptions);
		homeScreenPanel.add(btnExit);
		getContentPane().add(homeScreenPanel);
		setSize(200, 300);
		setTitle("Sapper");

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});

		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showOptions();
			}
		});

	}

	private void startGame() {
		SapperGui saper = new SapperGui();
		saper.setLocation(this.getLocationOnScreen());
		saper.setVisible(true);
		this.setVisible(false);
	}

	private void showOptions() {
		GuiOptions options = new GuiOptions(sizeX, sizeY, numberOfMines);
		options.setLocation(this.getLocationOnScreen());
		options.setVisible(true);
		this.setVisible(false);
	}

	private void closeWindow() {
		this.setVisible(false);
	}
}
