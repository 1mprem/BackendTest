package com.test.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.KeyValue;
import com.test.model.KeyValuePair;
import com.test.model.TestConstants;
import com.test.model.Value;

@Service
public class TestService implements ITestService {

	public String addData(KeyValuePair keyValuePair) throws FileNotFoundException, IOException {
		Properties mem = new Properties();
		mem = Load();
		if (keyValuePair != null) {
			if (!mem.containsKey(keyValuePair.getKey())) {
				KeyValue value = keyValuePair.getValue();
				Value data = getValue(value);
				try (FileWriter fileWriter = new FileWriter(TestConstants.FILE_NAME)) {
					ObjectMapper mapper = new ObjectMapper();
					try {
						mem.setProperty(keyValuePair.getKey(), mapper.writeValueAsString(data));
						mem.store(fileWriter, "BackEnd Assignment Info");
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
					return TestConstants.ADDED + TestConstants.SPACE + TestConstants.KEY + TestConstants.SPACE
							+ keyValuePair.getKey() + TestConstants.SPACE + TestConstants.VALUE + data.getValue();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		}
		return TestConstants.KEY_ALREADY_FOUND;
	}

	public String getData(String key) {
		try {
			Properties mem = new Properties();
			mem = Load();
			if (key != null) {
				Value data = new ObjectMapper().readValue(mem.get(key).toString(), Value.class);
				if (data != null && !isExpired(data)) {
					data.getValue();
					return TestConstants.VIEWED + TestConstants.SPACE + TestConstants.KEY + TestConstants.SPACE + key
							+ TestConstants.SPACE + TestConstants.VALUE + TestConstants.SPACE + data.getValue();

				}
			}

		} catch (Exception e) {
			return TestConstants.KEY_NO_LONGER_AVAILABLE;
		}
		return TestConstants.KEY_NO_LONGER_AVAILABLE;
	}

	public String deleteData(String key) {
		try {
			Properties mem = new Properties();
			mem = Load();
			if (key != null) {
				Value data = new ObjectMapper().readValue(mem.get(key).toString(), Value.class);
				if (data != null && !isExpired(data)) {
					mem.remove(key);
					try (FileWriter fileWriter = new FileWriter(TestConstants.FILE_NAME)) {
						try {
							mem.store(fileWriter, "BackEnd Assignment Info");
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}

						return TestConstants.DELETED + TestConstants.SPACE + TestConstants.KEY + TestConstants.SPACE
								+ key + TestConstants.SPACE + TestConstants.VALUE + TestConstants.SPACE
								+ data.getValue();
					}
				}
			}

		} catch (Exception e) {
			return TestConstants.KEY_NO_LONGER_AVAILABLE;
		}
		return TestConstants.KEY_NO_LONGER_AVAILABLE;
	}

	/*
	 * this function used for separation In the file only we have save the created
	 * date but we are not getting that from the user so that we have maintain 2
	 * model for value part.
	 */
	private Value getValue(KeyValue value) {
		Value data = new Value();
		data.setCreatedTimeStamp(getTimeStamp());
		if (value.getExiprytime() == null) {
			data.setExiprytime(200);
		} else {
			data.setExiprytime(value.getExiprytime());

		}
		data.setValue(value.getValue());
		return data;
	}

	/*
	 * this function is used to check the data is expired or not we have saved the
	 * expiry time & current time stamp while first time when we create a key value
	 * pair. FYI:We maintain the timestamp in the form of milliseconds. so that we
	 * have added multiply 1000 into expiry time then that value added into the
	 * created time stamp for evaluation purpose so that here we just compare the
	 * created and current time stamp to evaluate the key value pair is expired or
	 * not.
	 */ private Boolean isExpired(Value value) {
		if (value.getCreatedTimeStamp() != null && value.getExiprytime() != null) {
			DateFormat.getInstance().format(value.getCreatedTimeStamp());
			if ((getTimeStamp() < (value.getCreatedTimeStamp() + value.getExiprytime() * 1000))) {
				return false;
			}
		}
		return true;
	}

	/*
	 * this function is used to get the current system time stamp in the form of
	 * milliseconds
	 */
	private long getTimeStamp() {
		return System.currentTimeMillis();
	}

	/*
	 * this function is used to open the exist file and load the data from to append
	 * the content
	 */
	private Properties Load() throws FileNotFoundException, IOException {
		Properties p = new Properties();
		try (FileInputStream fis = new FileInputStream(TestConstants.FILE_NAME)) {
			p.load(fis);
			return p;
		}

	}
}
