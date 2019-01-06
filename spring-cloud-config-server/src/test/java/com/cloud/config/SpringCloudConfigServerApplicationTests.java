package com.cloud.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cloud.config.server.SpringCloudConfigServerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringCloudConfigServerApplication.class)
public class SpringCloudConfigServerApplicationTests {

	@Test
	public void contextLoads() {
	}

}

