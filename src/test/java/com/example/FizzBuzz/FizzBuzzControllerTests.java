package com.example.FizzBuzz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Eine Klasse zur durchführung der Test von dem Controller.
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FizzBuzzControllerTests
{

	@LocalServerPort
	private int port;

	private final TestRestTemplate restTemplate = new TestRestTemplate();

	/**
	 * Ein Testfall zum Überprüfen des FizzBuzz-funktion mit einer positiven Zahl.
	 */

	@Test
	public void testFizzBuzzEndpoint()
	{
		ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/fizzbuzz?number=15", List.class);
		List<String> result = responseEntity.getBody();
		assertThat(result).containsExactly(
				"1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz",
				"11", "Fizz", "13", "14", "FizzBuzz"
		);
	}

	/**
	 * Ein Testfall zum Überprüfen des FizzBuzz-funktion mit einer negativen Zahl.
	 * Erwartet wird eine Fehlermeldung "Number must be positive".
	 */

	@Test
	public void testFizzBuzzEndpointWithNegativeNumber()
	{
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/fizzbuzz?number=-5", String.class);
		assertThat(responseEntity.getBody()).isEqualTo("Number must be positive");
	}
}
