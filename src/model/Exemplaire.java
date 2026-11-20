package model;

public class Exemplaire {
    public int id;
    public String code;
    public String etat;
    public Ouvrage ouvrage;

    public Exemplaire(int id, String code, Ouvrage ouvrage) {
        this.id = id;
        this.code = code;
        this.ouvrage = ouvrage;
        this.etat = "DISPONIBLE";
    }

    public String toString() {
        return code + " - " + ouvrage.titre + " | " + etat;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Ouvrage getOuvrage() {
		return ouvrage;
	}

	public void setOuvrage(Ouvrage ouvrage) {
		this.ouvrage = ouvrage;
	}
}