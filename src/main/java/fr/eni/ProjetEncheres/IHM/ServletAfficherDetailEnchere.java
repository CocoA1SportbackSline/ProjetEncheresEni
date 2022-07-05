package fr.eni.ProjetEncheres.IHM;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ProjetEncheres.BLL.ArticleVenduManager;
import fr.eni.ProjetEncheres.BLL.BLLException;
import fr.eni.ProjetEncheres.BLL.CategorieManager;
import fr.eni.ProjetEncheres.BLL.EnchereManager;
import fr.eni.ProjetEncheres.BLL.RetraitManager;
import fr.eni.ProjetEncheres.BLL.UtilisateurManager;
import fr.eni.ProjetEncheres.BO.ArticleEnVente;
import fr.eni.ProjetEncheres.BO.ArticleVendu;
import fr.eni.ProjetEncheres.BO.Enchere;
import fr.eni.ProjetEncheres.BO.Retrait;
import fr.eni.ProjetEncheres.BO.Utilisateur;

public class ServletAfficherDetailEnchere extends HttpServlet{

private static final long serialVersionUID = 1L;
	
	private UtilisateurManager utilisateurManager;
	private ArticleVenduManager articleVenduManager;
	private CategorieManager categorieManager;
	private EnchereManager enchereManager;
	private RetraitManager retraitManager;
       
	public void init() throws ServletException {
		articleVenduManager = ArticleVenduManager.getInstance();
		utilisateurManager = UtilisateurManager.getInstance();
		categorieManager = CategorieManager.getInstance();
		enchereManager = EnchereManager.getInstance();
		retraitManager = RetraitManager.getInstance();
    	super.init();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		int no_article = 0;
		ArticleVendu articleVendu = null;
		Enchere derniereEnchere = null;
		ArticleEnVente article = null;
		Utilisateur user = null;
		Utilisateur userEnchere = null;
		String libelleCategorie = null;
		Retrait retrait = null;
		boolean checkDateDebut = true;
		boolean checkDateFin = true;
		
		if(request.getParameter("sno_article") != null && !request.getParameter("sno_article").isEmpty()) {
			no_article = Integer.parseInt(request.getParameter("sno_article"));
			request.setAttribute("sno_article", no_article);
		} else if(request.getAttribute("sno_article") != null) {
			no_article = (int) request.getAttribute("sno_article");
			request.setAttribute("sno_article", no_article);
		}
		
		try {
			articleVendu = articleVenduManager.selectArticleById(no_article);
			user = utilisateurManager.postUser(articleVendu.getNoUtilisateur());
			derniereEnchere = enchereManager.derniereEnchere(articleVendu);
			StringBuffer date = new StringBuffer();
			date.append(articleVendu.getDateFinEncheres().toLocalDate().toString()).append(" ").append(articleVendu.getDateFinEncheres().toLocalTime().toString());

			article = new ArticleEnVente(articleVendu, derniereEnchere, user, date.toString());
			
			if(derniereEnchere != null) {
				userEnchere = utilisateurManager.postUser(derniereEnchere.getNoUtilisateur());
				article.setUserEnchere(userEnchere);
			}
			
			request.setAttribute("article", article);
			
			libelleCategorie = categorieManager.selectNoCategorie(articleVendu.getNoCategorie()).getLibelle();
			
			request.setAttribute("categorie", libelleCategorie);
			
			retrait = retraitManager.selectById(articleVendu.getNoArticle());
			
			request.setAttribute("retrait", retrait);
			
			if(articleVendu.getDateDebutEncheres().isBefore(LocalDateTime.now())) {
				checkDateDebut = false;
			}
			if(articleVendu.getDateFinEncheres().isAfter(LocalDateTime.now())) {
				checkDateFin = false;
			}
			
			request.setAttribute("checkDateDebut", checkDateDebut);
			request.setAttribute("checkDateFin", checkDateFin);
		
			this.getServletContext().getRequestDispatcher("/WEB-INF/detailEnchere.jsp").forward(request, response);
			
		} catch (BLLException e) {
			e.printStackTrace(); // TODO
		}
		
		this.getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		doGet(request, response);
	}
	
}
