package controller.implementation;

import javax.servlet.http.HttpServletRequest;

import action.ActionFactory;
import action.implementation.authentication.TestScanHandler;
import controller.ActionControllerServlet;

public class EmployeeServlet extends ActionControllerServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6546530298336025449L;

	@Override
	protected void registerHandlers() {
	    //registerHandler(ActionControllerServlet.EMPTY_ACTION, ActionFactory.getInstance().getHandler(TestScanHandler.class));
	}

	  @Override
	  protected String getViewPath(HttpServletRequest request) {
	    return "/employee.jsp";
	  }
	}
