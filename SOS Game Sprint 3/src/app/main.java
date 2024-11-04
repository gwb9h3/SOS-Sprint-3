package app;

import views.*;
import controllers.*;

public class main {

	public static void main(String[] args) {
		
		GameView gv = new GameView();
		GameController gc = new GameController(gv);
		gv.setVisible(true);
	}

}
