package model;

public class Ouvrage {
    public int id;
    public String titre;
    public String isbn;
    public int annee;
    public Auteur auteur;
    public Categorie categorie;
    public boolean retire = false;

    public Ouvrage(int id, String titre, String isbn, int annee, Auteur auteur, Categorie categorie) {
        this.id = id;
        this.titre = titre;
        this.isbn = isbn;
        this.annee = annee;
        this.auteur = auteur;
        this.categorie = categorie;
    }

    public String toString() {
        return id + " - " + titre + " | ISBN: " + isbn + " | " + annee + " | Auteur: " + auteur.getNom() + " | Categorie: " + categorie.nom + (retire ? " [RETIRE]" : "");
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public boolean isRetire() {
		return retire;
	}

	public void setRetire(boolean retire) {
		this.retire = retire;
	}
}