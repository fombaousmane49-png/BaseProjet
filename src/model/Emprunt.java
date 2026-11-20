package model;

public class Emprunt {
    public int id;
    public Adherent adherent;
    public Ouvrage ouvrage;
    public Exemplaire exemplaire;
    public int jourEmprunt;
    public int jourPrevu;
    public int jourRetour = -1;
    public String statut;
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Adherent getAdherent() {
		return adherent;
	}

	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}

	public Ouvrage getOuvrage() {
		return ouvrage;
	}

	public void setOuvrage(Ouvrage ouvrage) {
		this.ouvrage = ouvrage;
	}

	public Exemplaire getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}

	public int getJourEmprunt() {
		return jourEmprunt;
	}

	public void setJourEmprunt(int jourEmprunt) {
		this.jourEmprunt = jourEmprunt;
	}

	public int getJourPrevu() {
		return jourPrevu;
	}

	public void setJourPrevu(int jourPrevu) {
		this.jourPrevu = jourPrevu;
	}

	public int getJourRetour() {
		return jourRetour;
	}

	public void setJourRetour(int jourRetour) {
		this.jourRetour = jourRetour;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public int getJoursRetard() {
		return joursRetard;
	}

	public void setJoursRetard(int joursRetard) {
		this.joursRetard = joursRetard;
	}

	public int getPenalite() {
		return penalite;
	}

	public void setPenalite(int penalite) {
		this.penalite = penalite;
	}

	public int joursRetard = 0;
    public int penalite = 0;

    public Emprunt(int id, Adherent adherent, Exemplaire exemplaire, int jourEmprunt, int jourPrevu) {
        this.id = id;
        this.adherent = adherent;
        this.exemplaire = exemplaire;
        this.ouvrage = exemplaire.ouvrage;
        this.jourEmprunt = jourEmprunt;
        this.jourPrevu = jourPrevu;
        this.statut = "EN_COURS";
    }

    public String toString() {
        return "Emprunt " + id + " | " + adherent.nom + " -> " + ouvrage.titre + " [" + exemplaire.code + "] | jour " + jourEmprunt + " -> prevu jour " + jourPrevu + " | " + statut + (penalite > 0 ? " Penalite: " + penalite + " FCFA" : "");
    }
}