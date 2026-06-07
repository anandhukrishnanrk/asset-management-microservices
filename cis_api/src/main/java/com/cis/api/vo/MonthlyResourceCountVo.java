package com.cis.api.vo;

public class MonthlyResourceCountVo {
	
    private int year;
    private int month;       // 1-12
    private String monthName; // "Jan", "Feb", etc.
    private long count;

    public MonthlyResourceCountVo(int year, int month, long count) {
        this.year = year;
        this.month = month;
        this.count = count;
        this.monthName = java.time.Month.of(month)
                             .getDisplayName(java.time.format.TextStyle.SHORT, 
                                             java.util.Locale.ENGLISH);
    }

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
