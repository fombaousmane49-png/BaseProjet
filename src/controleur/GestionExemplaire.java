package controleur;

import model.*;
import java.util.ArrayList;

public class GestionExemplaire implements IGestion<Exemplaire> {
    public ArrayList<Exemplaire> exemplaires = new ArrayList<>();
    public int prochainIdExemplaire = 0;
    private GestionOuvrage gestionOuvrage;

    public GestionExemplaire(GestionOuvrage go) { this.gestionOuvrage = go; }

    @Override
    public Exemplaire trouver(int id) {
        for (Exemplaire e : exemplaires) if (e.id == id) return e;
        return null;
    }

    @Override
    public String ajouter(Exemplaire ex) {
        Ouvrage o = gestionOuvrage.trouver(ex.ouvrage.id);
        if (o == null) return "Erreur: ouvrage inexistant";
        if (o.retire) return "Erreur: ouvrage retire";
        prochainIdExemplaire++;
        ex.id = prochainIdExemplaire;
        ex.code = "EX-" + ex.id;
        exemplaires.add(ex);
        return "Exemplaire ajoute: " + ex;
    }

    
    @Override
    public String supprimer(int id) {
        Exemplaire ex = trouver(id);
        if (ex == null) return "Erreur: exemplaire inexistant";
        if (ex.etat.equals("EMPRUNTE")) return "Erreur: exemplaire emprunte";
        exemplaires.remove(ex);
        return "Exemplaire supprime: " + ex;
    }

    @Override
    public void afficher() {
        for (Exemplaire ex : exemplaires) System.out.println(ex);
    }
}