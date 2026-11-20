package controleur;

import model.*;
import java.util.ArrayList;

public class GestionEmprunt implements IGestion<Emprunt> {
    public ArrayList<Emprunt> emprunts = new ArrayList<>();
    public int prochainIdEmprunt = 0;
    public int jourActuel = 0;
    public int dureeEmprunt = 14;
    public int maxEmprunts = 3;
    public int penaliteParJour = 500;

    private GestionAdherent gestionAdherent;
    private GestionExemplaire gestionExemplaire;
    private GestionOuvrage gestionOuvrage;
    private GestionCategorie gestionCategorie;
    private GestionAuteur gestionAuteur;

    public GestionEmprunt(GestionAdherent ga, GestionExemplaire ge, GestionOuvrage go, GestionCategorie gc, GestionAuteur gau) {
        this.gestionAdherent = ga;
        this.gestionExemplaire = ge;
        this.gestionOuvrage = go;
        this.gestionCategorie = gc;
        this.gestionAuteur = gau;
    }

    
    public void exemple() {
        gestionCategorie.ajouter(new Categorie(0, "Roman", "Histoire"));
        gestionCategorie.ajouter(new Categorie(0, "Science", "Science"));

        gestionAuteur.ajouter(new Auteur(0, "Hugo", "Victor"));
        gestionAuteur.ajouter(new Auteur(0, "Camus", "Albert"));

        gestionAdherent.ajouter(new Adherent(0, "Diallo", "Awa", "awa@gmail.com"));
        gestionAdherent.ajouter(new Adherent(0, "Traore", "Moussa", "moussa@gmail.com"));

        gestionOuvrage.ajouter(new Ouvrage(0, "Les Miserables", "978-123", 1862, gestionAuteur.trouver(1), gestionCategorie.trouver(1)));
        gestionOuvrage.ajouter(new Ouvrage(0, "L'Etranger", "978-456", 1942, gestionAuteur.trouver(2), gestionCategorie.trouver(1)));

        gestionExemplaire.ajouter(new Exemplaire(0, "", gestionOuvrage.trouver(1)));
        gestionExemplaire.ajouter(new Exemplaire(0, "", gestionOuvrage.trouver(2)));

        ajouter(new Emprunt(0, gestionAdherent.trouver(1), gestionExemplaire.trouver(1), 0, 0));
    }

    @Override
    public Emprunt trouver(int id) {
        for (Emprunt e : emprunts) if (e.id == id) return e;
        return null;
    }

    @Override
    public String ajouter(Emprunt e) {
        return emprunter(e.adherent.id, e.exemplaire.id);
    }

    @Override
    public String supprimer(int id) {
        Emprunt e = trouver(id);
        if (e == null) return "Erreur: emprunt inexistant";
        if (e.statut.equals("EN_COURS")) return "Erreur: emprunt en cours, il faut le retourner d'abord";
        emprunts.remove(e);
        return "Emprunt supprime: " + e;
    }

    @Override
    public void afficher() {
        for (Emprunt e : emprunts) System.out.println(e);
    }

    public void avancerJours(int nb) {
        jourActuel = jourActuel + nb;
        System.out.println("On est maintenant au jour " + jourActuel);
    }

    public String emprunter(int idAdherent, int idExemplaire) {
        Adherent a = gestionAdherent.trouver(idAdherent);
        if (a == null) return "Erreur: adherent inexistant";
        Exemplaire ex = gestionExemplaire.trouver(idExemplaire);
        if (ex == null) return "Erreur: exemplaire inexistant";
        if (!a.statut.equals("ACTIF")) return "Erreur: adherent " + a.statut;
        if (!ex.etat.equals("DISPONIBLE")) return "Erreur: exemplaire " + ex.etat;
        if (ex.ouvrage.retire) return "Erreur: ouvrage retire";
        int nbEnCours = 0;
        for (Emprunt e : emprunts) {
            if (e.adherent.id == idAdherent && e.statut.equals("EN_COURS")) nbEnCours++;
        }
        if (nbEnCours >= maxEmprunts) return "Erreur: limite de " + maxEmprunts + " emprunts atteinte";
        int jourEmprunt = jourActuel;
        int jourPrevu = jourActuel + dureeEmprunt;
        prochainIdEmprunt++;
        int id = prochainIdEmprunt;
        Emprunt e = new Emprunt(id, a, ex, jourEmprunt, jourPrevu);
        emprunts.add(e);
        ex.etat = "EMPRUNTE";
        return "Emprunt OK: " + e;
    }

    public String retourner(int idEmprunt, String etatRetour) {
        Emprunt e = trouver(idEmprunt);
        if (e == null) return "Erreur: emprunt inexistant";
        if (e.statut.equals("RETOURNE")) return "Erreur: deja retourne !";
        e.jourRetour = jourActuel;
        e.statut = "RETOURNE";
        if (e.jourRetour > e.jourPrevu) {
            int jours = e.jourRetour - e.jourPrevu;
            e.joursRetard = jours;
            e.penalite = jours * penaliteParJour;
            if (jours > 30) e.adherent.statut = "SUSPENDU";
        }
        e.exemplaire.etat = etatRetour;
        return "Retour OK: " + e;
    }

    public void afficherRetards() {
        boolean trouve = false;
        for (Emprunt e : emprunts) {
            if (e.statut.equals("EN_COURS") && jourActuel > e.jourPrevu) {
                int jours = jourActuel - e.jourPrevu;
                System.out.println(e + " | RETARD " + jours + " jours");
                trouve = true;
            }
        }
        if (!trouve) System.out.println("Aucun retard - Jour actuel: " + jourActuel);
    }

    public void afficherTableauDeBord() {
        int dispo = 0;
        for (Exemplaire ex : gestionExemplaire.exemplaires) if (ex.etat.equals("DISPONIBLE")) dispo++;
        int actifs = 0;
        for (Adherent a : gestionAdherent.adherents) if (a.statut.equals("ACTIF")) actifs++;
        int enCours = 0;
        for (Emprunt e : emprunts) if (e.statut.equals("EN_COURS")) enCours++;
        System.out.println("Jour: " + jourActuel + " | Categories: " + gestionCategorie.categories.size() + " | Auteurs: " + gestionAuteur.auteurs.size() + " | Ouvrages: " + gestionOuvrage.ouvrages.size() + " | Exemplaires dispo: " + dispo + " | Adherents actifs: " + actifs + " | Emprunts en cours: " + enCours);
    }
}