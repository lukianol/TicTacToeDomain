package lukianol.tictactoe.gamestate;

import lukianol.tictactoe.GameState;
import lukianol.tictactoe.GameBase;

public final class PlayingOrDrawnGameStateHandler implements GameStateHandler {

	public GameStateResult handleState(GameBase game) {
		
		int playgroundSize = game.getPlaygroundSize();
		
		for(int c = 0; c < playgroundSize; c++)
			for(int r = 0; r < playgroundSize; r++)
				if (!game.getField(c, r).hasStroke())
					return new GameStateResult(GameState.Playing);
		
		return new GameStateResult(GameState.Drawn);
	}

}
