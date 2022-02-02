package com.rltsistemas.rxlotteryexample.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.rltsistemas.rxlotteryexample.handler.AwardHandler;

@Configuration(proxyBeanMethods = false)
public class AwardRouter {

	@Bean
	public RouterFunction<ServerResponse> route(AwardHandler awardHandler) {

		return RouterFunctions.route(GET("/award")
				.and(accept(MediaType.APPLICATION_JSON)), awardHandler::lastAward)
				.andRoute(GET("/awards")
				.and(accept(MediaType.APPLICATION_JSON)), awardHandler::awards);
	}
}
