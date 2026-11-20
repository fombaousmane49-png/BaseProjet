package controleur;

import model.Auteur;
import java.util.ArrayList;

public class GestionAuteur implements IGestion<Auteur> {
    public ArrayList<Auteur> auteurs = new ArrayList<>();
    public int prochainIdAuteur = 0;

    @Override
    public Auteur trouver(int id) {
        for (Auteur a : auteurs) if (a.id == id) return a;
        return null;
    }

    @Override
    public String ajouter(Auteur a) {
        prochainIdAuteur++;
        a.id = prochainIdAuteur;
        auteurs.add(a);
        return "Auteur ajoute: " + a;
    }

    @Override
    public String supprimer(int id) {
        Auteur a = trouver(id);
        if (a == null) return "Erreur: auteur inexistant";
        auteurs.remove(a);
        return "Auteur supprime: " + a;
    }

    @Override
    public void afficher() {
        for (Auteur a : auteurs) System.out.println(a);
    }
}