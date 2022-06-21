package de.neuefische.quiz;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class QuizControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void theIntegrationTest(){
        ResponseEntity<Question[]> emptyResponse = restTemplate.getForEntity("/api/questions", Question[].class);
        Assertions.assertThat(emptyResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(emptyResponse.getBody()).isEmpty();

        ResponseEntity<Question> createResponse = restTemplate.postForEntity("/api/questions", new Question("Frage1", "Antwort1"), Question.class);
        Assertions.assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(createResponse.getBody()).isNotNull();
        Assertions.assertThat(createResponse.getBody().getQuestion()).isEqualTo("Frage1");
        Assertions.assertThat(createResponse.getBody().getAnswer()).isEqualTo("Antwort1");

        ResponseEntity<Question> oneResponse = restTemplate.getForEntity("/api/questions/" + createResponse.getBody().getId(), Question.class);
        Assertions.assertThat(oneResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(oneResponse.getBody().getQuestion()).isEqualTo("Frage1");
        Assertions.assertThat(oneResponse.getBody().getAnswer()).isEqualTo("Antwort1");

        restTemplate.delete("/api/questions/" + createResponse.getBody().getId());
        ResponseEntity<Question[]> deleteResponse = restTemplate.getForEntity("/api/questions", Question[].class);
        Assertions.assertThat(deleteResponse.getBody()).isEmpty();

    }

}