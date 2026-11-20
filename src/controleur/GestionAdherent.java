package controleur;

import model.Adherent;
import java.util.ArrayList;

public class GestionAdherent implements IGestion<Adherent> {
    public ArrayList<Adherent> adherents = new ArrayList<>();
    public int prochainIdAdherent = 0;

    @Override
    public Adherent trouver(int id) {
        for (Adherent a : adherents) if (a.id == id) return a;
        return null;
    }

    @Override
    public String ajouter(Adherent a) {
        prochainIdAdherent++;
        a.id = prochainIdAdherent;
        adherents.add(a);
        return "Adherent ajoute: " + a;
    }

    @Override
    public String supprimer(int id) {
        Adherent a = trouver(id);
        if (a == null) return "Erreur: adherent inexistant";
        adherents.remove(a);
        return "Adherent supprime: " + a;
    }

    @Override
    public void afficher() {
        for (Adherent a : adherents) System.out.println(a);
    }
}