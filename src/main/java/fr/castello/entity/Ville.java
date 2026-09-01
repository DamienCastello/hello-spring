package fr.castello.entity;

import java.util.Objects;

public class Ville {
    private long id;
    private String nom;
    private int population;

    private static int compteur = 1;

    public Ville(String nom, int population) {
        this.id = compteur++;
        this.nom = nom;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ville ville = (Ville) o;
        return id == ville.id && population == ville.population && Objects.equals(nom, ville.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, population);
    }

    @Override
    public String toString() {
        return "Ville{" +
                "nom='" + nom + '\'' +
                ", population=" + population +
                '}';
    }
}
