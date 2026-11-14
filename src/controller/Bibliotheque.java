package bibliotheque;

import model.*;
import java.util.ArrayList;

public class Bibliotheque implements IGestionBibliotheque {

    public ArrayList<Categorie> categories = new ArrayList<>();
    public ArrayList<Auteur> auteurs = new ArrayList<>();
    public ArrayList<Ouvrage> ouvrages = new ArrayList<>();
    public ArrayList<Exemplaire> exemplaires = new ArrayList<>();
    public ArrayList<Adherent> adherents = new ArrayList<>();
    public ArrayList<Emprunt> emprunts = new ArrayList<>();

    public int dureeEmprunt = 14;
    public int maxEmprunts = 3;
    public int penaliteParJour = 500;
    public int prochainIdCategorie = 0;
    public int prochainIdAuteur = 0;
    public int prochainIdOuvrage = 0;
    public int prochainIdExemplaire = 0;
    public int prochainIdAdherent = 0;
    public int prochainIdEmprunt = 0;
    public int jourActuel = 0;

    public void avancerJours(int nb) {
        jourActuel = jourActuel + nb;
        System.out.println("On est maintenant au jour " + jourActuel);
    }

    public Categorie trouverCategorie(int id) {
        for (Categorie c : categories) if (c.id == id) return c;
        return null;
    }
    public Auteur trouverAuteur(int id) {
        for (Auteur a : auteurs) if (a.id == id) return a;
        return null;
    }
    public Ouvrage trouverOuvrage(int id) {
        for (Ouvrage o : ouvrages) if (o.id == id) return o;
        return null;
    }
    public Exemplaire trouverExemplaire(int id) {
        for (Exemplaire e : exemplaires) if (e.id == id) return e;
        return null;
    }
    public Adherent trouverAdherent(int id) {
        for (Adherent a : adherents) if (a.id == id) return a;
        return null;
    }
    public Emprunt trouverEmprunt(int id) {
        for (Emprunt e : emprunts) if (e.id == id) return e;
        return null;
    }

    @Override
    public String ajouterCategorie(String nom, String description) {
        prochainIdCategorie++;
        int id = prochainIdCategorie;
        Categorie c = new Categorie(id, nom, description);
        categories.add(c);
        return "Categorie ajoutee : " + c;
    }

    @Override
    public String ajouterAuteur(String nom, String prenom) {
        prochainIdAuteur++;
        int id = prochainIdAuteur;
        Auteur a = new Auteur(id, nom, prenom);
        auteurs.add(a);
        return "Auteur ajoute : " + a;
    }

    @Override
    public String ajouterOuvrage(String titre, String isbn, int annee, int idAuteur, int idCategorie) {
        for (Ouvrage o : ouvrages) {
            if (o.isbn.equals(isbn)) return "Erreur : ISBN deja utilise !";
        }
        Auteur a = trouverAuteur(idAuteur);
        if (a == null) return "Erreur : auteur inexistant";
        Categorie c = trouverCategorie(idCategorie);
        if (c == null) return "Erreur : categorie inexistante";
        prochainIdOuvrage++;
        int id = prochainIdOuvrage;
        Ouvrage o = new Ouvrage(id, titre, isbn, annee, a, c);
        ouvrages.add(o);
        return "Ouvrage ajoute : " + o;
    }

    @Override
    public String retirerOuvrage(int id) {
        Ouvrage o = trouverOuvrage(id);
        if (o == null) return "Erreur : ouvrage inexistant";
        for (Emprunt e : emprunts) {
            if (e.ouvrage.id == id && e.statut.equals("EN_COURS")) return "Erreur : emprunt en cours sur cet ouvrage";
        }
        o.retire = true;
        return "Ouvrage retire (historique conserve)";
    }

    @Override
    public String ajouterExemplaire(int idOuvrage) {
        Ouvrage o = trouverOuvrage(idOuvrage);
        if (o == null) return "Erreur : ouvrage inexistant";
        if (o.retire) return "Erreur : ouvrage retire";
        prochainIdExemplaire++;
        int id = prochainIdExemplaire;
        Exemplaire ex = new Exemplaire(id, "EX-" + id, o);
        exemplaires.add(ex);
        return "Exemplaire ajoute : " + ex;
    }

    @Override
    public String ajouterAdherent(String nom, String prenom, String email) {
        if (nom.equals("") || prenom.equals("") || email.equals("")) return "Erreur : champs obligatoires !";
        for (Adherent a : adherents) {
            if (a.email.equals(email)) return "Erreur : email deja utilise !";
        }
        prochainIdAdherent++;
        int id = prochainIdAdherent;
        Adherent a = new Adherent(id, nom, prenom, email);
        adherents.add(a);
        return "Adherent ajoute : " + a;
    }

    @Override
    public String emprunter(int idAdherent, int idExemplaire) {
        Adherent a = trouverAdherent(idAdherent);
        if (a == null) return "Erreur : adherent inexistant";
        Exemplaire ex = trouverExemplaire(idExemplaire);
        if (ex == null) return "Erreur : exemplaire inexistant";
        if (!a.statut.equals("ACTIF")) return "Erreur : adherent " + a.statut;
        if (!ex.etat.equals("DISPONIBLE")) return "Erreur : exemplaire " + ex.etat;
        if (ex.ouvrage.retire) return "Erreur : ouvrage retire";

        int nbEnCours = 0;
        for (Emprunt e : emprunts) {
            if (e.adherent.id == idAdherent && e.statut.equals("EN_COURS")) nbEnCours++;
        }
        if (nbEnCours >= maxEmprunts) return "Erreur : limite de " + maxEmprunts + " emprunts atteinte";

        int jourEmprunt = jourActuel;
        int jourPrevu = jourActuel + dureeEmprunt;
        prochainIdEmprunt++;
        int id = prochainIdEmprunt;
        Emprunt e = new Emprunt(id, a, ex, jourEmprunt, jourPrevu);
        emprunts.add(e);
        ex.etat = "EMPRUNTE";
        return "Emprunt OK : " + e;
    }

    @Override
    public String retourner(int idEmprunt, String etatRetour) {
        Emprunt e = trouverEmprunt(idEmprunt);
        if (e == null) return "Erreur : emprunt inexistant";
        if (e.statut.equals("RETOURNE")) return "Erreur : deja retourne !";
        e.jourRetour = jourActuel;
        e.statut = "RETOURNE";
        if (e.jourRetour > e.jourPrevu) {
            int jours = e.jourRetour - e.jourPrevu;
            e.joursRetard = jours;
            e.penalite = jours * penaliteParJour;
            if (jours > 30) e.adherent.statut = "SUSPENDU";
        }
        e.exemplaire.etat = etatRetour;
        return "Retour OK : " + e;
    }

    @Override
    public void afficherRetards() {
        boolean trouve = false;
        for (Emprunt e : emprunts) {
            if (e.statut.equals("EN_COURS") && jourActuel > e.jourPrevu) {
                int jours = jourActuel - e.jourPrevu;
                System.out.println(e + " | RETARD " + jours + " jours");
                trouve = true;
            }
        }
        if (!trouve) System.out.println("Aucun retard - Jour actuel : " + jourActuel);
    }

    @Override
    public void afficherTableauDeBord() {
        int dispo = 0;
        for (Exemplaire ex : exemplaires) if (ex.etat.equals("DISPONIBLE")) dispo++;
        int actifs = 0;
        for (Adherent a : adherents) if (a.statut.equals("ACTIF")) actifs++;
        int enCours = 0;
        for (Emprunt e : emprunts) if (e.statut.equals("EN_COURS")) enCours++;
        System.out.println("Jour: " + jourActuel + " | Categories: " + categories.size() + " | Auteurs: " + auteurs.size() + " | Ouvrages: " + ouvrages.size() + " | Exemplaires dispo: " + dispo + " | Adherents actifs: " + actifs + " | Emprunts en cours: " + enCours);
    }

    public void chargerDemo() {
        ajouterCategorie("Roman", "Romans et litterature");
        ajouterCategorie("Informatique", "Livres informatiques");
        ajouterAuteur("Camara", "Laye");
        ajouterAuteur("Tanenbaum", "Andrew");
        ajouterOuvrage("L'Enfant noir", "978-123", 1953, 1, 1);
        ajouterOuvrage("Reseaux", "978-456", 2011, 2, 2);
        ajouterExemplaire(1); ajouterExemplaire(1); ajouterExemplaire(2);
        ajouterAdherent("Diallo", "Awa", "awa@gmail.com");
        ajouterAdherent("Traore", "Moussa", "moussa@gmail.com");
    }
}