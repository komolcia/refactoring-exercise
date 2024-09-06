package com.checkr.interviews;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class FundingRaisedServiceTest
		extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public FundingRaisedServiceTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(FundingRaisedServiceTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testSearchWhereFundingWasRaisedByParamsGivenCompany() {
		try {
			Map<String, String> options = new HashMap<String, String>();
			options.put("company_name", "Facebook");
			assertEquals(FundingRaisedService.searchWhereFundingWasRaisedByParams(options).size(), 7);
		} catch (IOException e) {
			System.out.print(e.getMessage());
			System.out.print("error");
		}
	}

	public void testSearchWhereFundingWasRaisedByParamsGivenCity() {
		try {
			Map<String, String> options = new HashMap<String, String>();
			options.put("city", "Tempe");
			assertEquals(FundingRaisedService.searchWhereFundingWasRaisedByParams(options).size(), 3);
		} catch (IOException e) {
			System.out.print(e.getMessage());
			System.out.print("error");
		}
	}

	public void testSearchWhereFundingWasRaisedByParamsGivenState() {
		try {
			Map<String, String> options = new HashMap<String, String>();
			options.put("state", "CA");
			assertEquals(FundingRaisedService.searchWhereFundingWasRaisedByParams(options).size(), 873);
		} catch (IOException e) {
			System.out.print(e.getMessage());
			System.out.print("error");
		}
	}

	public void testSearchWhereFundingWasRaisedByParamsGivenRound() {
		try {
			Map<String, String> options = new HashMap<String, String>();
			options.put("round", "a");
			assertEquals(FundingRaisedService.searchWhereFundingWasRaisedByParams(options).size(), 582);
		} catch (IOException e) {
			System.out.print(e.getMessage());
			System.out.print("error");
		}
	}

	public void testMultipleOptions() {
		try {
			Map<String, String> options = new HashMap<String, String>();
			options.put("round", "a");
			options.put("company_name", "Facebook");
			assertEquals(FundingRaisedService.searchWhereFundingWasRaisedByParams(options).size(), 1);
		} catch (IOException e) {
			System.out.print(e.getMessage());
			System.out.print("error");
		}
	}

	public void testSearchWhereFundingWasRaisedByParamsNotExists() {
		try {
			Map<String, String> options = new HashMap<String, String>();
			options.put("company_name", "NotFacebook");
			assertEquals(FundingRaisedService.searchWhereFundingWasRaisedByParams(options).size(), 0);
		} catch (IOException e) {
			System.out.print(e.getMessage());
			System.out.print("error");
		}
	}

	public void testSearchWhereFundingWasRaisedByParamsCorrectKeys() {
		try {
			Map<String, String> options = new HashMap<String, String>();
			options.put("company_name", "Facebook");
			Map<String, String> row = FundingRaisedService.searchWhereFundingWasRaisedByParams(options).get(0);

			assertEquals(row.get("permalink"), "facebook");
			assertEquals(row.get("company_name"), "Facebook");
			assertEquals(row.get("number_employees"), "450");
			assertEquals(row.get("category"), "web");
			assertEquals(row.get("city"), "Palo Alto");
			assertEquals(row.get("state"), "CA");
			assertEquals(row.get("funded_date"), "1-Sep-04");
			assertEquals(row.get("raised_amount"), "500000");
			assertEquals(row.get("round"), "angel");
		} catch (IOException e) {
			System.out.print(e.getMessage());
			System.out.print("error");
		}
	}

	public void testFindByParamsGivenCompanyName() {
		try {
			Map<String, String> options = new HashMap<String, String>();
			options.put("company_name", "Facebook");
			Map<String, String> row = FundingRaisedService.findByParams(options);

			assertEquals(row.get("permalink"), "facebook");
			assertEquals(row.get("company_name"), "Facebook");
			assertEquals(row.get("number_employees"), "450");
			assertEquals(row.get("category"), "web");
			assertEquals(row.get("city"), "Palo Alto");
			assertEquals(row.get("state"), "CA");
			assertEquals(row.get("funded_date"), "1-Sep-04");
			assertEquals(row.get("raised_amount"), "500000");
			assertEquals(row.get("round"), "angel");
		} catch (IOException e) {
			System.out.print(e.getMessage());
			System.out.print("error");
		} catch (NoSuchEntryException e) {
			System.out.print(e.getMessage());
			System.out.print("error");
		}
	}

	public void testFindByParamsGivenState() {
		try {
			Map<String, String> options = new HashMap<String, String>();
			options.put("state", "CA");
			Map<String, String> row = FundingRaisedService.findByParams(options);

			assertEquals(row.get("permalink"), "digg");
			assertEquals(row.get("company_name"), "Digg");
			assertEquals(row.get("number_employees"), "60");
			assertEquals(row.get("category"), "web");
			assertEquals(row.get("city"), "San Francisco");
			assertEquals(row.get("state"), "CA");
			assertEquals(row.get("funded_date"), "1-Dec-06");
			assertEquals(row.get("raised_amount"), "8500000");
			assertEquals(row.get("round"), "b");
		} catch (IOException e) {
			System.out.print(e.getMessage());
			System.out.print("error");
		} catch (NoSuchEntryException e) {
			System.out.print(e.getMessage());
			System.out.print("error");
		}
	}

	public void testFindByParamsMultipleOptions() {
		try {
			Map<String, String> options = new HashMap<String, String>();
			options.put("company_name", "Facebook");
			options.put("round", "c");
			Map<String, String> row = FundingRaisedService.findByParams(options);

			assertEquals(row.get("permalink"), "facebook");
			assertEquals(row.get("company_name"), "Facebook");
			assertEquals(row.get("number_employees"), "450");
			assertEquals(row.get("category"), "web");
			assertEquals(row.get("city"), "Palo Alto");
			assertEquals(row.get("state"), "CA");
			assertEquals(row.get("funded_date"), "1-Oct-07");
			assertEquals(row.get("raised_amount"), "300000000");
			assertEquals(row.get("round"), "c");
		} catch (IOException e) {
			System.out.print(e.getMessage());
			System.out.print("error");
		} catch (NoSuchEntryException e) {
			System.out.print(e.getMessage());
			System.out.print("error");
		}
	}

	public void testFindByParamsNotExists() {
		try {
			Map<String, String> options = new HashMap<String, String>();
			options.put("company_name", "NotFacebook");
			options.put("round", "c");
			Map<String, String> row = FundingRaisedService.findByParams(options);
			fail("findBy should throw exception");
		} catch (IOException e) {
			System.out.print(e.getMessage());
			System.out.print("error");
		} catch (NoSuchEntryException e) {
		}
	}
}
