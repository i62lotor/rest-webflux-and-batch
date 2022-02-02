package com.rltsistemas.rxlotteryexample;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.rltsistemas.rxlotteryexample.model.Award;
import com.rltsistemas.rxlotteryexample.model.Reward;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
//We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RxLotteryExampleApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	@Order(1)
	void givenAwardWhenPostThenCreateAward() {
		//Given
		Award award = new Award.Builder().withNumber(33)
				.withReward(new Reward(100, "points"))
				.build();
		//When
		webTestClient.post().uri("/award")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(award), Award.class)
		.exchange()
		//Then
		.expectStatus().isCreated(); 
		
	}
	
	
	@Test
	@Order(2)
	void whenRetrieveAnAwardThenGetOk() {
		webTestClient
	      .get().uri("/award")
	      .accept(MediaType.APPLICATION_JSON)
	      .exchange()
	      .expectStatus().isOk();
	}
	
	@Test
	@Order(3)
	void whenRetrieveAllAwardsThenGetOK() {
		webTestClient
	      .get().uri("/awards")
	      .accept(MediaType.APPLICATION_JSON)
	      .exchange()
	      // and use the dedicated DSL to test assertions against the response
	      .expectStatus().isOk()
	      .expectBodyList(Award.class).value(awards -> {
	        assertThat(awards).isNotNull();
	    });
	}
	
}
