package lukianol.tictactoe.gamestate;

import lukianol.tictactoe.Field;
import lukianol.tictactoe.IGame;
import lukianol.tictactoe.Position;

public abstract class DirectFieldWinGameStateHandlerBase extends GameStateHandlerBase {
	
	@Override
	protected final GameStateResult onHandleState(IGame game){
		
		int playgroundSize = game.getPlaygroundSize();
		
		for(int x=0; x < playgroundSize; x++) {
			
			Field current = getCurrentField(game, x, 0);
			Boolean rightStroke = current.hasStroke();			
			if (rightStroke)
			{
				Position[] positions = new Position[playgroundSize];	
				positions[0] = current.getPosition();
				
				for(int y = 1; y < playgroundSize && rightStroke; y++) {		
					Field next = getCurrentField(game, x, y);
					rightStroke &= next.getStroke() == current.getStroke();	
					current = next;	
					positions[y] = current.getPosition();
				}
				
				if (rightStroke)
				{
					return new GameStateResult(current.getStroke(), positions);
				}
			}				
		}
		
		return GameStateResult.UndefinedResult;
	}
		
	protected abstract Field getCurrentField(IGame game, int x, int y);
}