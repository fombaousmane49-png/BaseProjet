package vue;

import model.*;
import controleur.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        GestionCategorie gc = new GestionCategorie();
        GestionAuteur ga = new GestionAuteur();
        GestionAdherent gad = new GestionAdherent();
        GestionOuvrage go = new GestionOuvrage(ga, gc);
        GestionExemplaire ge = new GestionExemplaire(go);
        GestionEmprunt gem = new GestionEmprunt(gad, ge, go, gc, ga);
        go.setGestionEmprunt(gem);

        gem.exemple();
        System.out.println("Exemples charges !");

        int choix = -1;
        while (choix != 0) {
            System.out.println("\n--- BIBLIOTHEQUE ---");
            System.out.println("1. Afficher tout");
            System.out.println("2. Emprunter");
            System.out.println("3. Retourner");
            System.out.println("4. Afficher retards");
            System.out.println("5. Avancer jours");
            System.out.println("6. Tableau de bord");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            choix = sc.nextInt();
            sc.nextLine();

            if (choix == 1) {
                gc.afficher();
                ga.afficher();
                gad.afficher();
                go.afficher();
                ge.afficher();
                gem.afficher();
            } else if (choix == 2) {
                System.out.print("Id Adherent : ");
                int idAd = sc.nextInt();
                System.out.print("Id Exemplaire : ");
                int idEx = sc.nextInt();
                sc.nextLine();
                System.out.println(gem.ajouter(new Emprunt(0, gad.trouver(idAd), ge.trouver(idEx), 0, 0)));
            } else if (choix == 3) {
                System.out.print("Id Emprunt : ");
                int idE = sc.nextInt();
                sc.nextLine();
                System.out.println(gem.retourner(idE, "DISPONIBLE"));
            } else if (choix == 4) {
                gem.afficherRetards();
            } else if (choix == 5) {
                System.out.print("Combien de jours ? ");
                int nb = sc.nextInt();
                sc.nextLine();
                gem.avancerJours(nb);
            } else if (choix == 6) {
                gem.afficherTableauDeBord();
            }
        }
        sc.close();
        System.out.println("Au revoir !");
    }
}