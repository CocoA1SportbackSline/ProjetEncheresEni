package fr.eni.ProjetEncheres.BO;

public class ArticleEnVente {
	

	private ArticleVendu article;
	private Enchere meilleureEnchere;
	private Utilisateur user;
	private String dateFin;
	private Utilisateur userEnchere;
	
	public ArticleEnVente() {
	}
	
	public ArticleEnVente(ArticleVendu article, Enchere meilleureEnchere, Utilisateur user, String dateFin) {
		super();
		this.article = article;
		this.meilleureEnchere = meilleureEnchere;
		this.user = user;
		this.dateFin = dateFin;
	}

	public ArticleEnVente(ArticleVendu article, Enchere meilleureEnchere, Utilisateur user, String dateFin, Utilisateur userEnchere) {
		super();
		this.article = article;
		this.meilleureEnchere = meilleureEnchere;
		this.user = user;
		this.dateFin = dateFin;
		this.userEnchere = userEnchere;
	}

	

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	public Enchere getMeilleureEnchere() {
		return meilleureEnchere;
	}

	public void setMeilleureEnchere(Enchere meilleureEnchere) {
		this.meilleureEnchere = meilleureEnchere;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public Utilisateur getUserEnchere() {
		return userEnchere;
	}

	public void setUserEnchere(Utilisateur userEnchere) {
		this.userEnchere = userEnchere;
	}



	@Override
	public String toString() {
		return "ArticleEnVente [article=" + article + ", meilleureEnchere=" + meilleureEnchere + ", user=" + user
				+ ", dateFin=" + dateFin + "]";
	}



}
