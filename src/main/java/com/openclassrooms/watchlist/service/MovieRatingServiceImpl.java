package com.openclassrooms.watchlist.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Properties;

@Service
@ConditionalOnProperty(name="app.environment", havingValue="prod")
public class MovieRatingServiceImpl implements MovieRatingService {

    private final Logger logger = LoggerFactory.getLogger(MovieRatingServiceImpl.class);

    @Override
    public String getMovieRating(String title) {
        try {
            Properties prop = new Properties();
            try {
                prop.load(MovieRatingServiceImpl.class.getClassLoader().getResourceAsStream("apiurl.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String url = prop.getProperty("apiUrl");
            RestTemplate template = new RestTemplate();
            logger.info("OMDb API called withURL: {}", url + title);
            ResponseEntity<ObjectNode> entity = template.getForEntity(url + title, ObjectNode.class);
            ObjectNode jsonNodes = entity.getBody();
            logger.debug("OMDb API response: {}", jsonNodes);
            assert jsonNodes != null;
            return jsonNodes.path("imdbRating").asText();
        } catch (Exception e) {
            logger.warn("Something went wrong when calling OMDb API: " + e.getMessage());
            return null;
        }
    }


}
