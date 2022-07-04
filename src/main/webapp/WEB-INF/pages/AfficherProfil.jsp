<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="boostraps/bootstrap.min.css" />
    <link rel="stylesheet" href="Sidentifier.css" />
</head>
<body>
<h1>test afficher profil</h1>
 <main>
        <div>
        <form action="AfficherProfil" method="get">  
        <c:forEach var="id" items="${noUtilisateur}">
            <c:if test="${pseudo.equals(utilisateur.getPseudo())}">
                <table>
                <tr>
                    <td>Pseudo :</td>
                    <td>${noUtilisateur.getPseudo()}</td>
                </tr>
                <tr>
                    <td>Nom :</td>
                    <td>${noUtilisateur.getNom()}</td>
                </tr>
                <tr>
                    <td>Prénom :</td>
                    <td>${noUtilisateur.getPrenom()}</td>
                </tr>
                <tr>
                    <td>Email :</td>
                    <td>${noUtilisateur.getEmail()}</td>
                </tr>
                <tr>
                    <td>Téléphone :</td>
                    <td>${noUtilisateur.getTelephone()}</td>
                </tr>
                <tr>
                    <td>Rue :</td>
                    <td>${noUtilisateur.getRue()}</td>
                </tr>
                <tr>
                    <td>Code postal :</td>
                    <td>${noUtilisateur.getCodePostale()}</td>
                </tr>
                <tr>
                    <td>Ville :</td>
                    <td>${noUtilisateur.getVille()}</td>
                </tr>
            </table>
            </c:if>
        </c:forEach>
          </form>
        </div>
        <div>
            <c:if test="${pseudo.equals(pseudo)}">
                <form action="AfficherProfil" method="get">                 <!-- TODO lien vers la page de modif profil -->
                    <button>Modifier</button>                 <!-- TODO affichage button (c:if user=userEnCours) -->
                </form>
            </c:if>
        </div>

    </main>
</body>
</html>