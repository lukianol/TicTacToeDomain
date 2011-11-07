package lukianol.tictactoe;

public final class Position {
	
	public Position(int column, int row) {
		this.column = column;
		this.row = row;
	}

	public int getColumn() {
		return column;
	}
	
	public int getRow() {
		return row;
	}
	
	public final static Position Create(int column, int row){
		return new Position(column, row);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null)
			return false;
		
		if (!(obj instanceof Position))
			return false;
		
		Position casted = (Position)obj;
		
		return ((casted.column == column) && (casted.row == row));
	}

	@Override
	public int hashCode() {
		return column ^ 27 + row;
	}

	@Override
	public String toString() {
		return String.format("%d : %d", column, row);
	}
	
	private int column;
	private int row;
}