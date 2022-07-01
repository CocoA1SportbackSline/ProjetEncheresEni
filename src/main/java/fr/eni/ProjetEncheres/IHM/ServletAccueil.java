package fr.eni.ProjetEncheres.IHM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ProjetEncheres.BLL.ArticleVenduManager;
import fr.eni.ProjetEncheres.BLL.CategorieManager;
import fr.eni.ProjetEncheres.BLL.EnchereManager;
import fr.eni.ProjetEncheres.BLL.UtilisateurManager;
import fr.eni.ProjetEncheres.BO.ArticleVendu;
import fr.eni.ProjetEncheres.BO.Categorie;
import fr.eni.ProjetEncheres.BO.Utilisateur;

public class ServletAccueil extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArticleVenduManager articleVenduManager;
	private CategorieManager categorieManager;
	private EnchereManager enchereManager;
	private UtilisateurManager utilisateurManager;

	public void init() throws ServletException {
		articleVenduManager = ArticleVenduManager.getInstance();
		categorieManager = CategorieManager.getInstance();
		enchereManager = EnchereManager.getInstance();
		utilisateurManager = UtilisateurManager.getInstance();
		super.init();
	}

	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	/*	List<ArticleVendu> listeArticleVendu = new ArrayList<>();
		List<Categorie> listeCategorie = new ArrayList<>();
		
		String keyword = null;
		int no_categorie = 0;
		
		Utilisateur myUser = (Utilisateur) request.getSession().getAttribute("myUser");
		
		String sachatsVentes = null;
		
		
		
		if(request.getParameter("scategorie") != null && !request.getParameter("scategorie").isEmpty()) {
		no_categorie = Integer.parseInt(request.getParameter("scategorie"));
		request.setAttribute("no_categorie", no_categorie);
		}
	*/
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
