package model;

public class Exemplaire {
    public int id;
    public String code;
    public String etat;
    public Ouvrage ouvrage;

    public Exemplaire(int id, String code, Ouvrage ouvrage) {
        this.id = id;
        this.code = code;
        this.ouvrage = ouvrage;
        this.etat = "DISPONIBLE";
    }

    public String toString() {
        return code + " - " + ouvrage.titre + " | " + etat;
    }
}