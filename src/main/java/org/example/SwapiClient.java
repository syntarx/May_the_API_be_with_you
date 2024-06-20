package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SwapiClient {

    private static final String BASE_URL = "https://swapi.dev/api/";

    public static void main(String[] args) throws IOException {
        SwapiClient client = new SwapiClient();

        // Beispiel: Alle Personen filtern, die auf dem Planeten Tatooine geboren wurden
        List<Person> tatooineResidents = client.getPeople()
                .filter(person -> {
                    try {
                        return "Tatooine".equals(client.getPlanet(person.getHomeworld()).getName());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                })
                .collect(Collectors.toList());

        tatooineResidents.forEach(System.out::println);
    }

    public Stream<Person> getPeople() throws IOException {
        String peopleJson = fetchData("people/");
        PeopleResponse response = new ObjectMapper().readValue(peopleJson, PeopleResponse.class);
        return response.getResults().stream();
    }

    public Planet getPlanet(String url) throws IOException {
        String planetJson = fetchData(url.replace(BASE_URL, ""));
        return new ObjectMapper().readValue(planetJson, Planet.class);
    }

    private String fetchData(String endpoint) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(BASE_URL + endpoint);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }
            return EntityUtils.toString(response.getEntity());
        }
    }
}
