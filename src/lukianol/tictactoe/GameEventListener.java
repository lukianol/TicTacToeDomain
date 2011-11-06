package lukianol.tictactoe;

public interface GameEventListener {
	
	void GameStateChanged(IGame game, GameState gameState);
	void CurrentStrokeChanged(IGame game, StrokeKind stroke);

}
