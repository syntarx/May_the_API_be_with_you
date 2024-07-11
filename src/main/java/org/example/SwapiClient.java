package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SwapiClient {

    private static final String BASE_URL = "https://swapi.dev/api/";

    public List<Person> getPeople() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Person> people = new ArrayList<>();
        JsonNode rootNode = mapper.readTree(new URL(BASE_URL + "people/"));

        Iterator<JsonNode> elements = rootNode.path("results").elements();
        while (elements.hasNext()) {
            JsonNode personNode = elements.next();
            Person person = mapper.treeToValue(personNode, Person.class);
            people.add(person);
        }

        return people;
    }

    public Planet getPlanet(String url) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new URL(url), Planet.class);
    }
}
