package fr.castello.entity;

import java.util.Objects;

public class City {
    private long id;
    private String name;
    private int population;

    private static int compteur = 1;

    public City(String name, int population) {
        this.id = compteur++;
        this.name = name;
        this.population = population;
    }

    public long getId() {
        return id;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id && population == city.population && Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, population);
    }

    @Override
    public String toString() {
        return "Ville{" +
                "name='" + name + '\'' +
                ", population=" + population +
                '}';
    }
}
