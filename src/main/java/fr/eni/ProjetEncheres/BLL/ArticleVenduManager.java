package fr.eni.ProjetEncheres.BLL;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.BO.ArticleVendu;
import fr.eni.ProjetEncheres.BO.Enchere;
import fr.eni.ProjetEncheres.BO.Utilisateur;
import fr.eni.ProjetEncheres.DAL.ArticleVenduDAO;
import fr.eni.ProjetEncheres.DAL.DALException;
import fr.eni.ProjetEncheres.DAL.DAOFactory;
import fr.eni.ProjetEncheres.DAL.EnchereDAO;
import fr.eni.ProjetEncheres.DAL.UtilisateurDAO;

public class ArticleVenduManager {
	

	private static ArticleVenduManager articleVenduManager;
	private ArticleVenduDAO articleVenduDAO;
	private EnchereDAO enchereDAO;
	private UtilisateurDAO utilisateurDAO;
	
	private static List<String> listError;
	
	private ArticleVenduManager() {
		this.articleVenduDAO = DAOFactory.getArticleVenduDao();
		this.enchereDAO = DAOFactory.getEnchereDao();
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public static ArticleVenduManager getInstance() {
		if(articleVenduManager == null) {
			articleVenduManager = new ArticleVenduManager();
		}
		return articleVenduManager;
	}
	
	public List<String> getListError() {
		return listError;
	}
	
	public void insertArticleVendu(ArticleVendu av) throws BLLException {
		listError = new ArrayList<>();
		
		try {
			articleVenduDAO.insert(av);
		} catch (DALException e) {
			listError.add("impossible d'enregistrer cette article");
			throw new BLLException("echec method insertArticleVendu");
		}
	}
	
	public void updateArticleVendu(ArticleVendu av) throws BLLException {
		listError = new ArrayList<>();
		
		try {
			articleVenduDAO.update(av);
		} catch (DALException e) {
			listError.add("impossible de modifier cette article");
			throw new BLLException("echec method updateArticleVendu");
		}
	}
	
	
	
	
	public List<ArticleVendu> selectAllArticleVenteNonDebutee(int idUtilisateur) throws BLLException {
		listError = new ArrayList<>();
		List<ArticleVendu> list = new ArrayList<>();
		
		try {
			list = articleVenduDAO.selectAllBeforeDate(idUtilisateur);
		} catch (DALException e) {
			throw new BLLException("echec method selectAllArticleVenteNonDebutee");
		}
		
		if(list.isEmpty()) {
			listError.add("Aucun article trouv??e");
		}
		
		return list;
	}
	
	
	public List<ArticleVendu> selectAllArticleVenteFini(int idUtilisateur) throws BLLException {
		listError = new ArrayList<>();
		List<ArticleVendu> list = new ArrayList<>();
		
		try {
			list = articleVenduDAO.selectAllSoldOut(idUtilisateur);
		} catch (DALException e) {
			throw new BLLException("echec method selectAllArticleVenteFini");
		}
		
		if(list.isEmpty()) {
			listError.add("Aucun article trouv??e");
		}
		
		return list;
	}
	

	/*
	 * Recherche par no_article
	 * 
	 */
	public ArticleVendu selectArticleById(int idArticle) throws BLLException {
		listError = new ArrayList<>();
		ArticleVendu article = null;
		
		try {
			article = articleVenduDAO.selectByID(idArticle);
		} catch (DALException e) {
			throw new BLLException("echec method selectArticleById");
		}
		
		if(article==null) {
			listError.add("Aucun article trouv??");
		}
		
		return article;
	}

	protected List<String> returnListString(String keyWords) {
		List<String> list = new ArrayList<>();
		String keyWord;
		int y = 0;
		
		for(int i =1; i< keyWords.length(); i++) {
			if(keyWords.charAt(i)==' ' && keyWords.charAt(i-1)!=' ') {
				keyWord = keyWords.substring(y, i).trim();
				y=i+1;
				i++;
				list.add(keyWord);
			}else if (i == keyWords.length()-1) {
				keyWord = keyWords.substring(y, i+1).trim();
				list.add(keyWord);
			}
		}

		return list;
	}
	public List<ArticleVendu> selectByArticleAndCategorie(String keyWords, int idCategorie) throws BLLException {
		listError = new ArrayList<>();
		List<ArticleVendu> list = new ArrayList<>();
		List<ArticleVendu> listTemp = new ArrayList<>();
		
		List<String> listKeyWord = returnListString(keyWords);
		
		for (String keyWord : listKeyWord) {
			try {
				listTemp = articleVenduDAO.selectByKeyWordAndNoCategorie(keyWord, idCategorie);
			} catch (DALException e) {
				e.printStackTrace();
				throw new BLLException("echec method selectByArticleAndCategorie");
			}
			for (ArticleVendu article : listTemp) {
				list.add(article);
			}
		}
		
		if(list.isEmpty()) {
			listError.add("Aucun ??l??ment trouv?? pour cette recherche");
		}
	
		return validerVente(list);
	}
	

	
	/**
	 *  Recherche par mots cl?? : mot1 ou mot2 ... (dans nom_article ou description)
	 */
	public List<ArticleVendu> selectByArticle(String keyWords) throws BLLException {
		listError = new ArrayList<>();
		List<ArticleVendu> list = new ArrayList<>();
		List<ArticleVendu> listTemp = new ArrayList<>();
		
		List<String> listKeyWord = returnListString(keyWords);
		
		for (String keyWord : listKeyWord) {
			try {
				listTemp = articleVenduDAO.selectByKeyWord(keyWord);
			} catch (DALException e) {
				throw new BLLException("echec method selectByArticle");
			}
			for (ArticleVendu article : listTemp) {
				list.add(article);
			}
		}
		
		if(list.isEmpty()) {
			listError.add("Aucun ??l??ment trouv?? pour cette recherche");
		}
	
		return validerVente(list);
	}
	public List<ArticleVendu> selectAllArticle() throws BLLException {
		listError = new ArrayList<>();
		List<ArticleVendu> list = new ArrayList<>();
		
		try {
			list = articleVenduDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException("echec method selectAllArticle");
		}
		
		if(list.isEmpty()) {
			listError.add("Aucun article trouv??");
		}
		
		return validerVente(list);
	}
	
	protected List<ArticleVendu> validerVente(List<ArticleVendu> listAValider) {
		List<ArticleVendu> listValider = new ArrayList<>();
			
		for (ArticleVendu a : listAValider) {
			if(a.getDateFinEncheres().isBefore(LocalDateTime.now())) {
				Enchere enchere = null;
				try {
					enchere = enchereDAO.recupEnchereMax(a.getNoArticle());
				} catch (DALException e) {
					e.printStackTrace();
				}
				if(enchere != null) {
					a.setPrixVente(enchere.getMontantEnchere());
					try {
						Utilisateur vendeur = utilisateurDAO.getselectByID(a.getNoUtilisateur());
						vendeur.setCredit(vendeur.getCredit() + enchere.getMontantEnchere());
						utilisateurDAO.update(vendeur);
					} catch (DALException e) {
						e.printStackTrace();
					}
				} else {
					a.setPrixVente(0);
				}
				try {
					articleVenduDAO.update(a);
				} catch (DALException e) {
					e.printStackTrace();
				}
			} else {
				listValider.add(a);
			}
		}
		
		return listValider;
	}
	
	
	public List<ArticleVendu> selectByCategorie(int idCategorie) throws BLLException {
		listError = new ArrayList<>();
		List<ArticleVendu> list = new ArrayList<ArticleVendu>();
		
		try {
			list = articleVenduDAO.selectByNoCategorie(idCategorie);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("echec method selectByCategorie");
		}
		
		if(list.isEmpty()) {
			listError.add("Aucun ??l??ment trouv?? pour cette recherche");
		}
		
		return validerVente(list);
	}
	
	public List<ArticleVendu> selectAllArticleVenteEnCours(int idUtilisateur) throws BLLException {
		listError = new ArrayList<>();
		List<ArticleVendu> list = new ArrayList<>();
		
		try {
			list = articleVenduDAO.selectAllBetweenDate(idUtilisateur);
		} catch (DALException e) {
			throw new BLLException("echec method selectAllArticleVenteEnCours");
		}
		
		if(list.isEmpty()) {
			listError.add("Aucun article trouv??");
		}
		
		return list;
	}
	
	public List<ArticleVendu> selectAllArticleAvecMesEncheres(int idUtilisateur) throws BLLException {
		listError = new ArrayList<>();
		List<ArticleVendu> list = new ArrayList<>();
		List<ArticleVendu> listComplete = new ArrayList<>();
		
		try {
			listComplete = articleVenduDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException("echec method selectAllArticleAvecMesEncheres");
		}
		
		for (ArticleVendu av : listComplete) {
			try {
				Enchere enchere = null;
				enchere = enchereDAO.recupEnchereMax(av.getNoArticle());
				if(enchere != null && enchere.getNoUtilisateur() == idUtilisateur) {
					list.add(av);
				}
			} catch (DALException e) {
				e.printStackTrace();
			}
		}
		
		if(list.isEmpty()) {
			listError.add("Aucun article trouv??");
		}
		
		return list;
	}
	

	public List<ArticleVendu> selectAllArticleEnchereRemporte(int idUtilisateur) throws BLLException {
		listError = new ArrayList<>();
		List<ArticleVendu> list = new ArrayList<>();
		List<ArticleVendu> listComplete = new ArrayList<>();
		
		try {
			listComplete = articleVenduDAO.selectAllSoldOut();
		} catch (DALException e) {
			throw new BLLException("echec method selectAllArticleAvecMesEncheres");
		}
		
		for (ArticleVendu av : listComplete) {
			try {
				Enchere enchere = null;
				enchere = enchereDAO.recupEnchereMax(av.getNoArticle());
				if(enchere != null && enchere.getNoUtilisateur() == idUtilisateur) {
					list.add(av);
				}
			} catch (DALException e) {
				e.printStackTrace();
			}
		}
		
		if(list.isEmpty()) {
			listError.add("Aucun article trouv??");
		}
		
		return list;
	}
	
}
