package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

public class GuiOptions extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel;
	private JButton btnSave;
	private JLabel labelBoardSize;
	private JLabel labelMines;
	private JLabel labelX;
	private JSpinner spinnerMines;
	private JSpinner spinnerXsize;
	private JSpinner spinnerYSize;

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
		spinnerMines.setValue(mines);
		spinnerXsize.setValue(sizeX);
		spinnerYSize.setValue(sizeY);
	}

	private void initComponents() {

		labelBoardSize = new JLabel();
		spinnerXsize = new JSpinner();
		labelX = new JLabel();
		spinnerYSize = new JSpinner();
		labelMines = new JLabel();
		spinnerMines = new JSpinner();
		btnSave = new JButton();
		btnCancel = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		labelBoardSize.setText("Board size:");
		labelX.setText("X");
		labelMines.setText("Mines:");
		btnSave.setText("Save");
		btnCancel.setText("Cancel");

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xSize = (Integer) spinnerXsize.getValue();
				ySize = (Integer) spinnerYSize.getValue();
				mines = (Integer) spinnerMines.getValue();
				backToHomeScreen();
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToHomeScreen();
			}
		});
			
		int preferredSize = GroupLayout.PREFERRED_SIZE;
		int defaultSize = GroupLayout.DEFAULT_SIZE;
		int maxValue = Short.MAX_VALUE;
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		layoutHorizontalSet(layout, preferredSize, defaultSize, maxValue);
		layoutVerticalSet(layout, preferredSize, defaultSize, maxValue);
						
		pack();		
	}
	
	public void layoutHorizontalSet(GroupLayout layout, int preferedSize, int defaultSize, int maxValue) {	
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addGroup(layout.createSequentialGroup()
								.addComponent(labelBoardSize)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(spinnerXsize, preferedSize, 40, preferedSize))							
							.addGroup(layout.createSequentialGroup()
								.addComponent(labelMines)
								.addGap(18, 18, 18)
						.addComponent(spinnerMines, preferedSize, 43, preferedSize)))						
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(labelX)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(spinnerYSize, preferedSize, 40, preferedSize)
					.addGap(0, 0, maxValue))			
					.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addComponent(btnSave)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, defaultSize, maxValue)
				.addComponent(btnCancel)))
		.addContainerGap()));		
	}
	
	public void layoutVerticalSet(GroupLayout layout, int preferedSize, int defaultSize, int maxValue) {
		layout.setVerticalGroup(layout.createParallelGroup(
			GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(labelBoardSize)
						.addComponent(spinnerXsize, preferedSize, defaultSize, preferedSize)
						.addComponent(labelX)
					.addComponent(spinnerYSize, preferedSize, defaultSize, preferedSize))		
				.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(labelMines)
					.addComponent(spinnerMines, preferedSize, defaultSize, preferedSize))				
				.addGap(18, 18, 18)	
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(btnSave)
					.addComponent(btnCancel))
		.addContainerGap(defaultSize, maxValue)));	
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

	private void backToHomeScreen() {
		HomeScreen saper = new HomeScreen(xSize, ySize, mines);
		saper.setLocation(this.getLocationOnScreen());
		saper.setVisible(true);
		this.setVisible(false);
	}
	
}