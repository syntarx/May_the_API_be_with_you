package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Planet {
    private String name;

    // Getter und Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}