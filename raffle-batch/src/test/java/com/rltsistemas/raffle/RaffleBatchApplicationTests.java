package com.rltsistemas.raffle;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBatchTest
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration()
class RaffleBatchApplicationTests {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	//TODO: implementar test
	@Test
	public void raffleJobTest() throws Exception {
		jobLauncherTestUtils.launchJob();
	}
}
