package com.widyatama.nurseassistant.data.model;

public class Day {
	
	private int mYear;
	private int mMonth;
	private int mDay;
	
	public Day(int year, int month, int day){
		this.mYear = year;
		this.mMonth = month;
		this.mDay = day;
	}
	
	public int getMonth(){
		return mMonth;
	}
	
	public int getYear(){
		return mYear;
	}
	
	public int getDay(){
		return mDay;
	}

}
