package main;

import controller.*;

public class GameMain {

	static GameController gameController;
	static BoardController boardController;
	static ActionController pieceActionController;	

	public static void main(String[] args) {

		//INSTANTIATE ALL CONTROLLERS 
		boardController = new BoardController();
		pieceActionController = ActionController.getInstance();
		gameController = new GameController();
		
		//Assign Controllers 
		pieceActionController.setGameController(gameController);
		pieceActionController.setBoardController(boardController);
		gameController.setBoardController(boardController);
		System.out.println("All Controllers constructed");
				
	}
	
	
}
