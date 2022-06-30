package fr.eni.ProjetEncheres.BO;

import java.sql.Date;
import java.time.LocalDateTime;

public class Enchere {

	private int noEnchere;
	private LocalDateTime dateEnchere;
	private int montantEnchere;
	private int noArticle;
	private int noUtilisateur;
	
	
	public Enchere(int noEnchere, LocalDateTime dateEnchere, int montantEnchere, int noArticle,
			int noUtilisateur) {
		super();
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noArticle = noArticle;
		this.noUtilisateur = noUtilisateur;
	}

	

	public Enchere(LocalDateTime date, int montantEnchere, int noArticle, int noUtilisateur) {
		super();
		this.dateEnchere = date;
		this.montantEnchere = montantEnchere;
		this.noArticle = noArticle;
		this.noUtilisateur = noUtilisateur;
	}



	public int getNoEnchere() {
		return noEnchere;
	}


	public void setNoEnchere(int noEnchere) {
		this.noEnchere = noEnchere;
	}


	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}


	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}


	public Integer getMontantEnchere() {
		return montantEnchere;
	}


	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}


	public int getNoArticle() {
		return noArticle;
	}


	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}


	public int getNoUtilisateur() {
		return noUtilisateur;
	}


	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}


	@Override
	public String toString() {
		return "Enchere [noEnchere=" + noEnchere + ", dateEnchere=" + dateEnchere + ", montantEnchere="
				+ montantEnchere + ", noArticle=" + noArticle + ", noUtilisateur=" + noUtilisateur + "]";
	}
	
	
	
	
}
