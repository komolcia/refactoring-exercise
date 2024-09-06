package com.checkr.interviews;

public enum FundingRaisedEnum {
	PERMALINK(0, "permalink"),
	COMPANY_NAME(1, "company_name"),
	NUMBER_EMPLOYEES(2, "number_employees"),
	CATEGORY(3, "category"),
	CITY(4, "city"),
	STATE(5, "state"),
	FUNDED_DATE(6, "funded_date"),
	RAISED_AMOUNT(7, "raised_amount"),
	RAISED_CURRENCY(8, "raised_currency"),
	ROUND(9, "round");

	private final int number;
	private final String name;

	FundingRaisedEnum(int number, String name) {
		this.number = number;
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}
}
