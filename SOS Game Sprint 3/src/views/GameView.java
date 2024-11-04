package views;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class GameView extends JFrame{
	
	private JRadioButton selector1, selector2, selector3, selector4, gameMode1, gameMode2;
	private JButton btnEnter;
	private JCheckBox check1;
	private ButtonGroup g1,g2,g3;
	private JButton[] buttons;
	private JPanel buttonPanel;
	private JTextField numSize;
	private JLabel currentTurn,bluePoints,redPoints;
	private Integer blueTotal = 0,redTotal = 0;
	private boolean player1 = true ,player2 = false;
	
	public GameView() {
		setTitle("SOS Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//Top panel
		JPanel topPanel = new JPanel();
		//Adds the selector to play against cpu or not
		topPanel.add(new JLabel("Check to play against CPU"));
		check1 = new JCheckBox();
		topPanel.add(check1);
		//Adds the selector for the two game modes
		gameMode1 = new JRadioButton("Simple Game");
		gameMode2 = new JRadioButton("General Game");
		topPanel.add(gameMode1);
		topPanel.add(gameMode2);
		g3 = new ButtonGroup();
		g3.add(gameMode1);
		g3.add(gameMode2);
		//Adds the input section for board size
		topPanel.add(new JLabel("Enter the board size"));
		numSize = new JTextField(3);
		topPanel.add(numSize);
		btnEnter = new JButton("Enter");
		topPanel.add(btnEnter);
		//Creates title and boarder for the section
		String title1 = "SOS Game";
		Border border1 = BorderFactory.createTitledBorder(title1);
		topPanel.setBorder(border1);
		
		//Left panel
		JPanel leftPanel = new JPanel(new GridLayout(4,1));
		String title2 = "";
		Border border2 = BorderFactory.createTitledBorder(title2);
		leftPanel.setBorder(border2);
		leftPanel.add(new JLabel("Blue Player"));
		selector1 = new JRadioButton("S");
		selector2 = new JRadioButton("O");
		leftPanel.add(selector1);
		leftPanel.add(selector2);
		bluePoints = new JLabel("0");
		leftPanel.add(bluePoints);
		g1 = new ButtonGroup();
		g1.add(selector1);
		g1.add(selector2);
		
		//Right panel
		JPanel rightPanel = new JPanel(new GridLayout(4,1));
		String title3 = "";
		Border border3 = BorderFactory.createTitledBorder(title3);
		rightPanel.setBorder(border3);
		rightPanel.add(new JLabel("Red Player"));
		selector3 = new JRadioButton("S");
		selector4 = new JRadioButton("O");
		rightPanel.add(selector3);
		rightPanel.add(selector4);
		redPoints = new JLabel("0");
		rightPanel.add(redPoints);
		g2 = new ButtonGroup();
		g2.add(selector3);
		g2.add(selector4);
		
		//Center panel
        buttonPanel = new JPanel();
        
		//Bottom panel
		JPanel bottomPanel = new JPanel();
		currentTurn = new JLabel("Blue starts first");
		bottomPanel.add(currentTurn);
		
        
		add(topPanel,BorderLayout.NORTH);
		add(leftPanel,BorderLayout.WEST);
		add(rightPanel,BorderLayout.EAST);
		add(buttonPanel,BorderLayout.CENTER);
		add(bottomPanel,BorderLayout.SOUTH);
		setPreferredSize(new Dimension(750,500));
		pack();
	}

	public JRadioButton getSelector1() {
		return selector1;
	}

	public JRadioButton getSelector2() {
		return selector2;
	}

	public JRadioButton getSelector3() {
		return selector3;
	}

	public JRadioButton getSelector4() {
		return selector4;
	}

	public JRadioButton getGameMode1() {
		return gameMode1;
	}
	
	public JRadioButton getGameMode2() {
		return gameMode2;
	}
	
	public JCheckBox getCheck1() {
		return check1;
	}

	public ButtonGroup getG1() {
		return g1;
	}

	public ButtonGroup getG2() {
		return g2;
	}
	
	public ButtonGroup getG3() {
		return g3;
	}
	
	public JButton[] getButtons() {
		return buttons;
	}
	
	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public boolean getPlayer1() {
		return player1;
	}
	
	public boolean getPlayer2() {
		return player2;
	}
	
	public JButton getBtnEnter() {
		return btnEnter;
	}
	
	public JTextField getNumSize() {
		return numSize;
	}
	
	public JLabel getCurrentTurn() {
		return currentTurn;
	}
	
	public JLabel getBluePoints() {
		return bluePoints;
	}
	
	public JLabel getRedPoints(){
		return redPoints;
	}
	
	public Integer getBlueTotal() {
		return blueTotal;
	}
	
	public Integer getRedTotal(){
		return redTotal;
	}
	
	public void setBlueTotal(Integer num) {
		this.blueTotal = num;
	}
	
	public void setRedTotal(Integer num) {
		this.redTotal = num;
	}
	
	public void setPlayer1(boolean bool) {
		this.player1 = bool;
	}
	
	public void setPlayer2(boolean bool) {
		this.player2 = bool;
	}
	
	public void setButtons(JButton[] buttons) {
		this.buttons = buttons;
	}
	
	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}
}
