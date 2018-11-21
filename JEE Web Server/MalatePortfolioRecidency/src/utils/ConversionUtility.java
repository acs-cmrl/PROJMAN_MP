package utils;

import java.util.ArrayList;

public class ConversionUtility {

	private ConversionUtility(){}
	
	public static ArrayList<String> toArrayListString(String[] input){
		ArrayList<String> result = new ArrayList<>();
		
		for(String s : input)
			result.add(s);
		
		return result;
	}
	
	public static ArrayList<Integer> toArrayListInteger(String[] input){
		ArrayList<Integer> result = new ArrayList<>();
		
		for(String s : input){
			int i = -1;
			try{
				i = Integer.parseInt(s);
		    } catch(NumberFormatException e){
		    	Debug.error("ConversionUtility.toArrayListInteger", e.getMessage());
		    }
			if(i != -1)
				result.add(i);
		}
		
		return result;
	}
	
}
