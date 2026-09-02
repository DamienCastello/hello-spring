package fr.castello.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class City {
    private long id;
    @NotBlank
    @Size(min = 2, message = "Le nom de la ville est non nul et possède au moins 2 caractères")
    private String name;
    @Min(value = 1, message = "Le nombre d’habitants est supérieur ou égal à 1")
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
