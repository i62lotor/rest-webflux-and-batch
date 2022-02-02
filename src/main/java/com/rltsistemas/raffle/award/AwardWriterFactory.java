package com.rltsistemas.raffle.award;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.core.io.FileSystemResource;

import com.rltsistemas.raffle.model.Award;

public class AwardWriterFactory {

	private static final Logger LOG = LoggerFactory.getLogger(AwardWriterFactory.class);
	
	public static String FILE = "FILE";
	

	public ItemWriter<Award> create(String destination) {
		ItemWriter<Award> itemWriter = null;
		switch (destination) {
		case "FILE":
			itemWriter = new FlatFileItemWriterBuilder<Award>().name("awardItemWriter").encoding("UTF-8")
					.resource(new FileSystemResource("target/test-outputs/awards.txt")).delimited()
					.names("number", "reward", "date").build();
			break;
		case "HTTP":
			LOG.info("CREANDO INSTANCIA HTTP");
			itemWriter = new HttpAwardWriter();
			break;
		default:
			itemWriter = new LogAwardWriter();
			break;
		}

		return itemWriter;
	}
}
