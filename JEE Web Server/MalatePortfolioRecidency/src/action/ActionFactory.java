package action;

import java.util.HashMap;

import utils.Debug;

public class ActionFactory {

	private static ActionFactory instance = null;
	private HashMap<Class<?>, ActionHandler> map;
	
	private ActionFactory(){ 
		map = new HashMap<>(); 
		}
	
	public static ActionFactory getInstance(){ return instance == null? (instance = new ActionFactory()) : instance;}
	
	public ActionHandler getHandler(Class<?> clazz){
		if(map.containsKey(clazz))
			return map.get(clazz);
		ActionHandler result = null;
		try {
			result = (ActionHandler) clazz.newInstance();
			map.put(clazz, result);
		} catch (InstantiationException e) {
			Debug.error("ActionFactory.getHandler", e.getMessage());
			//e.printStackTrace();
		} catch ( IllegalAccessException e) {
			Debug.error("ActionFactory.getHandler", e.getMessage());
			//e.printStackTrace();
		}
		return result;
	}
	
}
