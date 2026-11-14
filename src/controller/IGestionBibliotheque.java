package bibliotheque;

public interface IGestionBibliotheque {
    String ajouterCategorie(String nom, String description);
    String ajouterAuteur(String nom, String prenom);
    String ajouterOuvrage(String titre, String isbn, int annee, int idAuteur, int idCategorie);
    String retirerOuvrage(int id);
    String ajouterExemplaire(int idOuvrage);
    String ajouterAdherent(String nom, String prenom, String email);
    String emprunter(int idAdherent, int idExemplaire);
    String retourner(int idEmprunt, String etatRetour);
    void afficherRetards();
    void afficherTableauDeBord();
}