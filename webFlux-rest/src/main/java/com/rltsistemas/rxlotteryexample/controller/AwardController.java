package com.rltsistemas.rxlotteryexample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rltsistemas.rxlotteryexample.model.Award;
import com.rltsistemas.rxlotteryexample.service.RaffleService;

import reactor.core.publisher.Mono;

@RestController
public class AwardController {

	private final static Logger LOG = LoggerFactory.getLogger(AwardController.class);
	private RaffleService raffleService;
	
	
	public AwardController(RaffleService raffleService) {
		super();
		this.raffleService = raffleService;
	}



	@PostMapping(value = "/award", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Mono<ResponseEntity<Award>> pushAward(@RequestBody Award award, UriComponentsBuilder builder) {
		LOG.info("AWARD RECIBIDO:" + award);
		return raffleService.pushAward(award)
				.map(newAward -> {
					HttpHeaders headers = new HttpHeaders();
					headers.setLocation(builder.path("/awards/{id}")
							.buildAndExpand(newAward.getNumber()).toUri());
					return new ResponseEntity<Award>(headers, HttpStatus.CREATED);
		});
	}
	
	
}
