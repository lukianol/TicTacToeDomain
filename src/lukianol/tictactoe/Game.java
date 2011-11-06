package lukianol.tictactoe;

import java.util.ArrayList;

import lukianol.tictactoe.gamestate.DefaultGameStateHandler;
import lukianol.tictactoe.gamestate.GameStateHandler;
import lukianol.tictactoe.gamestate.GameStateResult;

public class Game implements IGame {
	
	public Game(GameEventListener listener, int playgroundSize){
		
		if (playgroundSize <= 0)
			throw new IllegalArgumentException("Playground size must be more than 0");
		
		_playgroundSize = playgroundSize;
		addGameEventListener(listener);			
		initFields();
		setGameState(GameState.Playing);
		setCurrentStroke(StrokeKind.X);
	}
	
	/* (non-Javadoc)
	 * @see lukianol.tictactoe.IGame#Stroke(lukianol.tictactoe.Position)
	 */
	public void Stroke(Position position) throws TicTacToeException{
		
		if (!isPlaying())
			throw new TicTacToeException(String.format("You can not stroke because the game state is '%s'", getGameState()));
		
		getField(position).setStroke(getCurrentStroke());
		
		GameStateResult result = _stateHandler.handleState(this);
		GameState newState = result.getGameState();
		
		if (newState == GameState.Playing)
			SwapStroke();
		else 
		{
			if (newState == GameState.Won)
				_wonFields = result.getWonFields();
			
			setGameState(newState);
		}
	}

	public Field[] getWonFields(){
		return _wonFields;
	}
	
	/* (non-Javadoc)
	 * @see lukianol.tictactoe.IGame#getCurrentStroke()
	 */
	public StrokeKind getCurrentStroke(){
		return _currentStroke;
	}

	/* (non-Javadoc)
	 * @see lukianol.tictactoe.IGame#getGameState()
	 */
	public GameState getGameState(){
		return _gameState;
	}
	
	/* (non-Javadoc)
	 * @see lukianol.tictactoe.IGame#addGameEventListener(lukianol.tictactoe.GameEventListener)
	 */
	public void addGameEventListener(GameEventListener listener){
		if ((listener != null) && !_gameEventListeners.contains(listener))
			_gameEventListeners.add(listener);
	}
	
	/* (non-Javadoc)
	 * @see lukianol.tictactoe.IGame#removeGameEventListener(lukianol.tictactoe.GameEventListener)
	 */
	public void removeGameEventListener(GameEventListener listener){
		if ((listener != null) &&_gameEventListeners.contains(listener))
			_gameEventListeners.remove(listener);
	}

	/* (non-Javadoc)
	 * @see lukianol.tictactoe.IGame#getField(lukianol.tictactoe.Position)
	 */
	public Field getField(Position position){
		return getField(position.getColumn(), position.getRow());
	}

	/* (non-Javadoc)
	 * @see lukianol.tictactoe.IGame#getPlaygroundSize()
	 */
	public int getPlaygroundSize(){
		return this._playgroundSize;
	}
	
	/* (non-Javadoc)
	 * @see lukianol.tictactoe.IGame#getField(int, int)
	 */
	public Field getField(int column, int row){
		return _fields[column][row];
	}
	
	private void invokeGameEventListeners(Invoke invoker){
		for(GameEventListener listener : _gameEventListeners){
			invoker.Action(listener);
		}			
	}
	
	private void invokeGameStateChanged(final GameState gameState){
		invokeGameEventListeners(new Invoke(){
			public void Action(GameEventListener listener) {
				listener.GameStateChanged(_this, gameState);				
			}
		});
	}
		
	private void invokeCurrentStrokeChanged(final StrokeKind strokeKind){
		invokeGameEventListeners(new Invoke(){
			public void Action(GameEventListener listener) {
				listener.CurrentStrokeChanged(_this, strokeKind);				
			}
		});
	}
		
	private void setCurrentStroke(StrokeKind value){
		_currentStroke = value;
		invokeCurrentStrokeChanged(_currentStroke);
	}
	
	private void setGameState(GameState value){
		_gameState = value;
		invokeGameStateChanged(_gameState);
	}
	
	private void SwapStroke(){
		StrokeKind stroke = getCurrentStroke();
	
		if (stroke == null)
			throw new IllegalStateException("Stroke has to be initialized already");
		
		switch(stroke){
		case X:
			setCurrentStroke(StrokeKind.O);
			break;
		case O:
			setCurrentStroke(StrokeKind.X);
			break;
		default:
			throw new UnsupportedOperationException("Only O's and X's are supported");
		}
	
	}
	
	private void initFields(){
		
		_fields = new Field[_playgroundSize][_playgroundSize];
		
		for(int c = 0; c < _playgroundSize; c++){
			for (int r = 0; r < _playgroundSize; r++){
				Position position = new Position(c, r); 
				initField(position);
				
			}
		}
	}
		
	private void initField(Position position){
		_fields[position.getColumn()][position.getRow()] = new Field(position);
	}

	private StrokeKind _currentStroke;
	private GameState _gameState;
	private Field[][] _fields;
	private final ArrayList<GameEventListener> _gameEventListeners = new ArrayList<GameEventListener>();
	private final IGame _this = this;
	private final int _playgroundSize;
	private final GameStateHandler _stateHandler = new DefaultGameStateHandler();
	private Field[] _wonFields;
	
	private interface Invoke{
		void Action(GameEventListener listener);
	}

	public Boolean isPlaying() {
		return this.getGameState() == GameState.Playing;
	}
		
}
