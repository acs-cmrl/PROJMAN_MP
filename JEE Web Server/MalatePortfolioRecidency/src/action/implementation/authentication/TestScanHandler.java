package action.implementation.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.ActionHandler;
import dao.DaoFactory;
import dao.implementation.AttendanceDao;
import dao.implementation.EmployeeDao;
import model.Employee;

public class TestScanHandler implements ActionHandler{
	
	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		EmployeeDao eDao = (EmployeeDao) DaoFactory.getInstance().getDataAccessObject(EmployeeDao.class);
		AttendanceDao aDao = (AttendanceDao) DaoFactory.getInstance().getDataAccessObject(AttendanceDao.class);
		
		Employee employee = null;
		//eDao.insert(new Employee(1, "FIRST", "TEST", false, null));
		//eDao.delete(11);
		aDao.logout(eDao.select(1));
		//ActionFactory.getInstance().getHandler(RetrieveMaterialListHandler.class).execute(request, response);
		response.sendRedirect(request.getContextPath() + "/index");
	    //request.getRequestDispatcher("/index.jsp").forward(request, response);
		return false;
	}
}