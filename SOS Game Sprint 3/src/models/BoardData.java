package models;

import java.util.ArrayList;
import java.util.List;

public class BoardData {

private ArrayList<ArrayList<String>> gameBoard;
private Integer boardSize;
	
	//Constructor
	public BoardData() {
		 gameBoard = new ArrayList<ArrayList<String>>();
	}
	
	public void SetBoard(Integer size) {
		boardSize = size;
		for(Integer i = 0; i < size; i++) {
			ArrayList<String> gameRow = new ArrayList<String>();
			for(Integer j = 0; j < size; j++) {
				gameRow.add("");
			}
			gameBoard.add(gameRow);
		}
	}
	
	public ArrayList<Integer> makeSMove(Integer place) {
		ArrayList<Integer> points = new ArrayList<Integer>();
		Integer pointsIncrease = 0;
		Integer first, second;
		first = place/boardSize;
		second = place%boardSize;
		gameBoard.get(first).set(second, "S");
		
		//Check the 8 different ways SOS could be created via a S
		if(first-2 >= 0 && second-2 >=0 && gameBoard.get(first-2).get(second-2).equals("S") && gameBoard.get(first-1).get(second-1).equals("O")) {
			pointsIncrease ++;
			points.add((first-2)*boardSize + (second-2));
			points.add((first-1)*boardSize + (second-1));
		}
		if(second-2 >= 0 && gameBoard.get(first).get(second-2).equals("S") && gameBoard.get(first).get(second-1).equals("O")) {
		    pointsIncrease ++;
		    points.add((first)*boardSize + (second-2));
		    points.add((first)*boardSize + (second-1));
		}
		if(first+2 < boardSize && second-2 >=0 && gameBoard.get(first+2).get(second-2).equals("S") && gameBoard.get(first+1).get(second-1).equals("O")) {
		    pointsIncrease ++;
		    points.add((first+2)*boardSize + (second-2));
		    points.add((first+1)*boardSize + (second-1));
		}
		if(first+2 < boardSize && gameBoard.get(first+2).get(second).equals("S") && gameBoard.get(first+1).get(second).equals("O")) {
		    pointsIncrease ++;
		    points.add((first+2)*boardSize + (second));
		    points.add((first+1)*boardSize + (second));
		}
		if(first+2 < boardSize && second+2 < boardSize && gameBoard.get(first+2).get(second+2).equals("S") && gameBoard.get(first+1).get(second+1).equals("O")) {
		    pointsIncrease ++;
		    points.add((first+2)*boardSize + (second+2));
		    points.add((first+1)*boardSize + (second+1));
		}
		if(second+2 < boardSize && gameBoard.get(first).get(second+2).equals("S") && gameBoard.get(first).get(second+1).equals("O")) {
		    pointsIncrease ++;
		    points.add((first)*boardSize + (second+2));
		    points.add((first)*boardSize + (second+1));
		}
		if(first-2 >= 0 && second+2 < boardSize && gameBoard.get(first-2).get(second+2).equals("S") && gameBoard.get(first-1).get(second+1).equals("O")) {
		    pointsIncrease ++;
		    points.add((first-2)*boardSize + (second+2));
		    points.add((first-1)*boardSize + (second+1));
		}
		if(first-2 >= 0 && gameBoard.get(first-2).get(second).equals("S") && gameBoard.get(first-1).get(second).equals("O")) {
		    pointsIncrease ++;
		    points.add((first-2)*boardSize + (second));
		    points.add((first-1)*boardSize + (second));
		}
		if(pointsIncrease > 0) {
			points.add(0,pointsIncrease);
			points.add(place);
		}
		return points;
	}
	
	public ArrayList<Integer> makeOMove(Integer place) {
		ArrayList<Integer> points = new ArrayList<Integer>();
		Integer pointsIncrease = 0;
		Integer first, second;
		first = place/boardSize;
		second = place%boardSize;
		gameBoard.get(first).set(second, "O");
		
		//Checks the 4 different ways a SOS could be created via a O
		if(first-1 >= 0 && second-1 >= 0 && first+1 < boardSize && second+1 < boardSize && gameBoard.get(first-1).get(second-1).equals("S") && gameBoard.get(first+1).get(second+1).equals("S")) {
			pointsIncrease ++;
		    points.add((first-1)*boardSize + (second-1));
		    points.add((first+1)*boardSize + (second+1));
		}
		if(first-1 >= 0 && second+1 < boardSize && first+1 < boardSize && second-1 >= 0 && gameBoard.get(first-1).get(second+1).equals("S") && gameBoard.get(first+1).get(second-1).equals("S")) {
			pointsIncrease ++;
		    points.add((first-1)*boardSize + (second+1));
		    points.add((first+1)*boardSize + (second-1));
		}
		if(first-1 >= 0 && first+1 < boardSize && gameBoard.get(first-1).get(second).equals("S") && gameBoard.get(first+1).get(second).equals("S")) {
		    pointsIncrease ++;
		    points.add((first-1)*boardSize + (second));
		    points.add((first+1)*boardSize + (second));
		}
		if(second-1 >= 0 && second+1 < boardSize && gameBoard.get(first).get(second-1).equals("S") && gameBoard.get(first).get(second+1).equals("S")) {
		    pointsIncrease ++;
		    points.add((first)*boardSize + (second-1));
		    points.add((first)*boardSize + (second+1));
		}
		if(pointsIncrease > 0) {
			points.add(0,pointsIncrease);
			points.add(place);
		}
		return points;
	}
}
	

