package com.test.testControllerTest;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.test.controller.TestController;
import com.test.model.KeyValue;
import com.test.model.KeyValuePair;
import com.test.model.TestConstants;
import com.test.service.TestService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class TestControllerTest {

	@InjectMocks
	@Autowired
	private TestController testController;
	@Mock
	private TestService testService;

	@Before
	public void setup() {
	}

	@After
	public void after() {
	}

	@Test
	public void getHappy_Flow() throws FileNotFoundException, IOException {
		KeyValuePair data = new KeyValuePair();
		data.setKey("TEST KEY");
		KeyValue value = new KeyValue();
		value.setExiprytime(200);
		value.setValue("TEST VALUE");
		data.setValue(value);
		Assert.assertEquals(testService.addData(data), TestConstants.ADDED + TestConstants.SPACE + TestConstants.KEY
				+ TestConstants.SPACE + data.getKey() + TestConstants.SPACE + TestConstants.VALUE + data.getValue());
	}
}
