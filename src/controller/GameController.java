package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.GameTurn;
import model.Piece;
import model.Square;
import model.State;
import model.Team;

import view.AvailablePiecePanel;
import view.ControlPanel;
import view.EndTurnPanel;
import view.PieceInfoPanel;
import view.TeamColorPanel;
import view.TimePanel;

/**
 * Responsible for turn handling and computing the winner team
 *
 * @author skh, ms, pv
 *
 */

public class GameController implements Observer {
	
	//GAME CONTROL
	private static GameTurn gameTimer;	
	private static BoardController gameBoard;
	
	//UI
	private ControlPanel controlPanel;
	private TeamColorPanel teamColorPanel;
	private EndTurnPanel endTurnPanel;
	private TimePanel timePanel;
	
	//BOARD OBJECTS
	private Piece activePiece;
	private Piece targetPiece;
	private Square activeSquare;
	private Square targetSquare;
	private State currentState;
	private Team currentTeam;

	private static ArrayList<Piece> gamePiecesList = new ArrayList<Piece>();
	
	public GameController() {
		currentState = State.STARTGAME;
		generateGamePieces();
		startTimer();
		currentState = State.STARTMOVE;
	}
	
	private void startTimer() {
		gameTimer = new GameTurn();
		observe(gameTimer);
		gameTimer.start();
	}
	
	public void generateGamePieces() {
		setGamePiecesList((new PieceCreationController()).generateGamePieces());
	}	
	
	public void computeWinner() {
		
	}
	
	public void observe(Observable o) {
		o.addObserver(this);
	}
	
	public void setUIObjects(BoardController bd) {
		setControlPanel(bd.getBoardFrame().getControlPanel());
		teamColorPanel = controlPanel.getTeamColorPanel();
		endTurnPanel = controlPanel.getEndTurnPanel();
		timePanel = controlPanel.getTimePanel();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		GameTurn gameTurn = (GameTurn) o;
		if (gameTurn == null) return;
		int data = gameTurn.getGameTimer();
		
		// update time on ControlPanel view
		timePanel.setTime(data);

		// set end turn conditions
		endTurnPanel.setGameTurn(gameTurn);

		// update available pieces for the current team 
		if (gamePiecesList != null) {
			int count = getAvailablePieceCount();
			controlPanel.getAvailablePiecePanel().setAvailablePieces(count);
		}
		
		// when time is up
		if (data == 0) {
			System.out.println("Player change!");
			handleEndTurn();
		}
	}
	
	private int getAvailablePieceCount() {
		int count = 0;
		for (Piece piece : gamePiecesList) {
			if (teamColorPanel.getTeamColorEnum() == piece.getTeam()) {
				count++;
			}
		}
//		System.out.println("----current team " + teamColorPanel.getTeamColorEnum() 
//		+ " pierces: " + count);
		return count;
	}
	
	private void handleEndTurn() {
		System.out.println("Player change!");
		
		// update team color on ControlPanel view
		Color colorChange = (teamColorPanel.getTeamColorEnum() == Team.BLUE) ? Color.RED : Color.BLUE;
		teamColorPanel.setTeamColor(colorChange);
		
		// auto end the current player's turn
		endTurnPanel.executeEndTurn();
		
		// reset timer
		startTimer();
		
		// reset player move
		setCurrentState(State.STARTMOVE);
	}
	
	public void updatePieceInformation(Piece pce) {
		// Update Piece Statistics on Selection
		PieceInfoPanel pieceInfoPanel = controlPanel.getPieceInfoPanel();
		pieceInfoPanel.updatePieceInformation(pce);
	}
	
	public static ArrayList<Piece> getGamePiecesList() {
		return gamePiecesList;
	}

	public static void setGamePiecesList(ArrayList<Piece> piecesList) {
		gamePiecesList = piecesList;
	}	
	
	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State newState) {
		this.currentState = newState;
	}	
	
	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}

	public Team getCurrentTeam() {
		return currentTeam;
	}

	public void setCurrentTeam(Team currentTeam) {
		this.currentTeam = currentTeam;
	}

	public Piece getActivePiece() {
		return activePiece;
	}

	public void setActivePiece(Piece activePiece) {
		this.activePiece = activePiece;
	}

	public Piece getTargetPiece() {
		return targetPiece;
	}

	public void setTargetPiece(Piece targetPiece) {
		this.targetPiece = targetPiece;
	}

	public Square getActiveSquare() {
		return activeSquare;
	}

	public void setActiveSquare(Square activeSquare) {
		this.activeSquare = activeSquare;
	}

	public Square getTargetSquare() {
		return targetSquare;
	}

	public void setTargetSquare(Square targetSquare) {
		this.targetSquare = targetSquare;
	}

	public static GameTurn getGameTurn() {
		return gameTimer;
	}

}