package controller;

import java.awt.Component;
import java.awt.event.MouseEvent;

import model.board.Square;
import model.piece.Piece;
import model.state.IGameState;
import model.state.StateMove;
import view.BoardPanel;
import view.ControlPanel;
import view.DialogView;
import view.SquareView;

/**
 * This is main handler for selecting pieces and controlling their moves
 * Class is assigned access to the GameController
 * 
 * @author mark
 *
 */
public class ActionController {

	private GameController gameController;
	private BoardController boardController;

	private Piece activePiece;
	private Square activeSquare;
	private Square targetSquare;

	private int actionCount;
	private int actionButton;

	private IGameState gameState;

	private static ActionController instance;
	
	private ActionController(){
		gameState = StateMove.getInstance(this);
	}
	
	public static synchronized ActionController getInstance() {
		if (instance == null) {
			instance = new ActionController();
		}
		return instance;
	}
	
	// state change
	public void changeState(IGameState s)
	{
		gameState = s;
	}
	
	public void startAction(ActionController a, Square sqr)
	{
		activeSquare = sqr;
		gameState.startAction(this, sqr);		
	}

	public void endAction(ActionController a, Square sqr)
	{
		gameState.endAction(this, sqr);
		checkActionCount();
		boardController.getBoardData().doCellsUpdate();
	}	
	
	public void setActionButton(int i) {
		this.actionButton = i;
	}
	
	public int getActionButton() {
		return this.actionButton;
	}
	
	public IGameState getGameState() {
		return gameState;
	}

	public void setGameState(IGameState gameState) {
		this.gameState = gameState;
	}
	
	public void showDialog(MouseEvent e, String msg) {
		DialogView.getInstance().showInformation(msg, e.getXOnScreen(), e.getYOnScreen());
	}
	
	/**
	 * Method controls number of actions permitted per turn
	 * Ends turn if at least 2 actions performed
	 * If less than 2 actions performed, increments the action counter
	 * 
	 * @author DS
	 */
	private void checkActionCount(){
		if (actionCount >=1){
			endTurn();
		}else{
			actionCount++;
		}
	}
	
	private void endTurn() {		
		// automatically switch player when finishing a move
		gameController.getGameTurn().setGameTimer(0);		
		// saving game board for undo
		boardController.saveToMemento();
		//reset actionCount;
		actionCount = 0;
	}
	
	public void resetTraitValuesToBase(){
		activePiece.getTraitSet().getDamageTrait().setTraitValueToBase();
		activePiece.getTraitSet().getRangeTrait().setTraitValueToBase();
	}
	
	private void handleEndGameUI(){
		// disable board game interactions
		BoardPanel boardPanel = boardController.getBoardFrame().getBoardPanel();
		for (Component com : boardPanel.getComponents()) {
			if (com instanceof SquareView) {
				SquareView sv = (SquareView)com;
				sv.removeMouseListener(sv);
			}
		}
		
		// disable timer
		gameController.getGameTurn().stop();
		
		// disable buttons in control panel
		ControlPanel controlPanel = boardController.getBoardFrame().getControlPanel();
		controlPanel.disableAllButtons();
	}
	
	private void clearActivePieceRange() {
		// reset board
		boardController.clearRangeCells();
	}
	public GameController getGameController() {
		return gameController;
	}	
	public void setGameController(GameController g) {
		this.gameController = g;
	}
	public BoardController getBoardController() {
		return boardController;
	}	
	public void setBoardController(BoardController bd) {
		this.boardController = bd;
	}	
	public void resetActionCount(){
		actionCount = 0;
	}
	public Piece getActivePiece() {
		return activePiece;
	}	
	public void setActivePiece(Piece activePiece) {
		this.activePiece = activePiece;
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
	
}
