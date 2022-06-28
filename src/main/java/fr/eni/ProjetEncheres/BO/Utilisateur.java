package fr.eni.ProjetEncheres.BO;


public class Utilisateur {
	
	private int noUtilsateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private int telephone;
	private String rue;
	private int code_Postal;
	private String ville;
	private String motDePasse;
	private int credit;
	private byte administrateur;
	
	public Utilisateur(int noUtilsateur, String pseudo, String nom, String prenom, String email, int telephone,
			String rue, int code_Postal, String ville, String motDePasse, int credit, byte administrateur) {
		super();
		this.noUtilsateur = noUtilsateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_Postal = code_Postal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}
	
	
	
	
	public Utilisateur(String pseudo, String nom, String prenom, String email, int telephone, String rue,
			int code_Postal, String ville, String motDePasse, int credit, byte administrateur) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_Postal = code_Postal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}
	
	public Utilisateur() {
		super();
	}

	public int getNoUtilsateur() {
		return noUtilsateur;
	}
	public void setNoUtilsateur(int noUtilsateur) {
		this.noUtilsateur = noUtilsateur;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public byte getAdministrateur() {
		return administrateur;
	}
	public void setAdministrateur(byte administrateur) {
		this.administrateur = administrateur;
	}
	
	public int getCode_Postal() {
		return code_Postal;
	}

	public void setCode_Postal(int code_Postal) {
		this.code_Postal = code_Postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Utilisateur [noUtilsateur=" + noUtilsateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", code_Postal="
				+ code_Postal + ", ville=" + ville + ", motDePasse=" + motDePasse + ", credit=" + credit
				+ ", administrateur=" + administrateur + "]";
	}
}
