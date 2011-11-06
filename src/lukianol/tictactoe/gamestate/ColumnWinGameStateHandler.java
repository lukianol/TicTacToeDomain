package lukianol.tictactoe.gamestate;

import lukianol.tictactoe.Field;
import lukianol.tictactoe.IGame;

public final class ColumnWinGameStateHandler extends DirectFieldWinGameStateHandlerBase {
			
	@Override
	protected GameStateHandler onGetNextHandler(){
		return new RowWinGameStateHandler();
	}

	@Override
	protected Field getCurrentField(IGame game, int x, int y) {
		return game.getField(x, y);
	}

}
