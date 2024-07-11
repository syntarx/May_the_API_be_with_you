package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    private String name;
    private String homeworld;
    private String homeworldName;

    @JsonProperty("height")
    private String height;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("hair_color")
    private String hairColor;

    // Setter und Getter für alle Felder

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public String getHomeworldName() {
        return homeworldName;
    }

    public void setHomeworldName(String homeworldName) {
        this.homeworldName = homeworldName;
    }

    public int getHeight() {
        try {
            return Integer.parseInt(height);
        } catch (NumberFormatException e) {
            return 0; // Rückgabewert für unbekannte Höhe
        }
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getFirstName() {
        return name.split(" ")[0]; // Annahme: Vorname ist das erste Wort im Namen
    }

    public String getLastName() {
        return name.split(" ").length > 1 ? name.split(" ")[1] : ""; // Annahme: Nachname ist das zweite Wort im Namen, falls vorhanden
    }
}
