package com.upgrade.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class jsonReader {
	
	public String[] readJson() throws IOException, ParseException {
		
		JSONParser jsonparser = new JSONParser();
		FileReader filereader = new FileReader(".\\com\\upgrade\\datasource\\testdata.json");
		Object obj  = jsonparser.parse(filereader);
		JSONObject signupdataJsonObj = (JSONObject)obj ;
		JSONArray signupdataArray = (JSONArray)signupdataJsonObj.get("TestData");
		
		String arr[] = new String[signupdataArray.size()];
		
		for(int i=0; i<=signupdataArray.size();i++) {
			
			JSONObject signupdataObject =(JSONObject)signupdataArray.get(i);
			String amount = (String) signupdataObject.get("amount");
			String purpose = (String) signupdataObject.get("purpose");
			String firstName = (String) signupdataObject.get("firstName");
			String lastName = (String) signupdataObject.get("lastName");
			String homeAddress = (String) signupdataObject.get("homeAddress");
			String DOB = (String) signupdataObject.get("DOB");
			String annualIncome = (String) signupdataObject.get("annualIncome");
			String additonalIncome = (String) signupdataObject.get("additonalIncome");
			String email = (String) signupdataObject.get("email");
			String password = (String) signupdataObject.get("password");
			
			arr[i]=amount+","+purpose+","+firstName+","+lastName+","+homeAddress+","+DOB+","+annualIncome+","+additonalIncome+","
			+email+","+password;
		}
		
		return arr;
	}

}
