package lukianol.tictactoe.gamestate;

import lukianol.tictactoe.Field;
import lukianol.tictactoe.GameBase;

public final class NeDiagWinGameStateHandler extends DiagFieldWinGameStateHandler {
		
	private final static int InvertCoord(GameBase game, int coord){
		return game.getPlaygroundSize() - 1 - coord;
	}

	@Override
	protected Field getNextField(GameBase game, int x, int y) {
		return game.getField(InvertCoord(game, x) - 1, y + 1);
	}

	@Override
	protected Field getCurrentField(GameBase game, int x, int y) {
		return game.getField(InvertCoord(game, x), y);
	}

}
