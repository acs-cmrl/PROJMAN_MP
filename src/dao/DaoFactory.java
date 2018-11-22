package dao;

import java.util.HashMap;

import dao.GenericDao;
import utils.Debug;

public class DaoFactory {

	private static DaoFactory instance = null;
	private HashMap<Class<?>, GenericDao> map;
	
	private DaoFactory(){
		map = new HashMap<>();
	}
	
	public static DaoFactory getInstance(){ return instance == null? (instance = new DaoFactory()): instance;}
	
	public GenericDao getDataAccessObject(Class<?> clazz){
		if(map.containsKey(clazz))
			return map.get(clazz);
		GenericDao result = null;
		try {
			result = (GenericDao) clazz.newInstance();
			map.put(clazz, result);
		} catch (InstantiationException e) {
			Debug.log("DaoFactory.getDataAccessObject", e.getMessage());
			//e.printStackTrace();
		} catch ( IllegalAccessException e) {
			Debug.log("DaoFactory.getDataAccessObject", e.getMessage());
			//e.printStackTrace();
		}
		return result;
	}
}
