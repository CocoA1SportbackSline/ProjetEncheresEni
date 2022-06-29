package fr.eni.ProjetEncheres.BO;

import java.time.LocalDateTime;


public class ArticleVendu {

	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private Float miseAPrix;
	private Float PrixDeVente;
	private int noUtilisateur;
	private int noCategorie;
	private String etatVente;
	
	
	
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			Float miseAPrix) {
		
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.miseAPrix = miseAPrix;
	}



	public ArticleVendu( String nomArticle, String description, LocalDateTime dateDebutEncheres,
			Float miseAPrix) {
		
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.miseAPrix = miseAPrix;
	}
	
	

	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, Float miseAPrix, Float prixDeVente, int noUtilisateur, int noCategorie) {
		
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		PrixDeVente = prixDeVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
	}



	



	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, Float miseAPrix, int noUtilisateur, int noCategorie) {
		
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
	}



	public int getNoArticle() {
		return noArticle;
	}



	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}



	public String getNomArticle() {
		return nomArticle;
	}



	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public  LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}



	public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}



	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}



	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}



	public Float getMiseAPrix() {
		return miseAPrix;
	}



	public void setMiseAPrix(Float miseAPrix) {
		this.miseAPrix = miseAPrix;
	}



	public Float getPrixDeVente() {
		return PrixDeVente;
	}



	public void setPrixDeVente(Float prixDeVente) {
		PrixDeVente = prixDeVente;
	}



	public String getEtatVente() {
		return etatVente;
	}



	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}



	public int getNoUtilisateur() {
		return noUtilisateur;
	}



	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}



	public int getNoCategorie() {
		return noCategorie;
	}



	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArticleVendu [noArticle=");
		builder.append(noArticle);
		builder.append(", nomArticle=");
		builder.append(nomArticle);
		builder.append(", description=");
		builder.append(description);
		builder.append(", dateDebutEncheres=");
		builder.append(dateDebutEncheres);
		builder.append(", dateFinEncheres=");
		builder.append(dateFinEncheres);
		builder.append(", miseAPrix=");
		builder.append(miseAPrix);
		builder.append(", PrixDeVente=");
		builder.append(PrixDeVente);
		builder.append(", noUtilisateur=");
		builder.append(noUtilisateur);
		builder.append(", noCategorie=");
		builder.append(noCategorie);
		builder.append(", etatVente=");
		builder.append(etatVente);
		builder.append("]");
		return builder.toString();
	}



	
	
	
	
	
	
	
	
	
}
