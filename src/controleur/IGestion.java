package controleur;

public interface IGestion<T> {
    String ajouter(T obj);
    T trouver(int id);
    String supprimer(int id);
    void afficher();
}