package fr.eni.ProjetEncheres.BO;

import java.time.LocalDateTime;


public class ArticleVendu {


		private int noArticle;
		private String nomArticle;
		private String description;
		private LocalDateTime dateDebutEncheres;
		private LocalDateTime dateFinEncheres;
		private int prixInitial;
		private int prixVente;
		private String image;
		private int noUtilisateur;
		private int noCategorie;
	
		
	
	
	
	public ArticleVendu() {
	}
	
	/**
	 * Sans id et prix de vente
	 * 
	 */
	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int prixInitial, String image, int noUtilisateur, int noCategorie,
			int no_retrait) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.image = image;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
	
	}
	
	/**
	 * Sans id , prix de vente et photo
	 * 
	 */
	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int prixInitial, int noUtilisateur, int noCategorie,
			int no_retrait) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
	
	}

	/**
	 * All 
	 * 
	 */
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int prixInitial, int prixVente, String image, int noUtilisateur, int noCategorie,
			int no_retrait) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.image = image;
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

	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDate_debut_encheres(LocalDateTime dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDate_fin_encheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getPrix_initial() {
		return prixInitial;
	}

	public void setPrix_initial(int prix_initial) {
		this.prixInitial = prix_initial;
	}

	public int getPrix_vente() {
		return prixVente;
	}

	public void setPrix_vente(int prixVente) {
		this.prixVente = prixVente;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNo_utilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNo_categorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "ArticleVendu [no_article=" + noArticle + ", nom_article=" + nomArticle + ", description="
				+ description + ", date_debut_encheres=" + dateDebutEncheres + ", date_fin_encheres="
				+ dateFinEncheres + ", prix_initial=" + prixInitial + ", prix_vente=" + prixVente
				+ ", no_utilisateur=" + noUtilisateur + ", no_categorie=" + noCategorie + 
				"]";
	}
	




	




	



	
	
	
	
	
	
	
	
	
}
