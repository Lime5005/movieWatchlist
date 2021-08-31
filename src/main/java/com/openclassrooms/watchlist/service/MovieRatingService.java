package com.openclassrooms.watchlist.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Properties;

public class MovieRatingService {

    public String getMovieRating(String title) {
        try {
            Properties prop = new Properties();
            try {
                prop.load(MovieRatingService.class.getClassLoader().getResourceAsStream("apiurl.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String url = prop.getProperty("apiUrl");
            RestTemplate template = new RestTemplate();
            ResponseEntity<ObjectNode> entity = template.getForEntity(url + title, ObjectNode.class);
            ObjectNode jsonNodes = entity.getBody();
            assert jsonNodes != null;
            return jsonNodes.path("imdbRating").asText();
        } catch (Exception e) {
            System.out.println("Something went wrong when calling OMDb API: " + e.getMessage());
            return null;
        }
    }


}
