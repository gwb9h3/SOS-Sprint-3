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
                
                try {
                    boardSize = Integer.parseInt(input);
                }
                catch(Exception f) {
                    JOptionPane.showMessageDialog(gameView, "Please input a valid boardsize", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                virtualBoard.SetBoard(boardSize);
                
                if(3 <= boardSize && boardSize <= 10 && (gameView.getGameMode1().isSelected() || gameView.getGameMode2().isSelected())) {
                    buttonPanel.removeAll();
                    buttonPanel.setLayout(new GridLayout(boardSize, boardSize));
                    JButton[] buttons = new JButton[boardSize * boardSize];
                    
                    for (int i = 0; i < boardSize * boardSize; i++) {
                        final int currentI = i;
                        buttons[i] = new JButton("");
                        buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
                        buttonPanel.add(buttons[i]);
                        colorList.add(Color.white);
                        
                        buttons[i].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JButton clickedButton = (JButton) e.getSource();
                                if (gameView.getPlayer1() && (gameView.getSelector1().isSelected() || gameView.getSelector2().isSelected())) {
                                	clickedButton.setEnabled(false);
                                	if (gameView.getSelector1().isSelected()) {
                                        clickedButton.setText("S");
                                        ArrayList<Integer> pointsList = virtualBoard.makeSMove(currentI);
                                        updateScore(pointsList, Color.BLUE);
                                    } 
                                    else if (gameView.getSelector2().isSelected()) {
                                        clickedButton.setText("O");
                                        ArrayList<Integer> pointsList = virtualBoard.makeOMove(currentI);
                                        updateScore(pointsList, Color.BLUE);
                                    }
                                    gameView.setPlayer1(false);
                                    gameView.setPlayer2(true);
                                    gameView.getCurrentTurn().setText("Current turn is Red");
                                }
                                else if (gameView.getPlayer2() && (gameView.getSelector3().isSelected() || gameView.getSelector4().isSelected())) {
                                    clickedButton.setEnabled(false);
                                	if (gameView.getSelector3().isSelected()) {
                                        clickedButton.setText("S");
                                        ArrayList<Integer> pointsList = virtualBoard.makeSMove(currentI);
                                        updateScore(pointsList, Color.RED);
                                    } 
                                    else if (gameView.getSelector4().isSelected()) {
                                        clickedButton.setText("O");
                                        ArrayList<Integer> pointsList = virtualBoard.makeOMove(currentI);
                                        updateScore(pointsList, Color.RED);
                                    }
                                    gameView.setPlayer2(false);
                                    gameView.setPlayer1(true);
                                    gameView.getCurrentTurn().setText("Current turn is Blue");
                                } 
                                else {
                                    JOptionPane.showMessageDialog(gameView, "Please select S or O before making a move", "Invalid Move", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                        });
                    }
                    gameView.setButtons(buttons);
                    gameView.revalidate();
                    gameView.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(gameView, "Please confirm gamemode is selected and valid boardsize is entered", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    public void updateScore(ArrayList<Integer> pointsList, Color color) {
        if (pointsList != null && pointsList.size() > 0) {
            int points = pointsList.get(0);
            JButton[] buttons = gameView.getButtons();
            Color purple = new Color(128, 0, 128);
            if (color == Color.BLUE) {
                gameView.setBlueTotal(gameView.getBlueTotal() + points);
                gameView.getBluePoints().setText(Integer.toString(gameView.getBlueTotal()));
            } 
            else {
                gameView.setRedTotal(gameView.getRedTotal() + points);
                gameView.getRedPoints().setText(Integer.toString(gameView.getRedTotal()));
            }
            for (int i = 1; i < pointsList.size(); i++) {
            	if(color.equals(Color.blue) && (colorList.get(pointsList.get(i)).equals(Color.red) || colorList.get(pointsList.get(i)).equals(purple))){
            		buttons[pointsList.get(i)].setBackground(purple);
            		colorList.set(pointsList.get(i), purple);
            	}
            	else if(color.equals(Color.red) && (colorList.get(pointsList.get(i)).equals(Color.blue) || colorList.get(pointsList.get(i)).equals(purple))) {
            		buttons[pointsList.get(i)].setBackground(purple);
            		colorList.set(pointsList.get(i), purple);
            	}
            	else {
            		buttons[pointsList.get(i)].setBackground(color);
            		colorList.set(pointsList.get(i), color);
            	}
            }
        }
        if(gameView.getGameMode1().isSelected()) {
        	checkSimpleGame(colorList.size());
        }
        if(gameView.getGameMode2().isSelected()) {
        	checkGeneralGame(colorList.size());
        }
    }
    public boolean checkSimpleGame(Integer boardLength) {
    	if(gameView.getBlueTotal() > 0 || gameView.getRedTotal() > 0) {
    		if(gameView.getBlueTotal() > 0) {
    			JOptionPane.showMessageDialog(gameView, "The blue player has won");
    		}
    		else {
    			JOptionPane.showMessageDialog(gameView, "The red player has won");
    		}
    		JButton[] buttons = gameView.getButtons();
    		for(int i = 0; i < boardLength; i++) {
    			buttons[i].setEnabled(false);
    		}
    		return true;
    	}
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
		for(int i = 0; i < boardLength; i++) {
			if(buttons[i].isEnabled()) {
				return false;
			}
		}
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