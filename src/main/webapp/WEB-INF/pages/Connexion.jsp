<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
  <link rel="stylesheet" href="Css/bootstrap.min.css" />
    <link rel="stylesheet" href="Css/Connexion.css" />
</head>
<body>

<form action="Connexion" method="post">

    <header class="container-fluid">
    <div class="row" id="menu">
        <div class="col-md-1">
    	</div>
        <div class="col-md-3">
            <a href="./Accueil">
                <img alt="" src="Img/logo2 (3).png">
            </a>
        </div>
        <div class="col-md-8 pe-5">
            <div class="row m-3 text-white">
                <div class="col-md-3 pseudo">
                    <p>Bienvenue ${ myUser.pseudo } !</p>
                </div> 
                <div class="col-md-3 btn-mobile">
                <p>${ empty sessionScope.myUser ? '' : '<a class="nav-link active" href="./VendreUnArticle">Vendre un article</a>' }</p>
                </div>
                <div class="col-md-3 btn-mobile">
                    <p>${ empty sessionScope.myUser ? '' : '<a class="nav-link active" href="./AfficherProfil">Mon profil</a>' }</p>
                </div>
                <div class="col-md-3 btn-mobile">
   					<p>${ empty sessionScope.myUser ? '' : '<a class="nav-link active" href="./Connexion">Se Connecter</a>' } </p>								  
                </div>
                <div class="col-md-3 btn-mobile">
                	<p>${ empty sessionScope.myUser ? '' : '<a class="nav-link active" href="./Deconnexion">Se DÃ©connecter</a>' }</p>
                </div>
            </div>
        </div>
    </div>
</header>
    
<div class="l">
    <div class="col-3 col-md-3 col-sm-10  mx-auto text-center">
        <div class="mb-3">
            <label for="pseudoform" class="form-label">Pseudo</label>
            <input type="form-control" class="form-control" id="passwordform" name="pseudoform" required >
            
        </div>

        <div class="mb-3">
            <label for="passwordform" class="form-label">Mot de passe</label>
            <input type="password" class="form-control" id="passwordform" name="passwordform" required >
            
        </div>
    
    </div>
<div class="m">
        <input type="checkbox" class="form-check-input" id="check">
        <label class="form-check-label" for="check">Se souvenir de moi</label>
</div>
<div class="m">
        <a href="url a mettre">Mot de passe oubliÃ©</a>
        </div>
<div class="m">
    <input type="submit" value="Connexion" name="connexion">
</div>

<div class="m">

    <a href="SInscrire">Pas encore de compte, cliquez ici</a>
</div>


		 <p style="color:red">${listError}</p>


</div>
</form>
</body>
</html>