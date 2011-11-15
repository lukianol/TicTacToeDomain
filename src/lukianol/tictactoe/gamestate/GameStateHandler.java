package lukianol.tictactoe.gamestate;

import lukianol.tictactoe.GameBase;

public interface GameStateHandler {
	
	GameStateResult handleState(GameBase game);
	
}
