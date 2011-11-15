package lukianol.tictactoe.gamestate;

import lukianol.tictactoe.Field;
import lukianol.tictactoe.GameBase;

public final class NwDiagWinGameStateHandler extends DiagFieldWinGameStateHandler {
	
	@Override
	protected GameStateHandler onGetNextHandler(){
		return new NeDiagWinGameStateHandler();
	}

	@Override
	protected Field getNextField(GameBase game, int x, int y) {
		return game.getField(x + 1, y + 1);
	}

	@Override
	protected Field getCurrentField(GameBase game, int x, int y) {
		return game.getField(x, y);
	}

}
