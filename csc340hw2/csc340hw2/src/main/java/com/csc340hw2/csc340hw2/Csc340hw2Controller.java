package com.csc340hw2.csc340hw2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author kenne
 */
@RestController
public class Csc340hw2Controller {
    @GetMapping("/catquote")
    public Object getQuote() {
        try {
            String url = "https://meowfacts.herokuapp.com/";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonQuote = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonQuote);

            //Print the whole response to console.
            System.out.println(root);

            //Parse out the most important info from the response.
            String quote = root.get("data").asText();
            System.out.println("CAT QUOTE!! : " + quote);

            return root;

        } catch (JsonProcessingException ex) {
            Logger.getLogger(Csc340hw2Application.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /quote";
        }
    }
    
}
