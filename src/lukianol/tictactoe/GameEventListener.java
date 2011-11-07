package lukianol.tictactoe;

public interface GameEventListener {
	
	void onGameStateChanged(IGame game, GameState gameState);
	void onCurrentStrokeChanged(IGame game, StrokeKind stroke);
	void onFieldStroked(IGame game, Field field);

}
