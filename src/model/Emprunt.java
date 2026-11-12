package model;

public class Emprunt {
    public int id;
    public Adherent adherent;
    public Ouvrage ouvrage;
    public Exemplaire exemplaire;
    public int jourEmprunt;
    public int jourPrevu;
    public int jourRetour = -1;
    public String statut;
    public int joursRetard = 0;
    public int penalite = 0;

    public Emprunt(int id, Adherent adherent, Exemplaire exemplaire, int jourEmprunt, int jourPrevu) {
        this.id = id;
        this.adherent = adherent;
        this.exemplaire = exemplaire;
        this.ouvrage = exemplaire.ouvrage;
        this.jourEmprunt = jourEmprunt;
        this.jourPrevu = jourPrevu;
        this.statut = "EN_COURS";
    }

    public String toString() {
        return "Emprunt " + id + " | " + adherent.nom + " -> " + ouvrage.titre + " [" + exemplaire.code + "] | jour " + jourEmprunt + " -> prevu jour " + jourPrevu + " | " + statut + (penalite > 0 ? " Penalite: " + penalite + " FCFA" : "");
    }
}