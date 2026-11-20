package controleur;

import model.*;
import java.util.ArrayList;

public class GestionOuvrage implements IGestion<Ouvrage> {
    public ArrayList<Ouvrage> ouvrages = new ArrayList<>();
    public int prochainIdOuvrage = 0;
    private GestionAuteur gestionAuteur;
    private GestionCategorie gestionCategorie;
    private GestionEmprunt gestionEmprunt;

    public GestionOuvrage(GestionAuteur ga, GestionCategorie gc) {
        this.gestionAuteur = ga;
        this.gestionCategorie = gc;
    }
    public void setGestionEmprunt(GestionEmprunt ge) { this.gestionEmprunt = ge; }

    @Override
    public Ouvrage trouver(int id) {
        for (Ouvrage o : ouvrages) if (o.id == id) return o;
        return null;
    }

    @Override
    public String ajouter(Ouvrage o) {
        if (gestionAuteur.trouver(o.auteur.id) == null) return "Erreur: auteur inexistant";
        if (gestionCategorie.trouver(o.categorie.id) == null) return "Erreur: categorie inexistante";
        prochainIdOuvrage++;
        o.id = prochainIdOuvrage;
        ouvrages.add(o);
        return "Ouvrage ajoute: " + o;
    }

    @Override
    public String supprimer(int id) {
        Ouvrage o = trouver(id);
        if (o == null) return "Erreur: ouvrage inexistant";
        for (Emprunt e : gestionEmprunt.emprunts) {
            if (e.ouvrage.id == id && e.statut.equals("EN_COURS")) return "Erreur: emprunt en cours sur cet ouvrage";
        }
        o.retire = true;
        return "Ouvrage retire (historique conserve)";
    }

    public String retirerOuvrage(int id) { return supprimer(id); }

    @Override
    public void afficher() {
        for (Ouvrage o : ouvrages) System.out.println(o);
    }
}