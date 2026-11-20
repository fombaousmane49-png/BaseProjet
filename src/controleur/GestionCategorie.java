package controleur;

import model.Categorie;
import java.util.ArrayList;

public class GestionCategorie implements IGestion<Categorie> {
    public ArrayList<Categorie> categories = new ArrayList<>();
    public int prochainIdCategorie = 0;

    @Override
    public Categorie trouver(int id) {
        for (Categorie c : categories) if (c.id == id) return c;
        return null;
    }

    @Override
    public String ajouter(Categorie c) {
        prochainIdCategorie++;
        c.id = prochainIdCategorie;
        categories.add(c);
        return "Categorie ajoutee: " + c;
    }

    @Override
    public String supprimer(int id) {
        Categorie c = trouver(id);
        if (c == null) return "Erreur: categorie inexistante";
        categories.remove(c);
        return "Categorie supprimee: " + c;
    }

    @Override
    public void afficher() {
        for (Categorie c : categories) System.out.println(c);
    }
}