package com.rltsistemas.raffle.award;

import java.time.LocalDateTime;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import com.rltsistemas.raffle.model.Award;
import com.rltsistemas.raffle.model.Reward;

public class AwardProcessor implements ItemProcessor<Reward, Award>{

	private static final Logger LOG = LoggerFactory.getLogger(AwardProcessor.class);
	
	@Value("${raffle.initial.number:1}")
	private int raffleInitialNumber;
	
	@Value("${raffle.finish.number:10}")
	private int raffleFinishNumber;
	
	@Override
	public Award process(Reward reward) throws Exception {
		Random random = new Random();
		Award award = new Award(random.nextInt(raffleFinishNumber - raffleInitialNumber) + raffleInitialNumber, reward);
		award.setDate(LocalDateTime.now());	
		LOG.info("Processing Award: "+award.toString());
		
		return award;
	}

}
