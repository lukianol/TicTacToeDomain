package lukianol.tictactoe.gamestate;

import lukianol.tictactoe.Field;
import lukianol.tictactoe.GameState;
import lukianol.tictactoe.StrokeKind;

public final class GameStateResult {
	
	private StrokeKind _winnerStroke;
	private GameState _gameState;
	private Field[] _wonFields;
	
	private GameStateResult(){
		
	}

	public GameStateResult(GameState gameState){
		if (gameState.equals(GameState.Won))
			throw new IllegalArgumentException("Use a constructor with a winnerStroke instead");
		_gameState = gameState;
	}
	
	public GameStateResult(StrokeKind winnerStroke, Field[] wonFields){
		
		_gameState = GameState.Won;
		_winnerStroke = winnerStroke;
		this._wonFields = wonFields;
		
	}

	public StrokeKind getWinnerStroke(){
		return _winnerStroke;
	}
	
	public GameState getGameState(){
		return _gameState;
	}

	public Field[] getWonFields(){
		return this._wonFields;
	}
	
	public final static GameStateResult  UndefinedResult = new GameStateResult();
}