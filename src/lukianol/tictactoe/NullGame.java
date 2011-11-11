package lukianol.tictactoe;

public final class NullGame implements IGame {
	
	public static final IGame Instance = new NullGame();
	
	private NullGame(){
		
	}
	
	public void Stroke(Position position) throws TicTacToeException {
		
	}

	public StrokeKind getCurrentStroke() {
		return null;
	}

	public GameState getGameState() {
		return null;
	}

	public void addGameEventListener(GameEventListener listener) {
		
	}

	public void removeGameEventListener(GameEventListener listener) {
		
	}

	public Field getField(Position position) {
		return null;
	}

	public int getPlaygroundSize() {
		return 0;
	}

	public Field getField(int column, int row) {
		return null;
	}

	public Boolean isPlaying() {
		return false;
	}

	public Position[] getWonPositions() {
		return null;
	}

	public void dispose() {
		
	}

}
