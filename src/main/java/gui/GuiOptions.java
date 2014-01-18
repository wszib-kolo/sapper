package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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

	private int xSize = 2;
	private int ySize = 2;
	private int mines = 1;
	private int tmpXsize = 2, tmpYsize = 2, tmpMines = 1;

	SapperGui oldSapperGui;

	public GuiOptions() {
		initComponents();
	}

	public GuiOptions(SapperGui oldSapperGui) {
		initComponents();
		this.oldSapperGui = oldSapperGui;
		this.xSize = oldSapperGui.getSizeX();
		this.ySize = oldSapperGui.getSizeY();
		this.mines = oldSapperGui.getMines();
		spinnerMines.setValue(mines);
		spinnerXSize.setValue(xSize);
		spinnerYSize.setValue(ySize);
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
				saveGame();
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToGame();
			}
		});

		spinnerXSize.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				if ((Integer) spinnerXSize.getValue() < 2)
					spinnerXSize.setValue(2);
				compareSizeWithMines();

			}
		});

		spinnerYSize.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				if ((Integer) spinnerYSize.getValue() < 2)
					spinnerYSize.setValue(2);
				compareSizeWithMines();
			}
		});

		spinnerMines.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				if ((Integer) spinnerMines.getValue() < 1)
					spinnerMines.setValue(1);
				compareMinesWithSize();
			}
		});
		pack();
	}

	private void compareSizeWithMines() {
		tmpXsize = (Integer) spinnerXSize.getValue();
		tmpYsize = (Integer) spinnerYSize.getValue();
		tmpMines = (Integer) spinnerMines.getValue();

		if (tmpMines > (tmpXsize * tmpYsize))
			spinnerMines.setValue(tmpXsize * tmpYsize);
	}

	private void compareMinesWithSize() {
		tmpXsize = (Integer) spinnerXSize.getValue();
		tmpYsize = (Integer) spinnerYSize.getValue();
		tmpMines = tmpXsize * tmpYsize;
		if ((Integer) spinnerMines.getValue() > tmpMines) {
			if (tmpMines > 1)
				spinnerMines.setValue(tmpMines);
			else
				spinnerMines.setValue(1);
		}
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
		this.setVisible(false);
	}

	private void saveGame() {
		SapperGui sapper = new SapperGui(xSize, ySize, mines);
		sapper.setLocation(this.getLocationOnScreen());
		sapper.setVisible(true);
		this.setVisible(false);
		oldSapperGui.setVisible(false);
	}
}