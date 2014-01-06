package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class GuiOptions extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel;
	private JButton btnSave;
	private JLabel labelBoardSize;
	private JLabel labelMines;
	private JLabel labelX;
	private JSpinner spinnerXSize;
	private JSpinner spinnerYSize;
	private JSpinner spinnerMines;

	private int xSize = 0;
	private int ySize = 0;
	private int mines = 0;

	public GuiOptions() {
		initComponents();
	}

	public GuiOptions(int sizeX, int sizeY, int mines) {
		initComponents();
		this.xSize = sizeX;
		this.ySize = sizeY;
		this.mines = mines;
		spinnerXSize.setValue(sizeX);
		spinnerYSize.setValue(sizeY);
		spinnerMines.setValue(mines);
	}

	public void setWindow() {
		setTitle("Opcje");
		setMinimumSize(new Dimension(250, 130));
		setResizable(false);
	}

	private void initComponents() {

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel);

		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel.add(top, BorderLayout.PAGE_START);

		labelBoardSize = new JLabel("Rozmiar planszy:");
		top.add(labelBoardSize);

		spinnerXSize = new JSpinner();
		top.add(spinnerXSize);
		labelX = new JLabel("X");
		top.add(labelX);

		spinnerYSize = new JSpinner();
		top.add(spinnerYSize);

		JPanel center = new JPanel();
		center.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel.add(center, BorderLayout.CENTER);

		labelMines = new JLabel("Min:");
		center.add(labelMines);

		spinnerMines = new JSpinner();
		center.add(spinnerMines);

		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
		panel.add(bottom, BorderLayout.PAGE_END);

		btnSave = new JButton("Zapisz");
		bottom.add(btnSave);

		btnCancel = new JButton("Anuluj");
		bottom.add(btnCancel);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xSize = (Integer) spinnerXSize.getValue();
				ySize = (Integer) spinnerYSize.getValue();
				mines = (Integer) spinnerMines.getValue();
				backToGame();
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToGame();
			}
		});

		setWindow();
	
	}

	public int getXsize() {
		return xSize;
	}

	public int getYsize() {
		return ySize;
	}

	public int getMinesCount() {
		return mines;
	}

	private void backToGame() {
		SapperGui sapper = new SapperGui(xSize, ySize, mines);
		sapper.setLocation(this.getLocationOnScreen());
		sapper.setVisible(true);
		this.setVisible(false);
	}

}