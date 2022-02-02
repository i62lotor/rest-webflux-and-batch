package com.rltsistemas.rxlotteryexample.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.rltsistemas.rxlotteryexample.model.Award;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Simula un Repository
 * @author rltorres
 *
 */
@Repository
public class RaffleRepository {

	private final static Logger LOG = LoggerFactory.getLogger(RaffleRepository.class);
	
	private List<Award> awards =  new ArrayList<Award>();
	
	public Flux<Award> getAwards() {
		return Flux.fromIterable(awards);
	}
	
	public Mono<Award> pushAward(Award award) {
		
		this.awards.add(award);
		LOG.info("Award pushed-> " + System.currentTimeMillis() / 1000 + " award" + award);
		LOG.info("Total awards recibidos: "+awards.size());
		return Mono.just(award);
	}

	public Award addAward(Award award) {
		
		this.awards.add(award);
		LOG.info("Award added-> " + System.currentTimeMillis() / 1000 + " award" + award);
		LOG.info("Total awards recibidos: "+awards.size());
		return award;
	}

	public int numberOfAwards() {
		return awards.size();
	}
	
}
