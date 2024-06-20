package org.example;

public class Planet {
    private String name;

    // Getter und Setter


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Planet{name='" + name + "'}";
    }
}
