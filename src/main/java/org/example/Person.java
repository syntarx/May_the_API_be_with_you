package org.example;

public class Person {
    private String name;
    private String homeworld;

    // Getter und Setter

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', homeworld='" + homeworld + "'}";
    }
}
