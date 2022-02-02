package com.rltsistemas.raffle.award;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rltsistemas.raffle.model.Award;

public class HttpAwardWriter implements ItemWriter<Award> {

	@Value("${http.award.writer.url:http://localhost:8080/award}")
	private String serviceUrl;
	

	@Override
	public void write(List<? extends Award> items) throws Exception {
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = buildRequest(items.stream().findFirst().get());
		CompletableFuture<HttpResponse<String>> future = client.sendAsync(request,
															HttpResponse.BodyHandlers.ofString());
		future.thenApply(HttpResponse::body)
				.join();
		

	}
	


	private HttpRequest buildRequest(Award award) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		
		HttpRequest request = HttpRequest.newBuilder()
				.header("Content-Type", "application/json;")
                .POST(BodyPublishers.ofString(mapper.writeValueAsString(award)))				
                .uri(URI.create(serviceUrl))                
                .header("CONTENT_TYPE", "application/json")
                .build();
		return request;
	}

}
