package model;

import java.sql.Timestamp;

public class Attendance {
	public static final String TABLE = "attendance";
	public static final String COL_ID = "attendance_id";
	public static final String COL_EMPLOYEE_ID = "employee_id";
	public static final String COL_TIME_START = "time_start";
	public static final String COL_TIME_END = "time_end";
	public static final String COL_COMPLETED_MINUTES = "completed_minutes";
	
	private Integer attendance_id;
	private Integer employee_id;
	private Timestamp time_start;
	private Timestamp time_end;
	private Integer completed_minutes;
	
	public Attendance(
			Integer attendance_id,
			Integer employee_id,
			Timestamp time_start,
			Timestamp time_end,
			Integer completed_minutes) {
		setAttendance_id(attendance_id);
		setEmployee_id(employee_id);
		setTime_start(time_start);
		setTime_end(time_end);
		setCompleted_minutes(completed_minutes);
	}

	public Integer getAttendance_id() {
		return attendance_id;
	}

	public void setAttendance_id(Integer attendance_id) {
		this.attendance_id = attendance_id;
	}

	public Integer getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}

	public Timestamp getTime_start() {
		return time_start;
	}

	public void setTime_start(Timestamp time_start) {
		this.time_start = time_start;
	}

	public Timestamp getTime_end() {
		return time_end;
	}

	public void setTime_end(Timestamp time_end) {
		this.time_end = time_end;
	}

	public Integer getCompleted_minutes() {
		return completed_minutes;
	}

	public void setCompleted_minutes(Integer completed_minutes) {
		this.completed_minutes = completed_minutes;
	}
}
