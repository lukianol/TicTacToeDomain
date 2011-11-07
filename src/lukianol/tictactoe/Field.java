package lukianol.tictactoe;

import lukianol.tictactoe.resources.Exceptions;

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
						
		if (hasStroke())
			throw new TicTacToeException(Exceptions.string(Exceptions.FIELD_ALREADY_STROKED));
		
		_stroke = value;
	}
	
	public Boolean hasStroke(){
		return getStroke() != null;
	}
	
	final private Position _position;
	private StrokeKind _stroke; 

}
