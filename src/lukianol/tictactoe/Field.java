package lukianol.tictactoe;

public final class Field {
	
	public Field(Position position){
		this._position = position;
		_stroke = null;
	}
	
	public Position getPosition(){
		return _position;
	}
	
	public StrokeKind getStroke(){
		return _stroke;
	}
	
	public void setStroke(StrokeKind value) throws TicTacToeException{
		if (_stroke != null)
			throw new TicTacToeException("The field has been already filled");
		
		_stroke = value;
	}
	
	public Boolean hasStroke(){
		return getStroke() != null;
	}
	
	final private Position _position;
	private StrokeKind _stroke; 

}
