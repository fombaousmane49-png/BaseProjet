package model;

public class Ouvrage {
    public int id;
    public String titre;
    public String isbn;
    public int annee;
    public Auteur auteur;
    public Categorie categorie;
    public boolean retire = false;

    public Ouvrage(int id, String titre, String isbn, int annee, Auteur auteur, Categorie categorie) {
        this.id = id;
        this.titre = titre;
        this.isbn = isbn;
        this.annee = annee;
        this.auteur = auteur;
        this.categorie = categorie;
    }

    public String toString() {
        return id + " - " + titre + " | ISBN: " + isbn + " | " + annee + " | Auteur: " + auteur.nom + " | Categorie: " + categorie.nom + (retire ? " [RETIRE]" : "");
    }
}