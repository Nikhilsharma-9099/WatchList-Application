
package com.example.nikhilsharma.watchlist.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class RatingService {
	
String apiURL = "https://www.omdbapi.com/?apikey=a8431cee&t=gadar%202";
	
	public String getMovieRating(String title) {
		
		try {
			RestTemplate template = new RestTemplate(); 
			
			ResponseEntity<ObjectNode> response = template.getForEntity(apiURL+title, ObjectNode.class);
			
			ObjectNode jsonObject = response.getBody();
			
			System.out.println(jsonObject.path("imdbRating").asText());
			return jsonObject.path("imdbRating").asText();
		}catch(Exception e) {
			
			System.out.println("Either movie name not available or api is down" + e.getMessage());
			return null;
			
		}
		
	}

	
}
