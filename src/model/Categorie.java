package model;

public class Categorie {
    public int id;
    public String nom;
    public String description;

    public Categorie(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public String toString() {
        return id + " - " + nom + " | " + description;
    }
}