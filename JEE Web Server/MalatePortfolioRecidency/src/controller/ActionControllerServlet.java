package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionHandler;

public abstract class ActionControllerServlet extends HttpServlet {

/**
	 * 
	 */
	private static final long serialVersionUID = 4430617450771459547L;

public static final String ACTION = "ACTION";

  public static final String EMPTY_ACTION = "";

  private Map<String, ActionHandler> handlers;

  public ActionControllerServlet() {
    this.handlers = new HashMap<String, ActionHandler>();
  }

  @Override
  public void init() throws ServletException {
    super.init();
    registerHandlers();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    process(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    process(request, response);
  }

  protected void process(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
	boolean autoRedirect = true;
    try {
      String actionName = getAction(request);
      ActionHandler handler = this.handlers.get(actionName);
      System.out.println("Action: ["+actionName+"]");
      autoRedirect = handler.execute(request, response);

    } catch (Exception e) {
      handleException(request, response);
    }
    if(autoRedirect)
    	pushToView(request, response);
  }

  protected void registerHandler(String actionName, ActionHandler handler) {
    this.handlers.put(actionName, handler);
  }

  protected String getAction(HttpServletRequest request) {
    String action = request.getParameter(ACTION);
    return action == null ? EMPTY_ACTION : action;
  }

  protected void pushToView(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(getViewPath(request)).forward(request, response);
  }

  protected abstract void registerHandlers();

  protected abstract String getViewPath(HttpServletRequest request);

  protected void handleException(HttpServletRequest request,
      HttpServletResponse response) {
  }



}
