package lukianol.tictactoe.gamestate;

import lukianol.tictactoe.Field;
import lukianol.tictactoe.GameBase;
import lukianol.tictactoe.Position;

public abstract class DiagFieldWinGameStateHandler extends GameStateHandlerBase {
	
	@Override
	protected final GameStateResult onHandleState(GameBase game){
		
		int playgroundSize = game.getPlaygroundSize();
		Position[] positions = new Position[playgroundSize];
		Field firstField = getCurrentField(game, 0, 0);
		positions[0] = firstField.getPosition();
		Boolean rightStroke = firstField.hasStroke();
		
		if (rightStroke) {
			for(int c=0; c < playgroundSize - 1 && rightStroke;c++)
				for(int r=0; r < playgroundSize - 1 && rightStroke;r++){
					if (c == r) {
						Field current = getCurrentField(game, c, r);
						Field next = getNextField(game, c, r);					
						rightStroke &= current.hasStroke() && (current.getStroke() == next.getStroke());
						positions[c+1] = next.getPosition();
					}
				}
			
			if (rightStroke)
				return new GameStateResult(firstField.getStroke(), positions);
		}
		 
		return GameStateResult.UndefinedResult;
	}
	
	protected abstract Field getNextField(GameBase game, int x, int y);
	protected abstract Field getCurrentField(GameBase game, int x, int y);
}
