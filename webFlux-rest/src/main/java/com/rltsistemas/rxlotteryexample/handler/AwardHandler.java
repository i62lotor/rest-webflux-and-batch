package com.rltsistemas.rxlotteryexample.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.rltsistemas.rxlotteryexample.model.Award;
import com.rltsistemas.rxlotteryexample.service.RaffleService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AwardHandler {

	@Autowired
	private RaffleService raffleService;
	
	public Mono<ServerResponse> lastAward(ServerRequest request) {
		Mono<Award> award = raffleService.getLastAward();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(award, Award.class)
				.or(Mono.empty());
	}
	
	public Mono<ServerResponse> awards(ServerRequest request) {
		Flux<Award> awards = raffleService.getAwards();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
        		.body(awards, Award.class);
				
	}
	
	
}
