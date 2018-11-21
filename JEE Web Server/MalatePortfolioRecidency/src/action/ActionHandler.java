package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionHandler {
	
	public boolean execute(HttpServletRequest request, HttpServletResponse response)
		      throws Exception;
}
