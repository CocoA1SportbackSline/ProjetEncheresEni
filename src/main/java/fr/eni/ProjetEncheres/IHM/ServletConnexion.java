package fr.eni.ProjetEncheres.IHM;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.ProjetEncheres.BLL.BLLException;
import fr.eni.ProjetEncheres.BLL.UtilisateurManager;
import fr.eni.ProjetEncheres.BO.Utilisateur;
/**
 * Servlet implementation class ServletConnexion
 **/
@WebServlet("/Connexion")
public class ServletConnexion extends HttpServlet {
    private static final long serialVersionUID = 1L;
       private UtilisateurManager utilisateurManager;
       @Override
    public void init() throws ServletException {
        utilisateurManager = UtilisateurManager.getInstance();
        //Couplage faible !!!!
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utilisateur user = (Utilisateur) request.getSession().getAttribute("myUser");
        if(user != null && !user.getPseudo().isEmpty()) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/pages/Accueil.jsp").forward(request, response);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/pages/Connexion.jsp").forward(request, response);
    }
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> listError = new ArrayList<>();
        Utilisateur user = null;
        String pseudo =null;
        String motDepasse = null;
        String passwordError = null;
        String pseudoError = null;
        if(request.getParameter("pseudoform")!=null &&!request.getParameter("pseudoform").isEmpty() && request.getParameter("passwordform")!=null && !request.getParameter("passwordform").isEmpty()) {
            pseudo = request.getParameter("pseudoform");
            motDepasse = request.getParameter("passwordform");
        } else {
            if(!request.getParameter("pseudoform").isEmpty()) {
                pseudo = request.getParameter("pseudoform");
                request.setAttribute("pseudoform", pseudo);
                passwordError = "Renseigner un mot de passe";
            } else if(!request.getParameter("passwordform").isEmpty()) {
                pseudoError = "Renseigner un pseudo";
            }
            request.setAttribute("passwordError", passwordError);
            request.setAttribute("pseudoError", pseudoError);
            this.getServletContext().getRequestDispatcher("WEB-INF/pages/Accueil.jsp").forward(request, response);
        }
        try {
            user = utilisateurManager.connexionUser(pseudo, motDepasse);
        } catch (BLLException e) {
            request.setAttribute("pseudo", pseudo);
            listError = utilisateurManager.getListError();
            request.setAttribute("listError", "Pseudo ou Mot de passe non reconnu. Veuillez Reessayer");
            this.getServletContext().getRequestDispatcher("/WEB-INF/pages/Connexion.jsp").forward(request, response);
        }
                HttpSession session = request.getSession();
        session.setAttribute("myUser", user);
        this.getServletContext().getRequestDispatcher("/WEB-INF/pages/Accueil.jsp").forward(request, response);
    }
}