package fr.eni.ProjetEncheres.BO;

public class Retrait {

	private int noRetrait;
	private String rue;
	private int codePostal;
	private String ville;
	
	
	
	
	public Retrait() {
		super();
	}


	public Retrait(int noRetrait, String rue, int codePostal, String ville) {
		super();
		this.noRetrait = noRetrait;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}


	public Retrait(String rue, int codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}


	public int getNoRetrait() {
		return noRetrait;
	}


	public void setNoRetrait(int noRetrait) {
		this.noRetrait = noRetrait;
	}


	public String getRue() {
		return rue;
	}


	public void setRue(String rue) {
		this.rue = rue;
	}


	public int getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	@Override
	public String toString() {
		return "Retrait [noRetrait=" + noRetrait + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville
				+ "]";
	}


	
	
	
	
}
