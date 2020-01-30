package com.test.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.test.model.KeyValuePair;

public interface ITestService {

	public String deleteData(String Key);

	public String getData(String Key);

	public String addData(KeyValuePair keyValuePair) throws FileNotFoundException, IOException;

}
