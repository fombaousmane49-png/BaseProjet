package affichage;

import model.*;
import bibliotheque.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bibliotheque bib = new Bibliotheque();
        bib.chargerDemo();
        System.out.println("=== BIBLIOTHEQUE - Donnees demo chargees ===");

        int choix = -1;
        while (choix != 0) {
            System.out.println("\n--- MENU - Jour " + bib.jourActuel + " ---");
            System.out.println("1. Ajouter categorie  2. Lister categories");
            System.out.println("3. Ajouter auteur  4. Lister auteurs");
            System.out.println("5. Ajouter ouvrage  6. Lister ouvrages  7. Retirer ouvrage");
            System.out.println("8. Ajouter exemplaire  9. Lister exemplaires");
            System.out.println("10. Ajouter adherent  11. Lister adherents");
            System.out.println("12. Emprunter  13. Retourner  14. Emprunts en cours");
            System.out.println("15. Retards  16. Tableau de bord  17. Avancer les jours  0. Quitter");
            System.out.print("Choix : ");
            String ligne = sc.nextLine();
            if (ligne.equals("")) continue;
            choix = Integer.parseInt(ligne);

            if (choix == 1) {
                System.out.print("Nom categorie : "); String n = sc.nextLine();
                System.out.print("Description : "); String d = sc.nextLine();
                System.out.println(bib.ajouterCategorie(n, d));
            }
            else if (choix == 2) {
                for (Categorie c : bib.categories) System.out.println(c);
            }
            else if (choix == 3) {
                System.out.print("Nom auteur : "); String n = sc.nextLine();
                System.out.print("Prenom : "); String p = sc.nextLine();
                System.out.println(bib.ajouterAuteur(n, p));
            }
            else if (choix == 4) {
                for (Auteur a : bib.auteurs) System.out.println(a);
            }
            else if (choix == 5) {
                System.out.print("Titre : "); String t = sc.nextLine();
                System.out.print("ISBN : "); String isbn = sc.nextLine();
                System.out.print("Annee : "); int an = Integer.parseInt(sc.nextLine());
                System.out.print("Id auteur : "); int ida = Integer.parseInt(sc.nextLine());
                System.out.print("Id categorie : "); int idc = Integer.parseInt(sc.nextLine());
                System.out.println(bib.ajouterOuvrage(t, isbn, an, ida, idc));
            }
            else if (choix == 6) {
                for (Ouvrage o : bib.ouvrages) System.out.println(o);
            }
            else if (choix == 7) {
                System.out.print("Id ouvrage : "); int id = Integer.parseInt(sc.nextLine());
                System.out.println(bib.retirerOuvrage(id));
            }
            else if (choix == 8) {
                System.out.print("Id ouvrage : "); int id = Integer.parseInt(sc.nextLine());
                System.out.println(bib.ajouterExemplaire(id));
            }
            else if (choix == 9) {
                for (Exemplaire e : bib.exemplaires) System.out.println(e);
            }
            else if (choix == 10) {
                System.out.print("Nom : "); String n = sc.nextLine();
                System.out.print("Prenom : "); String p = sc.nextLine();
                System.out.print("Email : "); String e = sc.nextLine();
                System.out.println(bib.ajouterAdherent(n, p, e));
            }
            else if (choix == 11) {
                for (Adherent a : bib.adherents) System.out.println(a);
            }
            else if (choix == 12) {
                System.out.print("Id adherent : "); int ida = Integer.parseInt(sc.nextLine());
                System.out.print("Id exemplaire : "); int ide = Integer.parseInt(sc.nextLine());
                System.out.println(bib.emprunter(ida, ide));
            }
            else if (choix == 13) {
                System.out.print("Id emprunt : "); int ide = Integer.parseInt(sc.nextLine());
                System.out.print("Etat retour (DISPONIBLE / DETERIORE / PERDU) : "); String etat = sc.nextLine();
                System.out.println(bib.retourner(ide, etat));
            }
            else if (choix == 14) {
                for (Emprunt e : bib.emprunts) if (e.statut.equals("EN_COURS")) System.out.println(e);
            }
            else if (choix == 15) {
                bib.afficherRetards();
            }
            else if (choix == 16) {
                bib.afficherTableauDeBord();
            }
            else if (choix == 17) {
                System.out.print("Avancer de combien de jours ? ");
                int nb = Integer.parseInt(sc.nextLine());
                bib.avancerJours(nb);
            }
        }
        System.out.println("Au revoir !");
    }
}