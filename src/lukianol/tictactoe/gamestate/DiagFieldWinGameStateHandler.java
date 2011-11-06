package lukianol.tictactoe.gamestate;

import lukianol.tictactoe.Field;
import lukianol.tictactoe.IGame;

public abstract class DiagFieldWinGameStateHandler extends GameStateHandlerBase {
	
	@Override
	protected final GameStateResult onHandleState(IGame game){
		
		int playgroundSize = game.getPlaygroundSize();
		Field[] fields = new Field[playgroundSize];		
		fields[0] = getCurrentField(game, 0, 0);
		Boolean rightStroke = fields[0].hasStroke();
		
		if (rightStroke) {
			for(int c=0; c < playgroundSize - 1 && rightStroke;c++)
				for(int r=0; r < playgroundSize - 1 && rightStroke;r++){
					if (c == r) {
						Field current = getCurrentField(game, c, r);
						Field next = getNextField(game, c, r);					
						rightStroke &= current.hasStroke() && (current.getStroke() == next.getStroke());
						fields[c+1] = next;
					}
				}
			
			if (rightStroke)
				return new GameStateResult(fields[0].getStroke(), fields);
		}
		 
		return GameStateResult.UndefinedResult;
	}
	
	protected abstract Field getNextField(IGame game, int x, int y);
	protected abstract Field getCurrentField(IGame game, int x, int y);
}
