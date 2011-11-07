package lukianol.tictactoe;

import java.util.ArrayList;

import lukianol.tictactoe.gamestate.DefaultGameStateHandler;
import lukianol.tictactoe.gamestate.GameStateHandler;
import lukianol.tictactoe.gamestate.GameStateResult;
import lukianol.tictactoe.resources.Exceptions;

public final class Game implements IGame {
	
	public final static int DefaultPlaygroundSize = 3;
	
	public Game(){
		this(null, DefaultPlaygroundSize);
	}
	
	public Game(GameEventListener listener){
		this(listener, DefaultPlaygroundSize);
	}
	
	public Game(GameEventListener listener, int playgroundSize){
		
		if (playgroundSize <= 0)
			throw new IllegalArgumentException(Exceptions.string(Exceptions.PLAYGROUND_SIZE_GT_0));
		
		_playgroundSize = playgroundSize;
		addGameEventListener(listener);			
		initFields();
		setGameState(GameState.Playing);
		setCurrentStroke(StrokeKind.X);
	}
		
	public void Stroke(Position position) throws TicTacToeException {
		
		if (!isPlaying())
			throw new TicTacToeException(String.format(Exceptions.string(Exceptions.CAN_NOT_STROKE_F), getGameState()));
		
		Field field = getField(position);
		field = getField(position);
		field.setStroke(getCurrentStroke());
		invokeFieldStroked(field);		
		
		GameStateResult result = _stateHandler.handleState(this);
		GameState newState = result.getGameState();
		
		if (newState == GameState.Playing)
			swapStroke();
		else 
		{
			if (newState == GameState.Won)
				_wonPositions = result.getWonPositions();
			
			setGameState(newState);
		}
	}

	public Position[] getWonPositions(){
		return _wonPositions;
	}
		
	public StrokeKind getCurrentStroke(){
		return _currentStroke;
	}
	
	public GameState getGameState(){
		return _gameState;
	}
		
	public void addGameEventListener(GameEventListener listener){
		if ((listener != null) && !_gameEventListeners.contains(listener))
			_gameEventListeners.add(listener);
	}
	
	public void removeGameEventListener(GameEventListener listener){
		if ((listener != null) &&_gameEventListeners.contains(listener))
			_gameEventListeners.remove(listener);
	}

	public Field getField(Position position){
		return getField(position.getColumn(), position.getRow());
	}
	
	public int getPlaygroundSize(){
		return this._playgroundSize;
	}

	public Boolean isPlaying() {
		return this.getGameState() == GameState.Playing;
	}
	
	public Field getField(int column, int row){
		return _fields[column][row];
	}

	public void dispose(){
		_gameEventListeners.clear();
	}
	
	private void invokeGameEventListeners(Invoke invoker){
		for(GameEventListener listener : _gameEventListeners){
			invoker.Action(listener);
		}			
	}
		
	private void invokeGameStateChanged(final GameState gameState){
		invokeGameEventListeners(new Invoke(){
			public void Action(GameEventListener listener) {
				listener.onGameStateChanged(_this, gameState);				
			}
		});
	}
		
	private void invokeCurrentStrokeChanged(final StrokeKind strokeKind){
		invokeGameEventListeners(new Invoke(){
			public void Action(GameEventListener listener) {
				listener.onCurrentStrokeChanged(_this, strokeKind);				
			}
		});
	}
	
	private void invokeFieldStroked(final Field field){
		invokeGameEventListeners(new Invoke(){
			public void Action(GameEventListener listener) {
				listener.onFieldStroked(_this, field);				
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
	
	private void swapStroke(){
		
		StrokeKind stroke = getCurrentStroke();
	
		if (stroke == null)
			throw new IllegalStateException(Exceptions.string(Exceptions.STROKE_NOT_INIT));
		
		switch(stroke){
		case X:
			stroke = StrokeKind.O;
			break;
		case O:
			stroke = StrokeKind.X;
			break;
		default:
			throw new UnsupportedOperationException(String.format(Exceptions.string(Exceptions.STROKEKIND_UNSUPPORTED_F), stroke));
		}
		
		setCurrentStroke(stroke);	
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
	private Position[] _wonPositions;
	
	private interface Invoke{
		void Action(GameEventListener listener);
	}

		
}
