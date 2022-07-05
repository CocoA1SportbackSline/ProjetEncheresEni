package fr.eni.ProjetEncheres.IHM;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ProjetEncheres.BLL.ArticleVenduManager;
import fr.eni.ProjetEncheres.BLL.BLLException;
import fr.eni.ProjetEncheres.BLL.CategorieManager;
import fr.eni.ProjetEncheres.BLL.EnchereManager;
import fr.eni.ProjetEncheres.BLL.UtilisateurManager;
import fr.eni.ProjetEncheres.BO.ArticleEnVente;
import fr.eni.ProjetEncheres.BO.ArticleVendu;
import fr.eni.ProjetEncheres.BO.Categorie;
import fr.eni.ProjetEncheres.BO.Enchere;
import fr.eni.ProjetEncheres.BO.Utilisateur;


@WebServlet("/Accueil")
public class ServletAccueil extends HttpServlet {
	
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
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			List<ArticleVendu> listeArticleVendu = new ArrayList<>();
			List<ArticleEnVente> listeArticle = new ArrayList<>();
			List<Categorie> listeCategorie = new ArrayList<>();
			String keyword = null;
			int noCategorie = 0;
			
			Utilisateur myUser = (Utilisateur) request.getSession().getAttribute("myUser");
			
			String sachatsVentes = null;

			//Recuperer les parametres de recherche avancée 

			if(request.getParameter("sachatsVentes") != null && !request.getParameter("sachatsVentes").isEmpty()) {
				sachatsVentes = request.getParameter("sachatsVentes");
			}
			
			if(request.getParameter("scategorie") != null && !request.getParameter("scategorie").isEmpty()) {
			noCategorie = Integer.parseInt(request.getParameter("scategorie"));
			request.setAttribute("noCategorie", noCategorie);
			}

			if(request.getParameter("skeyword") != null && !request.getParameter("skeyword").isEmpty()) {
				keyword = request.getParameter("skeyword");
				request.setAttribute("keyword", keyword);
			}
						
			try {
				
				listeCategorie = categorieManager.selectall();
				request.setAttribute("listeCategorie", listeCategorie);
			}catch (BLLException e) {
				e.printStackTrace();
			}
			
			//affichage article sans recherche achats/mesventes
			if(sachatsVentes == null || sachatsVentes.equals("enchereOuverte")) {
				try {
					if(keyword == null && noCategorie == 0) {
						listeArticleVendu = articleVenduManager.selectAllArticle();
					}else if(keyword == null && noCategorie != 0){
						listeArticleVendu = articleVenduManager.selectByCategorie(noCategorie);
					}else if(keyword != null && noCategorie ==0) {
						listeArticleVendu = articleVenduManager.selectByArticle(keyword);
					}else if(keyword !=null && noCategorie !=0) {
						listeArticleVendu = articleVenduManager.selectByArticleAndCategorie(keyword, noCategorie);
					}
					
					if(!listeArticleVendu.isEmpty()) {
						for(ArticleVendu a : listeArticleVendu) {
							Enchere enchere = null;
							Utilisateur user = null;
							LocalDate date = a.getDateDebutEncheres().toLocalDate();
							LocalTime heure = a.getDateDebutEncheres().toLocalTime();
							StringBuffer datefmt = new StringBuffer();
							datefmt.append(date.toString()).append(" ").append(heure.toString());
							enchere = enchereManager.derniereEnchere(a);
							user = utilisateurManager.postUser(a.getNoUtilisateur());
							listeArticle.add(new ArticleEnVente(a, enchere, user, datefmt.toString()));
						}
				}
						
					request.setAttribute("listeArticle", listeArticle);
				}catch (BLLException e) {
					e.printStackTrace();
				}
			} else if (sachatsVentes.equals("ventesNonDebutees")) {
			//affichage de mes ventes non debutÃ©es
				try {
					listeArticleVendu = articleVenduManager.selectAllArticleVenteNonDebutee(myUser.getNoUtilisateur());

					
					if(!listeArticleVendu.isEmpty()) {
						for(ArticleVendu a : listeArticleVendu) {
							Enchere enchere = null;
							Utilisateur user = null;
							LocalDate date = a.getDateDebutEncheres().toLocalDate();
							LocalTime heure = a.getDateDebutEncheres().toLocalTime();
							StringBuffer datefmt = new StringBuffer();
							datefmt.append(date.toString()).append(" ").append(heure.toString());
							enchere = enchereManager.derniereEnchere(a);
							user = utilisateurManager.postUser(a.getNoUtilisateur());
							listeArticle.add(new ArticleEnVente(a, enchere, user, datefmt.toString()));
						}
				}
						
					request.setAttribute("listeArticle", listeArticle);
				}catch (BLLException e) {
					e.printStackTrace();
				}
			} else if (sachatsVentes.equals("mesVentesEnCours")) {
			//affichage de mes ventes en cours
				try {
					listeArticleVendu = articleVenduManager.selectAllArticleVenteEnCours(myUser.getNoUtilisateur());

					if(!listeArticleVendu.isEmpty()) {
						for(ArticleVendu a : listeArticleVendu) {
							Enchere enchere = null;
							Utilisateur user = null;
							LocalDate date = a.getDateDebutEncheres().toLocalDate();
							LocalTime heure = a.getDateDebutEncheres().toLocalTime();
							StringBuffer datefmt = new StringBuffer();
							datefmt.append(date.toString()).append(" ").append(heure.toString());
							enchere = enchereManager.derniereEnchere(a);
							user = utilisateurManager.postUser(a.getNoUtilisateur());
							listeArticle.add(new ArticleEnVente(a, enchere, user, datefmt.toString()));
						}
				}
						
					request.setAttribute("listeArticle", listeArticle);
				}catch (BLLException e) {
					e.printStackTrace();
				}
			} else if (sachatsVentes.equals("ventesTerminees")) {
			//affichage de mes ventes terminées
				try {
					listeArticleVendu = articleVenduManager.selectAllArticleVenteFini(myUser.getNoUtilisateur());

					if(!listeArticleVendu.isEmpty()) {
						for(ArticleVendu a : listeArticleVendu) {
							Enchere enchere = null;
							Utilisateur user = null;
							LocalDate date = a.getDateDebutEncheres().toLocalDate();
							LocalTime heure = a.getDateDebutEncheres().toLocalTime();
							StringBuffer datefmt = new StringBuffer();
							datefmt.append(date.toString()).append(" ").append(heure.toString());
							enchere = enchereManager.derniereEnchere(a);
							user = utilisateurManager.postUser(a.getNoUtilisateur());
							listeArticle.add(new ArticleEnVente(a, enchere, user, datefmt.toString()));
						}
				}
						
					request.setAttribute("listeArticle", listeArticle);
				}catch (BLLException e) {
					e.printStackTrace();
				}
			} else if (sachatsVentes.equals("mesEncheres")) {
			//affichage de mes Encheres
				try {
					listeArticleVendu = articleVenduManager.selectAllArticleAvecMesEncheres(myUser.getNoUtilisateur());

					if(!listeArticleVendu.isEmpty()) {
						for(ArticleVendu a : listeArticleVendu) {
							Enchere enchere = null;
							Utilisateur user = null;
							LocalDate date = a.getDateDebutEncheres().toLocalDate();
							LocalTime heure = a.getDateDebutEncheres().toLocalTime();
							StringBuffer datefmt = new StringBuffer();
							datefmt.append(date.toString()).append(" ").append(heure.toString());
							enchere = enchereManager.derniereEnchere(a);
							user = utilisateurManager.postUser(a.getNoUtilisateur());
							listeArticle.add(new ArticleEnVente(a, enchere, user, datefmt.toString()));
						}
				}
						
					request.setAttribute("listeArticle", listeArticle);
				}catch (BLLException e) {
					e.printStackTrace();
				}
			}else if (sachatsVentes.equals("mesEncheresRemportees")) {
			//affichage de mes Encheres Remportées
				try {
					listeArticleVendu = articleVenduManager.selectAllArticleEnchereRemporte(myUser.getNoUtilisateur());

					if(!listeArticleVendu.isEmpty()) {
						for(ArticleVendu a : listeArticleVendu) {
							Enchere enchere = null;
							Utilisateur user = null;
							LocalDate date = a.getDateDebutEncheres().toLocalDate();
							LocalTime heure = a.getDateDebutEncheres().toLocalTime();
							StringBuffer datefmt = new StringBuffer();
							datefmt.append(date.toString()).append(" ").append(heure.toString());
							enchere = enchereManager.derniereEnchere(a);
							user = utilisateurManager.postUser(a.getNoUtilisateur());
							listeArticle.add(new ArticleEnVente(a, enchere, user, datefmt.toString()));
						}
				}
						
					request.setAttribute("listeArticle", listeArticle);
				}catch (BLLException e) {
					e.printStackTrace();
				}
			}

			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/Accueil.jsp").forward(request, response);
		}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
