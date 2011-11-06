package lukianol.tictactoe.gamestate;

public final class DefaultGameStateHandler extends GameStateHandlerBase {
	
	@Override
	protected GameStateHandler onGetNextHandler(){
		return new ColumnWinGameStateHandler();		
	}

}
