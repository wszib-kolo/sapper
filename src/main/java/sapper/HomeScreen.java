package sapper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class HomeScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton btnExit;
	private JButton btnLoadGame;
	private JButton btnNewGame;
	private JButton btnOptions;
	private JLabel labelTitle;

	private int sizeX = 10;
	private int sizeY = 15;
	private int mines = 20;

	public HomeScreen() {
		initComponents();
	}

	public HomeScreen(int xSize, int ySize, int mines) {
		this.sizeX = xSize;
		this.sizeY = ySize;
		this.mines = mines;
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

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup(
										GroupLayout.Alignment.LEADING)
										.addComponent(labelTitle,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(btnNewGame,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(btnLoadGame,
												GroupLayout.DEFAULT_SIZE, 180,
												Short.MAX_VALUE)
										.addComponent(btnOptions,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(btnExit,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(labelTitle)
								.addGap(18, 18, 18)
								.addComponent(btnNewGame)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnLoadGame)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnOptions)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btnExit)
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

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
