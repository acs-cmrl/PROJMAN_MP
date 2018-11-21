package controller.implementation;

import javax.servlet.http.HttpServletRequest;

import action.ActionFactory;
import action.implementation.authentication.TestScanHandler;
import controller.ActionControllerServlet;

public class AttendanceServlet extends ActionControllerServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6440270093682693011L;

	@Override
	protected void registerHandlers() {
	    //registerHandler(ActionControllerServlet.EMPTY_ACTION, ActionFactory.getInstance().getHandler(TestScanHandler.class));
	}

	  @Override
	  protected String getViewPath(HttpServletRequest request) {
	    return "/attendance.jsp";
	  }
	}