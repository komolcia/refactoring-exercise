package com.checkr.interviews;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class FundingRaisedService {
	private static final Logger logger = Logger.getLogger(FundingRaisedService.class.getName());


	public static void main(String[] args) {
		try {
			Map<String, String> options = new HashMap<>();
			options.put(FundingRaisedEnum.COMPANY_NAME.getName(), "Facebook");
			options.put(FundingRaisedEnum.ROUND.getName(), "a");
			List<Map<String, String>> fundingRaisedPlaces = FundingRaisedService.searchWhereFundingWasRaisedByParams(options);
			logger.info(String.format("Funding was raised in %d places", fundingRaisedPlaces.size()));
		} catch (IOException e) {
			logger.severe(e.getMessage());
		}
	}

	public static List<Map<String, String>> searchWhereFundingWasRaisedByParams(Map<String, String> params) throws IOException {
		List<String[]> csvData = getContentOfFile();

		csvData = changeResultWhenContainsSpecifiedParams(params, csvData);

		List<Map<String, String>> output = new ArrayList<>();

		for (String[] csvDatum : csvData) {
			Map<String, String> result = new HashMap<>();
			putAllColumnsFromRecord(csvDatum, result);
			output.add(result);
		}

		return output;
	}

	private static List<String[]> changeResultWhenContainsSpecifiedParams(Map<String, String> params, List<String[]> csvData) {
		csvData = getListOfResults(params, csvData, FundingRaisedEnum.COMPANY_NAME);
		csvData = getListOfResults(params, csvData, FundingRaisedEnum.CITY);
		csvData = getListOfResults(params, csvData, FundingRaisedEnum.STATE);
		csvData = getListOfResults(params, csvData, FundingRaisedEnum.ROUND);
		return csvData;
	}

	private static List<String[]> getListOfResults(Map<String, String> params, List<String[]> csvData, FundingRaisedEnum fundingRaisedEnum) {
		if (params.containsKey(fundingRaisedEnum.getName())) {
			List<String[]> results = new ArrayList<>();

			for (String[] csvDatum : csvData) {
				if (csvDatum[fundingRaisedEnum.getNumber()].equals(params.get(fundingRaisedEnum.getName()))) {
					results.add(csvDatum);
				}
			}
			csvData = results;
		}
		return csvData;
	}

	public static Map<String, String> findByParams(Map<String, String> params) throws IOException, NoSuchEntryException {
		List<String[]> csvData = getContentOfFile();
		Map<String, String> results = new HashMap<>();
		for (int i = 0; i < csvData.size(); i++) {
			if (addAllFromLineIfContainsKey(params, csvData, i, results, FundingRaisedEnum.COMPANY_NAME) || addAllFromLineIfContainsKey(params,
					csvData, i, results, FundingRaisedEnum.CITY) || addAllFromLineIfContainsKey(params, csvData, i, results, FundingRaisedEnum.STATE) || addAllFromLineIfContainsKey(params, csvData, i, results, FundingRaisedEnum.ROUND))
				continue;
			return results;
		}

		throw new NoSuchEntryException("No such entry.");
	}

	private static List<String[]> getContentOfFile() throws IOException {
		List<String[]> csvData = new ArrayList<>();
		try (CSVReader reader = new CSVReader(new FileReader("startup_funding.csv"))) {
			String[] row;

			while ((row = reader.readNext()) != null) {
				csvData.add(row);
			}

		}
		csvData.remove(0);
		return csvData;
	}

	private static boolean addAllFromLineIfContainsKey(Map<String, String> params, List<String[]> csvData, int i, Map<String, String> mapped, FundingRaisedEnum fundingRaisedEnum) {
		if (params.containsKey(fundingRaisedEnum.getName())) {
			if (csvData.get(i)[fundingRaisedEnum.getNumber()].equals(params.get(fundingRaisedEnum.getName()))) {
				putAllColumnsFromRecord(csvData, mapped, i);

			} else {
				return true;
			}
		}
		return false;
	}

	private static void putAllColumnsFromRecord(List<String[]> csvData, Map<String, String> mapped, int line) {
		Arrays.stream(FundingRaisedEnum.values()).forEach(a -> mapped.put(a.getName(), csvData.get(line)[a.getNumber()]));
	}

	private static void putAllColumnsFromRecord(String[] csvDatum, Map<String, String> mapped) {
		Arrays.stream(FundingRaisedEnum.values()).forEach(a -> mapped.put(a.getName(), csvDatum[a.getNumber()]));
	}
}
