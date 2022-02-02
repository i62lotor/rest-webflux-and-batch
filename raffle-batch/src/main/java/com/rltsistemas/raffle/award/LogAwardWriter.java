package com.rltsistemas.raffle.award;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.rltsistemas.raffle.model.Award;

public class LogAwardWriter implements ItemWriter<Award>{

	private static final Logger LOG = LoggerFactory.getLogger(LogAwardWriter.class);
	
	@Override
	public void write(List<? extends Award> items) throws Exception {
		items.forEach(i -> LOG.info("Writing: "+i.toString()));
	}



}
