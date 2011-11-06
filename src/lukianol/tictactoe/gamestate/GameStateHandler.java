package lukianol.tictactoe.gamestate;

import lukianol.tictactoe.IGame;

public interface GameStateHandler {
	
	GameStateResult handleState(IGame game);
	
}
