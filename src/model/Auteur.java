package model;

public class Auteur {
    public int id;
    public String nom;
    public String prenom;

    public Auteur(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String toString() {
        return id + " - " + nom + " " + prenom;
    }
}
