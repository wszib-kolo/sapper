package sapper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;

public class GuiOptions extends javax.swing.JFrame {
    
    
	private JButton btnBack;
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
    public GuiOptions(int sizeX, int sizeY, int mines2) {
    	initComponents();
    	spinnerMines.setValue(mines2);
    	spinnerXsize.setValue(sizeX);
    	spinnerYSize.setValue(sizeY);
    	
	}
	@SuppressWarnings("unchecked")
                          
    private void initComponents() {

        labelBoardSize = new JLabel();
        spinnerXsize = new JSpinner();
        labelX = new JLabel();
        spinnerYSize = new JSpinner();
        labelMines = new JLabel();
        spinnerMines = new JSpinner();
        btnSave = new JButton();
        btnBack = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelBoardSize.setText("Board size:");
        labelX.setText("X");
        labelMines.setText("Mines:");
        btnSave.setText("Save");
        btnBack.setText("<< Back");
        
        btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				xSize = (Integer)spinnerXsize.getValue();
				ySize = (Integer)spinnerYSize.getValue();
				mines = (Integer)spinnerMines.getValue();
			}
		});
        
        btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backToHomeScreen();
			}
		});
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelBoardSize)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spinnerXsize, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelMines)
                                .addGap(18, 18, 18)
                                .addComponent(spinnerMines, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelX)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerYSize, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBoardSize)
                    .addComponent(spinnerXsize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelX)
                    .addComponent(spinnerYSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMines)
                    .addComponent(spinnerMines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnBack))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    
    
    public int getXsize(){
    	return xSize;
    }
    
    public int getYsize(){
    	return ySize;
    }
    
    public int getMinesCount(){
    	return mines;
    }
    
    private void backToHomeScreen(){
    	HomeScreen saper = new HomeScreen(xSize, ySize, mines);
		saper.setVisible(true);
		this.setVisible(false);
    }
                           
}
