package lukianol.tictactoe;

public abstract class GameBase {
	
	public static final GameBase Null = new NullGame();

	public abstract void Stroke(Position position) throws TicTacToeException;

	public abstract StrokeKind getCurrentStroke();

	public abstract GameState getGameState();

	public abstract void addGameEventListener(GameEventListener listener);

	public abstract void removeGameEventListener(GameEventListener listener);

	public abstract Field getField(Position position);

	public abstract int getPlaygroundSize();

	public abstract Field getField(int column, int row);
	
	public abstract Boolean isPlaying();

	public abstract Position[] getWonPositions();
	
	public abstract void dispose();

}