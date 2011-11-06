package lukianol.tictactoe.gamestate;

import lukianol.tictactoe.Field;
import lukianol.tictactoe.IGame;

public final class NeDiagWinGameStateHandler extends DiagFieldWinGameStateHandler {
		
	private final static int InvertCoord(IGame game, int coord){
		return game.getPlaygroundSize() - 1 - coord;
	}

	@Override
	protected Field getNextField(IGame game, int x, int y) {
		return game.getField(InvertCoord(game, x) - 1, y + 1);
	}

	@Override
	protected Field getCurrentField(IGame game, int x, int y) {
		return game.getField(InvertCoord(game, x), y);
	}

}
