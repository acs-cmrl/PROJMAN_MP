package model.datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

import utils.Debug;

import java.sql.Date;
import java.sql.Timestamp;

public class SimpleDate {

	private LocalDate date;
	
	public SimpleDate(int year,int month,int day){
		setLocalDate(LocalDate.of(year, month, day));
	}
	
	public SimpleDate(long unix){
		Instant i = Instant.ofEpochMilli(unix);
		setLocalDate(LocalDate.from(i));
	}
	
	public SimpleDate(Date date){
		if(date != null)
			this.date = date.toLocalDate();
		else
			this.date = LocalDate.now();
	}
	
	public SimpleDate(LocalDate localDate){
		this.date = localDate;
	}

	public SimpleDate(){
		Instant i = Instant.ofEpochMilli(System.currentTimeMillis());
		setLocalDate(LocalDateTime.ofInstant(i, ZoneOffset.systemDefault()).toLocalDate());
	}
	
	public static SimpleDate generate(Date date){
		SimpleDate result = null;
		if(date != null)
			result = new SimpleDate(date);
		return result;
		
	}
	
	public static SimpleDate generate(String date){
		SimpleDate result = null;
		LocalDate localDate = null;
		if(date != null)
		try {
		localDate = LocalDate.parse(date);
		result = new SimpleDate(localDate);
		} catch (DateTimeParseException e) {
			Debug.error("SimpleDate.generate", e.getMessage());
		}
		return result;
		
	}
	
	public int getYear() {
		return getLocalDate().getYear();
	}
	public void setYear(int year) {
		getLocalDate().withYear(year);
	}
	public int getMonth() {
		return getLocalDate().getMonthValue();
	}
	public void setMonth(int month) {
		getLocalDate().withMonth(month);
	}
	public int getDay() {
		return getLocalDate().getDayOfMonth();
	}
	public void setDay(int day) {
		getLocalDate().withDayOfMonth(day);
	}
	
	@Override
	public String toString(){
		return ""+getLocalDate();
	}
	
	public boolean equals(int year, int month, int day){
		return getYear() == year && getMonth() == month && getDay() == day;
	}
	
	public boolean equals(SimpleDate date){
		return date.getYear() == getYear() && date.getMonth() == getMonth() && date.getDay() == getDay();
	}

	public LocalDate getLocalDate() {
		return date;
	}

	public void setLocalDate(LocalDate date) {
		this.date = date;
	}
	
	public Date toDate(){ return Date.valueOf(date); }
	
}
