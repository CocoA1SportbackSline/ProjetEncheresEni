package fr.eni.ProjetEncheres.BO;

public class ArticleEnVente {
	

	private ArticleVendu article;
	private Enchere meilleurEnchere;
	private Utilisateur user;
	private String dateFin;
	private Utilisateur userEncher;
	
	public ArticleEnVente() {
	}
	
	public ArticleEnVente(ArticleVendu article, Enchere meilleurEnchere, Utilisateur user, String date_fin) {
		super();
		this.article = article;
		this.meilleurEnchere = meilleurEnchere;
		this.user = user;
		this.dateFin = date_fin;
	}

	public ArticleEnVente(ArticleVendu article, Enchere meilleurEnchere, Utilisateur user, String date_fin, Utilisateur userEnchere) {
		super();
		this.article = article;
		this.meilleurEnchere = meilleurEnchere;
		this.user = user;
		this.dateFin = date_fin;
		this.userEncher = userEnchere;
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

	public Enchere getMeilleurEnchere() {
		return meilleurEnchere;
	}

	public void setMeilleurEnchere(Enchere meilleurEnchere) {
		this.meilleurEnchere = meilleurEnchere;
	}

	public String getDate_fin() {
		return dateFin;
	}

	public void setDate_fin(String date_fin) {
		this.dateFin = date_fin;
	}

	public Utilisateur getUserEncher() {
		return userEncher;
	}

	public void setUserEncher(Utilisateur userEncher) {
		this.userEncher = userEncher;
	}



	@Override
	public String toString() {
		return "ArticleEnVente [article=" + article + ", meilleurEnchere=" + meilleurEnchere + ", user=" + user
				+ ", date_fin=" + dateFin + "]";
	}



}
