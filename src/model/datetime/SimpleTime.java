package model.datetime;

import java.time.Instant;
import java.time.LocalTime;

public class SimpleTime {

	private LocalTime time;
	
	public SimpleTime(int hour,int minute){
		setLocalTime(LocalTime.of(hour, minute));
	}
	public SimpleTime(int hour,int minute,int second){
		setLocalTime(LocalTime.of(hour, minute, second));
	}
	public SimpleTime(long unix){
		Instant i = Instant.ofEpochMilli(unix);
		setLocalTime(LocalTime.from(i));
	}
	public SimpleTime(){
		Instant i = Instant.ofEpochMilli(System.currentTimeMillis());
		setLocalTime(LocalTime.from(i));
	}
	public int getHour() {
		return getLocalTime().getHour();
	}
	public void setHour(int hour) {
		getLocalTime().withHour(hour);
	}
	public int getMinute() {
		return getLocalTime().getMinute();
	}
	public void setMinute(int minute) {
		getLocalTime().withMinute(minute);
	}
	public int getSecond() {
		return getLocalTime().getSecond();
	}
	public void setSecond(int second) {
		getLocalTime().withSecond(second);
	}
	@Override
	public String toString(){
		return ""+getLocalTime();
	}
	
	public boolean equals(int hour, int minute, int second){
		return hour == getHour() && minute == getMinute() && second == getSecond();
		
	}
	public boolean equals(SimpleTime time){
		return getHour() == time.getHour() && getMinute() == time.getMinute() && getSecond() == time.getSecond();
	}
	public LocalTime getLocalTime() {
		return time;
	}
	public void setLocalTime(LocalTime time) {
		this.time = time;
	}
}
