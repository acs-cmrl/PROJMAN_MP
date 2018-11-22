package dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoResult;
import dao.GenericDao;
import database.DatabaseConnectionPool;
import database.Query;
import model.Employee;
import model.datetime.SimpleDate;

public class EmployeeDao implements GenericDao{
	
	public DaoResult insert(Employee employee) {
		DaoResult status = DaoResult.FAILURE;
		String sql;
		if(employee.getPhoto() != null)
			sql = "INSERT INTO "+Employee.TABLE+"("+Employee.COL_FIRSTNAME+", "+Employee.COL_LASTNAME+", "+Employee.COL_BOARDMEMBER+", "+Employee.COL_PHOTO+")"
				+ " VALUES (?, ?, ?, ?)";
		else
			sql = "INSERT INTO "+Employee.TABLE+"("+Employee.COL_FIRSTNAME+", "+Employee.COL_LASTNAME+", "+Employee.COL_BOARDMEMBER+")"
				+ " VALUES (?, ?, ?)";
		Connection conn = null;
		Query query = null;
		ArrayList<Object> input = new ArrayList<>();
		input.add(employee.getFirstname());
		input.add(employee.getLastname());
		input.add(employee.isBoardmember());
		if(employee.getPhoto() != null)
		input.add(employee.getPhoto());
		try {
			conn = DatabaseConnectionPool.getInstance().getConnection();
			query = new Query(conn);
			query.runInsertUpdateDelete(sql, input);
			status = DaoResult.SUCCESS;
		} catch (SQLException e) {
			e.printStackTrace();
			status = DaoResult.EXCEPTION;
		}finally{
			try {
				query.close(); // closes all query resources
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}
	
	public DaoResult update(Employee employee) {
		return update(employee.getEmployee_id(), employee.getFirstname(), employee.getLastname(), employee.isBoardmember(), employee.getPhoto());
	}
	
	public DaoResult update(Integer employee_id,
			String firstname,
			String lastname,
			Boolean boardmember,
			String photo) {
		DaoResult status = DaoResult.FAILURE;
		String sql = "UPDATE "+Employee.TABLE+" SET ";

		if(firstname != null) {
			sql += Employee.COL_FIRSTNAME + " = ?";
			sql += (lastname != null || boardmember != null || photo != null? ", " : "");
		}
		if(lastname != null) {
			sql += Employee.COL_LASTNAME + " = ?";
			sql += (boardmember != null || photo != null? ", " : "");
		}
		if(boardmember != null) {
			sql += Employee.COL_BOARDMEMBER + " = ?";
			sql += (photo != null? ", " : "");
		}
		if(photo != null) {
			sql += Employee.COL_PHOTO + " = ?";
		}
		sql += "WHERE "+Employee.COL_ID+" = ? ";
		Connection conn = null;
		Query query = null;
		ArrayList<Object> input = new ArrayList<>();
		if(firstname != null) {
		input.add(firstname);
		}
		if(lastname != null) {
		input.add(lastname);
		}
		if(boardmember != null) {
		input.add(boardmember);
		}
		if(photo != null) {
		input.add(photo);
		}
		input.add(employee_id);
		
		try {
			conn = DatabaseConnectionPool.getInstance().getConnection();
			query = new Query(conn);
			query.runInsertUpdateDelete(sql, input);
			status = DaoResult.SUCCESS;
		} catch (SQLException e) {
			e.printStackTrace();
			status = DaoResult.EXCEPTION;
		}finally{
			try {
				query.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}
	public DaoResult delete(int employee_id) {
		DaoResult status = DaoResult.FAILURE;
		String sql = "DELETE FROM "+Employee.TABLE+" WHERE "+Employee.COL_ID+" = ?;";
		Connection conn = null;
		Query query = null;
		ArrayList<Object> input = new ArrayList<>();
		input.add(employee_id);
		try {
			conn = DatabaseConnectionPool.getInstance().getConnection();
			query = new Query(conn);
			query.runInsertUpdateDelete(sql, input);
			status = DaoResult.SUCCESS;
		} catch (SQLException e) {
			e.printStackTrace();
			status = DaoResult.EXCEPTION;
		}finally{
			try {
				query.close(); // closes all query resources
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}
	public Employee select(int employee_id){
		String sql = "select * from "+Employee.TABLE+" WHERE "+Employee.COL_ID+" = ?;";
		Employee result = null;
		Connection conn = null;
		Query query = null;
		ResultSet r = null;
		ArrayList<Object> input = new ArrayList<>();
		input.add(employee_id);
		try {
			conn = DatabaseConnectionPool.getInstance().getConnection();
			query = new Query(conn);
			r = query.runQuery(sql, input);
			if(r.next()){
				result = new Employee(
						r.getInt(Employee.COL_ID),
						r.getString(Employee.COL_FIRSTNAME),
						r.getString(Employee.COL_LASTNAME),
						r.getBoolean(Employee.COL_BOARDMEMBER),
						r.getString(Employee.COL_PHOTO)
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				query.close(); // closes all query resources
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public List<Employee> search(
			String firstname,
			String lastname,
			Boolean boardmember){
		return null;
	}

}
