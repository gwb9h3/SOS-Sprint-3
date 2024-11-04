package controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import views.*;
import models.*;

public class GameController {
    
    private GameView gameView;
    private BoardData virtualBoard;
    private ArrayList<Color> colorList;
    
    public GameController(GameView gv) {
        this.gameView = gv;
        this.virtualBoard = new BoardData();
        this.colorList = new ArrayList<Color>();
    
        gameView.getBtnEnter().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel buttonPanel = gameView.getButtonPanel();
                
                String input = gameView.getNumSize().getText();
                int boardSize = 0;
                // Attempt to get an integer from the board size input
                try {
                    boardSize = Integer.parseInt(input);
                }
                
                catch(Exception f) {
                    JOptionPane.showMessageDialog(gameView, "Please input a valid boardsize", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                virtualBoard.SetBoard(boardSize);
                // Checks to see if the number input for the board size is within the valid range for the game
                if(3 <= boardSize && boardSize <= 10 && (gameView.getGameMode1().isSelected() || gameView.getGameMode2().isSelected())) {
                    buttonPanel.removeAll();
                    buttonPanel.setLayout(new GridLayout(boardSize, boardSize));
                    JButton[] buttons = new JButton[boardSize * boardSize];
                    
                    //Loop to create the buttons and action listeners for each button on the board
                    for (int i = 0; i < boardSize * boardSize; i++) {
                        final int currentI = i;
                        buttons[i] = new JButton("");
                        buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
                        buttonPanel.add(buttons[i]);
                        //Color list is a list to keep track of what the background color of each button is to determine the appropriate background color when dealing with SOS's
                        colorList.add(Color.white);
                        
                        buttons[i].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JButton clickedButton = (JButton) e.getSource();
                                //Checks to see if it is the Blue players turn and if they have selected S or O
                                if (gameView.getPlayer1() && (gameView.getSelector1().isSelected() || gameView.getSelector2().isSelected())) {
                                	//Sets the current button to false (needed to be done before updateScore function)
                                	clickedButton.setEnabled(false);
                                	// If S is selected then the game will make the S move in the given tile
                                	if (gameView.getSelector1().isSelected()) {
                                        clickedButton.setText("S");
                                        ArrayList<Integer> pointsList = virtualBoard.makeSMove(currentI);
                                        updateScore(pointsList, Color.BLUE);
                                    } 
                                	// If O is selected then the game will make the O move in the given tile
                                    else if (gameView.getSelector2().isSelected()) {
                                        clickedButton.setText("O");
                                        ArrayList<Integer> pointsList = virtualBoard.makeOMove(currentI);
                                        updateScore(pointsList, Color.BLUE);
                                    }
                                	// Changes the turn to the other player
                                    gameView.setPlayer1(false);
                                    gameView.setPlayer2(true);
                                    gameView.getCurrentTurn().setText("Current turn is Red");
                                }
                              //Checks to see if it is the Red players turn and if they have selected S or O
                                else if (gameView.getPlayer2() && (gameView.getSelector3().isSelected() || gameView.getSelector4().isSelected())) {
                                	//Sets the current button to false (needed to be done before updateScore function)
                                    clickedButton.setEnabled(false);
                                 // If S is selected then the game will make the S move in the given tile
                                	if (gameView.getSelector3().isSelected()) {
                                        clickedButton.setText("S");
                                        ArrayList<Integer> pointsList = virtualBoard.makeSMove(currentI);
                                        updateScore(pointsList, Color.RED);
                                    } 
                                	// If O is selected then the game will make the O move in the given tile
                                    else if (gameView.getSelector4().isSelected()) {
                                        clickedButton.setText("O");
                                        ArrayList<Integer> pointsList = virtualBoard.makeOMove(currentI);
                                        updateScore(pointsList, Color.RED);
                                    }
                                	// Changes the turn to the other player
                                    gameView.setPlayer2(false); 
                                    gameView.setPlayer1(true);
                                    gameView.getCurrentTurn().setText("Current turn is Blue");
                                } 
                                //Error detection to check if a player has selected S or O before making a move
                                else {
                                    JOptionPane.showMessageDialog(gameView, "Please select S or O before making a move", "Invalid Move", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                        });
                    }
                    
                    //Updates the GUI 
                    gameView.setButtons(buttons);
                    gameView.revalidate();
                    gameView.repaint();
                }
                //Checks to see if the player has selected a gamemode and a valid board size before pressing enter
                else {
                    JOptionPane.showMessageDialog(gameView, "Please confirm gamemode is selected and valid boardsize is entered", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    public void updateScore(ArrayList<Integer> pointsList, Color color) {
    	//Checks if the list of points and button locations is null or not
        if (pointsList != null && pointsList.size() > 0) {
            int points = pointsList.get(0);
            JButton[] buttons = gameView.getButtons();
            //Creates a variable for the color purple to use later on to assign background color
            Color purple = new Color(128, 0, 128);
            //Checks to see if it is the Blue players turn to update their points
            if (color.equals(Color.blue)) {
                gameView.setBlueTotal(gameView.getBlueTotal() + points);
                gameView.getBluePoints().setText(Integer.toString(gameView.getBlueTotal()));
            } 
            //Updates the Red players points
            else {
                gameView.setRedTotal(gameView.getRedTotal() + points);
                gameView.getRedPoints().setText(Integer.toString(gameView.getRedTotal()));
            }
            //Function to loop through the list of button locations to change the background color of because they are a part of valid SOS's
            for (int i = 1; i < pointsList.size(); i++) {
            	//Sets the background color to purple because the tile is associated with multiple SOS's
            	if(color.equals(Color.blue) && (colorList.get(pointsList.get(i)).equals(Color.red) || colorList.get(pointsList.get(i)).equals(purple))){
            		buttons[pointsList.get(i)].setBackground(purple);
            		colorList.set(pointsList.get(i), purple);
            	}
            	//Sets the background color to purple because the tile is associated with multiple SOS's
            	else if(color.equals(Color.red) && (colorList.get(pointsList.get(i)).equals(Color.blue) || colorList.get(pointsList.get(i)).equals(purple))) {
            		buttons[pointsList.get(i)].setBackground(purple);
            		colorList.set(pointsList.get(i), purple);
            	}
            	//Sets the background color to the given player's color
            	else {
            		buttons[pointsList.get(i)].setBackground(color);
            		colorList.set(pointsList.get(i), color);
            	}
            }
        }
        //Two functions to see if the game is over given the selected gamemode
        if(gameView.getGameMode1().isSelected()) {
        	checkSimpleGame(colorList.size());
        }
        if(gameView.getGameMode2().isSelected()) {
        	checkGeneralGame(colorList.size());
        }
    }
    public boolean checkSimpleGame(Integer boardLength) {
    	//Checks to see if either player has scored points and ends the game
    	if(gameView.getBlueTotal() > 0 || gameView.getRedTotal() > 0) {
    		if(gameView.getBlueTotal() > 0) {
    			JOptionPane.showMessageDialog(gameView, "The blue player has won");
    		}
    		else {
    			JOptionPane.showMessageDialog(gameView, "The red player has won");
    		}
    		//Function to press all buttons
    		JButton[] buttons = gameView.getButtons();
    		for(int i = 0; i < boardLength; i++) {
    			buttons[i].setEnabled(false);
    		}
    		return true;
    	}
    	//Loops through to see if the game is a tie
    	JButton[] buttons = gameView.getButtons();
		for(int i = 0; i < boardLength; i++) {
			if(buttons[i].isEnabled()) {
				return false;
			}
		}
		JOptionPane.showMessageDialog(gameView, "The game is a tie");
    	return true;
    }
    public boolean checkGeneralGame(Integer boardLength) {
		JButton[] buttons = gameView.getButtons();
		//Loops through to see if every tile has been pressed
		for(int i = 0; i < boardLength; i++) {
			if(buttons[i].isEnabled()) {
				return false;
			}
		}
		//Checks which player has won or if it is a tie
		if(gameView.getBlueTotal() > gameView.getRedTotal()) {
			JOptionPane.showMessageDialog(gameView, "The blue player has won");
		}
		else if(gameView.getRedTotal() > gameView.getBlueTotal()) {
			JOptionPane.showMessageDialog(gameView, "The red player has won");
		}
		else {
			JOptionPane.showMessageDialog(gameView, "The game is a tie");
		}
		return true;
    }
}