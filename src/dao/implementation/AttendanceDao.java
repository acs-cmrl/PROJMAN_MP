package dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.DaoResult;
import dao.GenericDao;
import database.DatabaseConnectionPool;
import database.Query;
import model.Attendance;
import model.Employee;
import model.datetime.SimpleDatetime;

public class AttendanceDao implements GenericDao{
	
	public boolean login(Employee employee) {
		if(employee == null)
			return false;
		boolean success = true;
		String sql = "INSERT INTO "+ Attendance.TABLE +"("
				+ Attendance.COL_EMPLOYEE_ID + ")"
				+ " VALUES (?)";
		Connection conn = null;
		Query query = null;
		ArrayList<Object> input = new ArrayList<>();
		input.add(employee.getEmployee_id());
		try {
			conn = DatabaseConnectionPool.getInstance().getConnection();
			query = new Query(conn);
			query.runInsertUpdateDelete(sql, input);
		} catch (SQLException e) {
			e.printStackTrace();
			success = false;
		}finally{
			try {
				query.close(); // closes all query resources
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}
	public boolean logout(Employee employee) {
		if(employee == null)
			return false;
		boolean success = false;
		
		// get latest login
		Attendance latest = latestAttendance(employee);
		
		if(latest != null)
			if(latest.getTime_end() == null) {
		
		String sql = "UPDATE "+Attendance.TABLE+" SET ";
		sql += Attendance.COL_TIME_END + " = CURRENT_TIMESTAMP ";
		sql += "WHERE "+Attendance.COL_ID+" = ? ;";
		Connection conn = null;
		Query query = null;
		ArrayList<Object> input = new ArrayList<>();
		input.add(latest.getAttendance_id());
		try {
			conn = DatabaseConnectionPool.getInstance().getConnection();
			query = new Query(conn);
			query.runInsertUpdateDelete(sql, input);
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				query.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			}
		latest = select(latest.getAttendance_id());
		Integer totalMinutes = (int) compareTwoTimeStamps(latest.getTime_end(), latest.getTime_start());
		update(latest.getAttendance_id(), null, null, null, totalMinutes);
		return success;
	}
	public Attendance latestAttendance(Employee employee){
		String sql = "select * from "+Attendance.TABLE+" WHERE "+Attendance.COL_EMPLOYEE_ID+" = ?  order by "+Attendance.COL_TIME_START+" desc;";
		Attendance result = null;
		Connection conn = null;
		Query query = null;
		ResultSet r = null;
		ArrayList<Object> input = new ArrayList<>();
		input.add(employee.getEmployee_id());
		try {
			conn = DatabaseConnectionPool.getInstance().getConnection();
			query = new Query(conn);
			r = query.runQuery(sql, input);
			if(r.next()){
				result = new Attendance(
						r.getInt(Attendance.COL_ID),
						r.getInt(Attendance.COL_EMPLOYEE_ID),
						r.getTimestamp(Attendance.COL_TIME_START),
						r.getTimestamp(Attendance.COL_TIME_END),
						r.getInt(Attendance.COL_COMPLETED_MINUTES)
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
	public List<Attendance> attendanceByEmployee(Employee employee) {
		List<Attendance> list = new ArrayList<>();
		
		return list;
	}
	public DaoResult insert(Attendance attendance) {
		DaoResult status = DaoResult.FAILURE;
		String sql = "INSERT INTO "+ Attendance.TABLE +"("
				+ Attendance.COL_EMPLOYEE_ID + ", "
				+ Attendance.COL_TIME_START + ", "
				+ (attendance.getTime_end() != null?Attendance.COL_TIME_END + ", ":"")
				+ Attendance.COL_COMPLETED_MINUTES + ")"
				+ " VALUES (?, ?, ?, ?)";
		Connection conn = null;
		Query query = null;
		ArrayList<Object> input = new ArrayList<>();
		input.add(attendance.getEmployee_id());
		input.add(attendance.getTime_start());
		if(attendance.getTime_end() != null)
		input.add(attendance.getTime_end());
		input.add(attendance.getCompleted_minutes());
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
	public DaoResult update(Integer attendance_id,
			Integer employee_id,
			Timestamp time_start,
			Timestamp time_end,
			Integer completed_minutes) {
		DaoResult status = DaoResult.FAILURE;
		String sql = "UPDATE "+Attendance.TABLE+" SET ";

		if(employee_id != null) {
			sql += Attendance.COL_EMPLOYEE_ID + " = ? ";
			sql += (time_start != null || time_end != null || completed_minutes != null? ", " : "");
		}
		if(time_start != null) {
			sql += Attendance.COL_TIME_START + " = ? ";
			sql += (time_end != null || completed_minutes != null? ", " : "");
		}
		if(time_end != null) {
			sql += Attendance.COL_TIME_END + " = ? ";
			sql += (completed_minutes != null? ", " : "");
		}
		if(completed_minutes != null) {
			sql += Attendance.COL_COMPLETED_MINUTES + " = ? ";
		}
		sql += "WHERE "+Attendance.COL_ID+" = ? ";
		Connection conn = null;
		Query query = null;
		ArrayList<Object> input = new ArrayList<>();
		if(employee_id != null) {
		input.add(employee_id);
		}
		if(time_start != null) {
		input.add(time_start);
		}
		if(time_end != null) {
		input.add(time_end);
		}
		if(completed_minutes != null) {
		input.add(completed_minutes);
		}
		input.add(attendance_id);
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
	public DaoResult delete(int attendance_id) {
		DaoResult status = DaoResult.FAILURE;
		String sql = "DELETE FROM "+Attendance.TABLE+" WHERE "+Attendance.COL_ID+" = ?;";
		Connection conn = null;
		Query query = null;
		ArrayList<Object> input = new ArrayList<>();
		input.add(attendance_id);
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
	public Attendance select(int attendance_id){
		String sql = "select * from "+Attendance.TABLE+" WHERE "+Attendance.COL_ID+" = ?  order by "+Attendance.COL_TIME_START+" desc;";
		Attendance result = null;
		Connection conn = null;
		Query query = null;
		ResultSet r = null;
		ArrayList<Object> input = new ArrayList<>();
		input.add(attendance_id);
		try {
			conn = DatabaseConnectionPool.getInstance().getConnection();
			query = new Query(conn);
			r = query.runQuery(sql, input);
			if(r.next()){
				result = new Attendance(
						r.getInt(Attendance.COL_ID),
						r.getInt(Attendance.COL_EMPLOYEE_ID),
						r.getTimestamp(Attendance.COL_TIME_START),
						r.getTimestamp(Attendance.COL_TIME_END),
						r.getInt(Attendance.COL_COMPLETED_MINUTES)
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
	public List<Attendance> search(
			String firstname,
			String lastname,
			Boolean boardmember){
		return null;
	}
	public static long compareTwoTimeStamps(Timestamp currentTime, Timestamp oldTime)
	{
	    long milliseconds1 = oldTime.getTime();
	  long milliseconds2 = currentTime.getTime();

	  long diff = milliseconds2 - milliseconds1;
	  long diffSeconds = diff / 1000;
	  long diffMinutes = diff / (60 * 1000);
	  long diffHours = diff / (60 * 60 * 1000);
	  long diffDays = diff / (24 * 60 * 60 * 1000);

	    return diffMinutes;
	}
}