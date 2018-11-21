package utils;

public class Debug {
	
	private Debug(){}
	
	public static void log(String tag, String message) {
			System.out.println("["+tag+"]: " + message);
	}
	
	public static void error(String tag, String message) {
			System.err.println("["+tag+"]: " + message);
	}

}
