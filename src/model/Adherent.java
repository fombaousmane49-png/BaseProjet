package model;

public class Adherent {
    public int id;
    public String nom;
    public String prenom;
    public String email;
    public String statut; // ACTIF, SUSPENDU, INACTIF

    public Adherent(int id, String nom, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.statut = "ACTIF";
    }

    public String toString() {
        return id + " - " + nom + " " + prenom + " | " + email + " | " + statut;
    }
}