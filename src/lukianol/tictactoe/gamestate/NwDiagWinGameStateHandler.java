package lukianol.tictactoe.gamestate;

import lukianol.tictactoe.Field;
import lukianol.tictactoe.IGame;

public final class NwDiagWinGameStateHandler extends DiagFieldWinGameStateHandler {
	
	@Override
	protected GameStateHandler onGetNextHandler(){
		return new NeDiagWinGameStateHandler();
	}

	@Override
	protected Field getNextField(IGame game, int x, int y) {
		return game.getField(x + 1, y + 1);
	}

	@Override
	protected Field getCurrentField(IGame game, int x, int y) {
		return game.getField(x, y);
	}

}
