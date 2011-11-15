package lukianol.tictactoe.gamestate;

import lukianol.tictactoe.Field;
import lukianol.tictactoe.GameBase;

public final class RowWinGameStateHandler extends DirectFieldWinGameStateHandlerBase {
	
	@Override
	protected GameStateHandler onGetNextHandler(){
		return new NwDiagWinGameStateHandler();
	}
	
	@Override
	protected Field getCurrentField(GameBase game, int x, int y) {
		return game.getField(y, x);
	}

}
