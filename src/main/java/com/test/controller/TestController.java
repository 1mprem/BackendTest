package com.test.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.KeyValuePair;
import com.test.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestService testService;

	/*
	 * this get method is used to add the data into the file
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	private String add(@RequestBody KeyValuePair keyValuePair) throws FileNotFoundException, IOException {
		return testService.addData(keyValuePair);

	}

	/*
	 * this function is used to get the data using the key
	 */
	@RequestMapping(value = "/get/{key}", method = RequestMethod.GET)
	private String get(@PathVariable(value = "key") String key) {
		return testService.getData(key);
		 

	}

	/*
	 * this function is used to delete the data using key
	 */
	@RequestMapping(value = "/delete/{key}", method = RequestMethod.GET)
	private String delete(@PathVariable(value = "key") String key) {
		return 	testService.deleteData(key);
		 

	}

}
