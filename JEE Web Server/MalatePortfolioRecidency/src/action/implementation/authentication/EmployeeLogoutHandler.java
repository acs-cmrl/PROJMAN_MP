package action.implementation.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionHandler;
import dao.DaoFactory;
import dao.implementation.AttendanceDao;
import dao.implementation.EmployeeDao;
import model.Employee;

public class EmployeeLogoutHandler implements ActionHandler{

	public static final String SCAN_LOGOUT_ACTION = "SCAN_LOGOUT";
	public static final String ID_PARAMETER = "EMPLOYEE_ID";
	
	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		EmployeeDao eDao = (EmployeeDao) DaoFactory.getInstance().getDataAccessObject(EmployeeDao.class);
		AttendanceDao aDao = (AttendanceDao) DaoFactory.getInstance().getDataAccessObject(AttendanceDao.class);
		
		Employee employee = null;
		
		// use face recog api here
		
		// if face recog api assigns a unique id to an employee use that to create and retrieve employees
		// in this case the unique id is '1'
		employee = eDao.select(1);
		
		// logout employee and update database
		aDao.logout(employee);

		response.sendRedirect(request.getContextPath() + "/index");
		return false;
	}
}