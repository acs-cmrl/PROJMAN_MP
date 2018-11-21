package model.datetime;

import java.time.*;
import java.sql.Date;
import java.sql.Timestamp;

public class SimpleDatetime {
	
	private LocalDateTime datetime;
	
	public SimpleDatetime(SimpleDate date, SimpleTime time){
		datetime = LocalDateTime.of(date.getLocalDate(), time.getLocalTime());
	}
	
	public SimpleDatetime(int year,int month,int day, int hour,int minute){
		datetime = LocalDateTime.of(year, month, day, hour, minute);
	}
	
	public SimpleDatetime(int year,int month,int day, int hour,int minute,int second){
		datetime = LocalDateTime.of(year, month, day, hour, minute, second);
	}
	
	public SimpleDatetime(long unix){
		Instant i = Instant.ofEpochMilli(unix);
		ZoneId zone = ZoneId.systemDefault();
		datetime = LocalDateTime.ofInstant(i, zone);
	}
	
	public SimpleDatetime(Timestamp timestamp){
		if(timestamp != null)
			datetime = timestamp.toLocalDateTime();
		else{
			ZoneId zone = ZoneId.systemDefault();
			datetime = LocalDateTime.now(zone);
			}
	}
	
	public SimpleDatetime(){
		ZoneId zone = ZoneId.systemDefault();
		datetime = LocalDateTime.now(zone);
	}
	
	public static SimpleDatetime generate(Timestamp timestamp){
		SimpleDatetime result = null;
		if(timestamp != null)
			result = new SimpleDatetime(timestamp);
		return result;
		
	}
	
	public int getYear() {
		return datetime.getYear();
	}
	public void setYear(int year) {
		datetime.withYear(year);
	}
	public int getMonth() {
		return datetime.getMonthValue();
	}
	public void setMonth(int month) {
		datetime.withMonth(month);
	}
	public int getDay() {
		return datetime.getDayOfMonth();
	}
	public void setDay(int day) {
		datetime.withDayOfMonth(day);
	}
	public int getHour() {
		return datetime.getHour();
	}
	public void setHour(int hour) {
		datetime.withHour(hour);
	}
	public int getMinute() {
		return datetime.getMinute();
	}
	public void setMinute(int minute) {
		datetime.withMinute(minute);
	}
	public int getSecond() {
		return datetime.getSecond();
	}
	public void setSecond(int second) {
		datetime.withSecond(second);
	}
	public SimpleDate getDate() {
		return new SimpleDate(datetime.getYear(),datetime.getMonthValue(),datetime.getDayOfMonth());
	}
	public void setDate(SimpleDate simpleDate) {
		datetime = LocalDateTime.of(simpleDate.getLocalDate(), datetime.toLocalTime());
	}
	public SimpleTime getTime() {
		return new SimpleTime(datetime.getHour(),datetime.getMinute(),datetime.getSecond());
	}
	public void setTime(SimpleTime simpleTime) {
		datetime = LocalDateTime.of(datetime.toLocalDate(), simpleTime.getLocalTime());
	}
	public boolean equals(SimpleDatetime datetime){
		return datetime.equals(datetime);
	}

	@Override
	public String toString(){
		return datetime.toLocalDate()+" "+datetime.toLocalTime(); 
	}
	
	public Date toDate(){ return Date.valueOf(datetime.toLocalDate()); }
	
	public Timestamp toTimestamp(){ return Timestamp.valueOf(datetime);}
}
