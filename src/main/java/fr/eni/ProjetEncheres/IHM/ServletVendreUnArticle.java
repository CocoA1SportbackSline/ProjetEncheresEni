package fr.eni.ProjetEncheres.IHM;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.eni.ProjetEncheres.BLL.ArticleVenduManager;
import fr.eni.ProjetEncheres.BLL.BLLException;
import fr.eni.ProjetEncheres.BLL.CategorieManager;
import fr.eni.ProjetEncheres.BLL.RetraitManager;
import fr.eni.ProjetEncheres.BO.ArticleVendu;
import fr.eni.ProjetEncheres.BO.Categorie;
import fr.eni.ProjetEncheres.BO.Retrait;
import fr.eni.ProjetEncheres.BO.Utilisateur;


@WebServlet("/VendreUnArticle")
public class ServletVendreUnArticle extends HttpServlet{
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private static final String UPLOAD_DIR = "imageArticle";
		
		LocalDateTime date_debut_enchere = null;
		LocalDateTime date_fin_enchere = null;
		
		String sarticle = null; String sdecscription = null; String scategorie = null;
		String sphoto = null; String fileName = null;
		String sprix = null;
		String srue = null; String scode_postal = null; String sville = null;
		String sdate_debut = null; String sheure_debut = null; String sdate_fin = null; String sheure_fin = null;
		int intsprix = 0; int intscode_postal = 0; int intscategorie = 0;
		 
		private RetraitManager retraitManager;
		private ArticleVenduManager articleVenduManager;
		private CategorieManager categorieManager;
	       
		
		public void init() throws ServletException {
			articleVenduManager = ArticleVenduManager.getInstance();
			retraitManager = RetraitManager.getInstance();
			categorieManager = CategorieManager.getInstance();
	    	super.init();
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			List<Categorie> listCategorie = new ArrayList<>();
			
			String sdate_debut = null; String sheure_debut = null; String sdate_fin = null; String sheure_fin = null;
			
			Utilisateur user = (Utilisateur) request.getSession().getAttribute("myUser");
			
			if(user != null && !user.getPseudo().isEmpty()) {
				setAutomaticDate(request, response, sdate_debut, sheure_debut, sdate_fin, sheure_fin);
				
				try {
					listCategorie = categorieManager.selectall();
				} catch (BLLException e) {
					e.printStackTrace(); //TODO
				}
				
				request.setAttribute("categories", listCategorie);
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/VendreUnArticle.jsp").forward(request, response);
			}
				
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/Accueil.jsp").forward(request, response);	
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			List<String> listError = new ArrayList<>();
			List<Categorie> listCategorie = new ArrayList<>();
			
			try {
				listCategorie = categorieManager.selectall();
			} catch (BLLException e) {
				e.printStackTrace(); //TODO
			}
			
			request.setAttribute("categories", listCategorie);
			
			getParameterAndSetAttribute(request, response, listError);
				
			// TODO If listError not empty --> dispatch ?? la jsp

				// --> Telecharger photo dans dossier imageArticle
				// construit le chemin du r??pertoire pour enregistrer le fichier t??l??charg??

	
			/*	String uploadFilePath = request.getServletContext().getRealPath("") + "public" + File.separator + UPLOAD_DIR;
>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
				
				// cr??e le r??pertoire de sauvegarde s'il n'existe pas
		        File fileSaveDir = new File(uploadFilePath);
		        if (!fileSaveDir.exists()) {
		            fileSaveDir.mkdirs();
		        }
		
			// R??cup??re toutes les parties de la requ??te et les ??crit dans le fichier sur le serveur
				Part part = request.getPart("sphoto");
				fileName = getFileName(part);
				
				if(fileName != null && !fileName.isEmpty()) {
					part.write(uploadFilePath + File.separator + fileName);		
				}*/
			
			if((request.getParameter("sarticle")!=null)&&!request.getParameter("sarticle").isEmpty() && (request.getParameter("sdecscription")!=null)&&!request.getParameter("sdecscription").isEmpty() && 
					(request.getParameter("scategorie")!=null)&&!request.getParameter("scategorie").isEmpty() && (request.getParameter("sprix")!=null)&&!request.getParameter("sprix").isEmpty() && 
					(request.getParameter("srue")!=null)&&!request.getParameter("srue").isEmpty() && (request.getParameter("scode_postal")!=null)&& !request.getParameter("scode_postal").isEmpty() &&
					(request.getParameter("sville")!=null)&&!request.getParameter("sville").isEmpty() && (request.getParameter("sdate_debut")!=null)&&!request.getParameter("sdate_debut").isEmpty() &&
					(request.getParameter("sheure_debut")!=null)&&!request.getParameter("sheure_debut").isEmpty() &&(request.getParameter("sdate_fin")!=null)&& !request.getParameter("sdate_fin").isEmpty() &&
					(request.getParameter("sheure_fin")!=null)&&!request.getParameter("sheure_fin").isEmpty()
			) {
				
				// verifier format date envoyer et en fonction envoyer une erreur si 9:00 au lieu de 09:00
				// heure "hh:mm" : 5 car, 2 chiffres : 2 chiffres , pas sup ?? 23
				// date "yyyy/mm/dd"
			    if (request.getParameter("sheure_debut").length() != 5 
			    		&& request.getParameter("sheure_fin").length() != 5) {
					listError.add("L'heure doit ??tre au format '00:00'");
				}
			    
				if (request.getParameter("sdate_debut").length() != 10 
						&& request.getParameter("sdate_debut").length() != 10) {
					listError.add("La date doit ??tre au format 'AAAA/MM/JJ'");
				}

				
				//Recuperer les dates 
				date_debut_enchere = parseStringToLocalDate(request, response, request.getParameter("sdate_debut"), request.getParameter("sheure_debut"));
				date_fin_enchere = parseStringToLocalDate(request, response, request.getParameter("sdate_fin"), request.getParameter("sheure_fin"));
				
				Utilisateur user = (Utilisateur) request.getSession().getAttribute("myUser");

				Retrait retrait = new Retrait(srue, intscode_postal, sville);

				try {
					retraitManager.insertRetrait(retrait);
				} catch (BLLException e) {
					e.printStackTrace();
					//TODO
				}
			
				ArticleVendu articleVendu = null;
				if(fileName == null || fileName.isEmpty()) {
					articleVendu = new ArticleVendu(sarticle, sdecscription, date_debut_enchere, date_fin_enchere, 
							intsprix, user.getNoUtilisateur(), intscategorie, retrait.getNoRetrait()); 
				} else {
					articleVendu = new ArticleVendu(sarticle, sdecscription, date_debut_enchere, date_fin_enchere, 
							intsprix, fileName, user.getNoUtilisateur(), intscategorie, retrait.getNoRetrait()); 
				}
				
				if(date_debut_enchere.isBefore(LocalDateTime.now())) {
					listError.add("Date de d??but incorrect");
				} else {
					try {
						articleVenduManager.insertArticleVendu(articleVendu);
					} catch (BLLException e) {
						e.printStackTrace();
					}
				}
				
				listError.addAll(articleVenduManager.getListError());
				
				if(listError.isEmpty()) {
					this.getServletContext().getRequestDispatcher("WEB-INF/pages/Accueil.jsp").forward(request, response);
				}
				
			}
			
			
			request.setAttribute("listError", listError);
				
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/VendreUnArticle.jsp").forward(request, response);
		}
		
		/**
		 *
		 * recuperer les parametre et transmetre les attributs 
		 */
		protected void getParameterAndSetAttribute(HttpServletRequest request, HttpServletResponse response, List<String> listError) {
			
			if((request.getParameter("sarticle")!=null) && !request.getParameter("sarticle").isEmpty()) {
				sarticle = request.getParameter("sarticle");
				request.setAttribute("sarticle", sarticle);
			}
			if((request.getParameter("sdecscription")!=null)&&!request.getParameter("sdecscription").isEmpty()) {
				sdecscription = request.getParameter("sdecscription");
				request.setAttribute("sdecscription", sdecscription);
			}
			if((request.getParameter("scategorie")!=null)&&!request.getParameter("scategorie").isEmpty()) {
				scategorie = request.getParameter("scategorie");
				try {
				intscategorie = Integer.parseInt(scategorie);
			} catch(Exception e){
				listError.add("Erreur cat??gorie");
			}
				request.setAttribute("scategorie", scategorie);
			}
			if((request.getParameter("sprix")!=null)&&!request.getParameter("sprix").isEmpty()) {
				sprix = request.getParameter("sprix");
				try {
					intsprix = Integer.parseInt(sprix);
				} catch(Exception e){
					listError.add("Le prix saisi est incorrect");
				}
				request.setAttribute("sprix", sprix);
			}
			if((request.getParameter("srue")!=null)&&!request.getParameter("srue").isEmpty()) {
				srue = request.getParameter("srue");
				request.setAttribute("srue", srue);
			}
			if((request.getParameter("scode_postal")!=null)&&!request.getParameter("scode_postal").isEmpty()) {
				scode_postal = request.getParameter("scode_postal");
				try {
					intscode_postal = Integer.parseInt(scode_postal);
				} catch(Exception e){
					listError.add("Le Code postal saisi est incorrect");
				}
				request.setAttribute("scode_postal", scode_postal);
			}
			if((request.getParameter("sville")!=null)&&!request.getParameter("sville").isEmpty()) {
				sville = request.getParameter("sville");
				request.setAttribute("sville", sville);
			}
			if((request.getParameter("sdate_debut")!=null)&&!request.getParameter("sdate_debut").isEmpty()) {
				sdate_debut = request.getParameter("sdate_debut");
				request.setAttribute("sdate_debut", sdate_debut);
			}
			if((request.getParameter("sheure_debut")!=null)&&!request.getParameter("sheure_debut").isEmpty()) {
				sheure_debut = request.getParameter("sheure_debut");
				request.setAttribute("sheure_debut", sheure_debut);
			}
			if((request.getParameter("sdate_fin")!=null)&&!request.getParameter("sdate_fin").isEmpty()) {
				sdate_fin = request.getParameter("sdate_fin");
				request.setAttribute("sdate_fin", sdate_fin);
			}
			if((request.getParameter("sheure_fin")!=null)&&!request.getParameter("sheure_fin").isEmpty()) {
				sheure_fin = request.getParameter("sheure_fin");
				request.setAttribute("sheure_fin", sheure_fin);
			}
		}
			
		/**
		 *
		 * Parse String - LocalDate
		 */
		protected void setAutomaticDate(HttpServletRequest request, HttpServletResponse response,
			String sdate_debut, String sheure_debut, String sdate_fin, String sheure_fin) {
			LocalDate date_debut = LocalDate.now();
			LocalDate date_fin = LocalDate.now().plusDays(7);
			LocalTime heure = LocalTime.now().plusHours(1);
			String heureString = heure.toString().substring(0,2) + ":" + heure.toString().substring(3,5);
			
			sdate_debut = date_debut.toString();
			request.setAttribute("sdate_debut", sdate_debut);
			sdate_fin = date_fin.toString();
			request.setAttribute("sdate_fin", sdate_fin);
			sheure_debut = heureString;
			request.setAttribute("sheure_debut", heureString);
			sheure_fin = heureString;
			request.setAttribute("sheure_fin", heureString);
		}
		
		/**
		 *
		 * Parse String - LocalDate
		 */
		protected LocalDateTime parseStringToLocalDate(HttpServletRequest request, HttpServletResponse response,
				String dateString, String timeString) {
			LocalDateTime date = null;
			LocalDate localDate = null;
			LocalTime localTime = null;
			
			localDate = LocalDate.of(
					Integer.parseInt(dateString.substring(0,4)),
					Integer.parseInt(dateString.substring(5,7)),
					Integer.parseInt(dateString.substring(8,10))
					);
			
			localTime = LocalTime.of(
					Integer.parseInt(timeString.substring(0,2)), 
					Integer.parseInt(timeString.substring(3,5))
					);
			
			date = LocalDateTime.of(localDate, localTime);
			
//			date= LocalDateTime.of(				*Plus performant
//								Integer.parseInt(dateString.substring(0,4)),
//								Integer.parseInt(dateString.substring(5,7)),
//								Integer.parseInt(dateString.substring(8,10)),
//								Integer.parseInt(timeString.substring(0,2)), 
//								Integer.parseInt(timeString.substring(3,5))
//							);
			
			return date;
		}
		
		 /**
		 * source : journaldev.com - servlet 3 file - upload - multipartconfig-part 
	     * M????thode utilitaire pour obtenir le nom de fichier ???? partir de la disposition du contenu de l'en-t????te HTTP
	     */

	  /*  private String getFileName(Part part) {
>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
	        String contentDisp = part.getHeader("content-disposition");
//	      System.out.println("content-disposition header= "+contentDisp);
	        String[] tokens = contentDisp.split(";");
	        for (String token : tokens) {
	            if (token.trim().startsWith("filename")) {
	                return token.substring(token.indexOf("=") + 2, token.length()-1);
	            }
	        }
	        return "";
	    }*/
	}

