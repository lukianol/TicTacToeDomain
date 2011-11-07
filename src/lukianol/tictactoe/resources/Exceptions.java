package lukianol.tictactoe.resources;

import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class Exceptions extends ListResourceBundle {

	public final static String FIELD_ALREADY_STROKED = "FIELD_ALREADY_STROKED";
	public final static String PLAYGROUND_SIZE_GT_0 = "PLAYGROUND_SIZE_GT_0";
	public final static String CAN_NOT_STROKE_F = "CAN_NOT_STROKE";
	public final static String STROKE_NOT_INIT = "STROKE_NOT_INIT";
	public final static String STROKEKIND_UNSUPPORTED_F = "STROKEKIND_UNSUPPORTED_F";
	public final static String GAMERESULT_DIFFERENT_CTOR = "GAMERESULT_DIFFERENT_CTOR";
	
	public final static ResourceBundle getManager(){
		
		if (_manager == null){
			_manager = ResourceBundle.getBundle(Exceptions.class.getName());
		}
		
		return _manager;
	}

	public final static String string(String key){
		return getManager().getString(key);
	}

	@Override
	protected Object[][] getContents() {
		return new Object[][]{
				{FIELD_ALREADY_STROKED, "The field has been already stroked"},
				{PLAYGROUND_SIZE_GT_0, "Playground size must be more than 0"},
				{CAN_NOT_STROKE_F,"You can not stroke because the game state is '%s'"},
				{STROKE_NOT_INIT, "The current stroke has not been initialized yet"},
				{STROKEKIND_UNSUPPORTED_F, "This StrokeKind of '%s' is not supported"},
				{GAMERESULT_DIFFERENT_CTOR,"Use a constructor with a winnerStroke instead"}
		};
	}
	
	private static ResourceBundle _manager;
	
}
