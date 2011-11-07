package lukianol.tictactoe.gamestate;

import lukianol.tictactoe.GameState;
import lukianol.tictactoe.Position;
import lukianol.tictactoe.StrokeKind;
import lukianol.tictactoe.resources.Exceptions;

public final class GameStateResult {
	
	private StrokeKind _winnerStroke;
	private GameState _gameState;
	private Position[] _wonPositions;
	
	private GameStateResult(){
		
	}

	public GameStateResult(GameState gameState){
		if (gameState.equals(GameState.Won))
			throw new IllegalArgumentException(Exceptions.string(Exceptions.GAMERESULT_DIFFERENT_CTOR));
		_gameState = gameState;
	}
	
	public GameStateResult(StrokeKind winnerStroke, Position[] wonPositions){
		
		_gameState = GameState.Won;
		_winnerStroke = winnerStroke;
		this._wonPositions = wonPositions;
		
	}

	public StrokeKind getWinnerStroke(){
		return _winnerStroke;
	}
	
	public GameState getGameState(){
		return _gameState;
	}

	public Position[] getWonPositions(){
		return this._wonPositions;
	}
	
	public final static GameStateResult  UndefinedResult = new GameStateResult();
}
