package lukianol.tictactoe;

public interface GameEventListener {
	
	void onGameStateChanged(GameBase game, GameState gameState);
	void onCurrentStrokeChanged(GameBase game, StrokeKind stroke);
	void onFieldStroked(GameBase game, Field field);

}
