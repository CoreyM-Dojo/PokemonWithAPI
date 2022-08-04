package com.codingdojo.pokemon.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	private static HttpURLConnection connection;
	private static String api = "https://pokeapi.co/api/v2/pokemon/";
	@RequestMapping("")
	public static String getApi() {
		String response = sendGetRequest(api);
		
		return response;
	}
	
	@RequestMapping("/{pokeName}")
	public static String getPokemon(@PathVariable String pokeName) {
		String response = sendGetRequest(api + pokeName);
		return response;
	}
	
	public static String sendGetRequest(String url) {
		try {
			HttpRequest getRequest = (HttpRequest) HttpRequest.newBuilder()
					.uri(new URI(url))
					.build();
			System.out.println(getRequest);
			
			HttpClient httpClient = HttpClient.newHttpClient();
			HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());
			System.out.println(getResponse.body());
			
			return getResponse.body();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Something went wrong";
	}

}
