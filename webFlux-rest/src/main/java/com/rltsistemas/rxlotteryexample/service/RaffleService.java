package com.rltsistemas.rxlotteryexample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rltsistemas.rxlotteryexample.model.Award;
import com.rltsistemas.rxlotteryexample.repository.RaffleRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RaffleService {

	private final static Logger LOG = LoggerFactory.getLogger(RaffleService.class);
	
	private RaffleRepository raffleRepository;
	
	@Value("${number.of.awards:15}")
	private int awardsNumber;
	
	public RaffleService(RaffleRepository raffleRepository) {
		this.raffleRepository = raffleRepository;
	}

	public Mono<Award> getLastAward() {
		return raffleRepository.getAwards().reduce((first, second) -> second)
				.or(Mono.empty());
	}

	public Flux<Award> getAwards(){
		return raffleRepository.getAwards();
	}

	public Mono<Award> pushAward(Award award) {
		return raffleRepository.pushAward(award);
	}
	
	public Award addAward(Award award) {
		return raffleRepository.addAward(award);
	}


}
