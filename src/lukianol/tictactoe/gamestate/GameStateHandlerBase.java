package lukianol.tictactoe.gamestate;

import lukianol.tictactoe.IGame;

public abstract class GameStateHandlerBase implements GameStateHandler {

	public final GameStateResult handleState(IGame game) {
		
		GameStateResult result = onHandleState(game);
		
		if (result == GameStateResult.UndefinedResult) {
			result = getNextHandler().handleState(game);
		}
		
		return result;
	}
	
	protected GameStateResult onHandleState(IGame game){
		return GameStateResult.UndefinedResult;
	}
	
	protected GameStateHandler onGetNextHandler(){
		return new PlayingOrDrawnGameStateHandler();
	}
	
	private final GameStateHandler getNextHandler(){
		if (_nextGameHandler == null){
			_nextGameHandler = onGetNextHandler();
		}
		return _nextGameHandler;
	}
	
	private GameStateHandler _nextGameHandler;
}

