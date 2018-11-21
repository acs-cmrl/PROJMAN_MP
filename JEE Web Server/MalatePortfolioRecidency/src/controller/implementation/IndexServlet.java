package controller.implementation;

import javax.servlet.http.HttpServletRequest;

import action.ActionFactory;
import action.implementation.authentication.EmployeeLoginHandler;
import action.implementation.authentication.EmployeeLogoutHandler;
import controller.ActionControllerServlet;

public class IndexServlet extends ActionControllerServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1320073915319191756L;

	@Override
	protected void registerHandlers() {
	    //registerHandler(ActionControllerServlet.EMPTY_ACTION, ActionFactory.getInstance().getHandler(TestScanHandler.class));
	    registerHandler(EmployeeLoginHandler.SCAN_LOGIN_ACTION, ActionFactory.getInstance().getHandler(EmployeeLoginHandler.class));
	    registerHandler(EmployeeLogoutHandler.SCAN_LOGOUT_ACTION, ActionFactory.getInstance().getHandler(EmployeeLogoutHandler.class));
	}

	  @Override
	  protected String getViewPath(HttpServletRequest request) {
	    return "/index.jsp";
	  }
	}