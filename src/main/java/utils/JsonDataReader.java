package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;


import models.TestCaseData;;

public class JsonDataReader {

	private final String usersPath = "src\\main\\resources\\testdata\\Users.json";

	private List<TestCaseData> testCaseDataList;

	public JsonDataReader() {
	
		testCaseDataList = getTestCaseDataListFromFile();
	}

	private List<TestCaseData> getTestCaseDataListFromFile() {
		Gson gson = new Gson();
		BufferedReader bufferReader = null;
		try {
			bufferReader = new BufferedReader(new FileReader(usersPath));
			TestCaseData[] testCaseDetails = gson.fromJson(bufferReader, TestCaseData[].class);
			return Arrays.asList(testCaseDetails);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : " + usersPath);
		} finally {
			try {
				if (bufferReader != null)
					bufferReader.close();
			} catch (IOException ignore) {
			}
		}
	}

	

	public TestCaseData getDataByTestCaseName(String testCaseNumber) {
		return testCaseDataList.stream().filter(x -> x.TestCaseNumber.equalsIgnoreCase(testCaseNumber)).findAny().get();
	}

	

}
