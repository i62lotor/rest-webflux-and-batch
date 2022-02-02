package com.rltsistemas.raffle.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.rltsistemas.raffle.award.AwardProcessor;
import com.rltsistemas.raffle.award.AwardWriterFactory;
import com.rltsistemas.raffle.model.Award;
import com.rltsistemas.raffle.model.Reward;

@Configuration
@EnableBatchProcessing
public class BatchConfigurarion {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public AwardProcessor processor() {
	  return new AwardProcessor();
	}
	

	@Bean
	public FlatFileItemReader<Reward> rewardReader() {
	  return new FlatFileItemReaderBuilder<Reward>()
	    .name("rewardItemReader")
	    .resource(new ClassPathResource("rewards.csv"))
	    .delimited()
	    .names(new String[]{"value","coinage"})
	    .fieldSetMapper(new BeanWrapperFieldSetMapper<Reward>() {{
	      setTargetType(Reward.class);
	    }})
	    .build();
	}
	
	@Bean
	public ItemWriter<Award> writer(@Value("${write.destination:LOG}")String destination) {
	  return new AwardWriterFactory().create(destination);
	}
	
	@Bean
	public Job raffleJob(Step step1) {
	  return jobBuilderFactory.get("raffleJob")
	    .incrementer(new RunIdIncrementer())
	    //.listener(listener)
	    .flow(step1)
	    .end()
	    .build();
	}

	@Bean
	public Step step1(ItemWriter<Award> writer) {
	  return stepBuilderFactory.get("step1")
	    .<Reward, Award> chunk(1)
	    .reader(rewardReader())
	    .processor(processor())
	    .writer(writer)
	    .build();
	}
	
	
}
