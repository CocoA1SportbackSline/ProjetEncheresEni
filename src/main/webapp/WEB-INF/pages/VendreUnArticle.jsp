<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="Css/bootstrap.min.css" />
    <link rel="stylesheet" href="Css/VendreUnArticle.css" />
</head>
<body>
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
                	<p>${ empty sessionScope.myUser ? '' : '<a class="nav-link active" href="./Deconnexion">Se Déconnecter</a>' }</p>
                </div>
            </div>
        </div>
    </div>
</header>



<form action="VendreUnArticle" method="post">
  <div class="col-12">
        <div class="row">
        
            <div class="col-12 col-sm-12 col-md-4 col-lg-4 col-xl-4  ">
                <div class="mb-3">
                     <label for="Pseudo" class="form-label">image de l'objet</object></label>
                    <!-- ICI IMG DE L'OBJET -->
                </div>
            </div>
            
            
                <div class="col-12 col-sm-12 col-md-4 col-lg-4 col-xl-4 ">
                    <div class="mb-3">
                        <label for="nom" class="form-label">Article :</label>
                        <input type="form-control" class="form-control" id="nom" required>
                    </div>
                    <div>
                        <label for="description">Description :</label><br />
                        <textarea name="description" id="description"></textarea>
                    </div>
                    <div class="cat" id="a">
                        <label for="Categorie" >Catégorie :</label>
                    <select class="form-select" aria-label="Default select example">
                            <option selected>Toutes</option>
                            <option value="1">Informatique</option>
                            <option value="2">Ameublement</option>
                            <option value="3">Vêtement</option>
                            <option value="4">Sport&Loisirs</option>
                    </select>
                     
                
                    <div>

                        <label for="uploadImg" class="form-label">Photo de l'article :</label>
                             <input type="file" />
                    </div>
                    <div id="a">

                        Mise à prix :  <input type="number" step="0" value="0" min="0" >
                    </div>
                    <div id="a">
                        <label>
                            Début de l'enchère:
                            <input type="date" name="debutEnchere">
                        </label>
                    </div>
                    <div id="a">
                        <label>
                            Fin de l'enchère : 
                            <input type="date" name="debutEnchere">
                        </label>
                    </div>
                    <fieldset>
                        <legend>Retrait</legend>
                        <label for="rue" class="form-label">Rue :</label>
                        <input type="form-control" class="form-control" id="rue" >
                        <label for="rue" class="form-label">Code postal :</label>
                        <input type="form-control" class="form-control" id="cp" >
                        <label for="rue" class="form-label">Ville :</label>
                        <input type="form-control" class="form-control" id="ville" >
                      </fieldset>
                      
                    <div>
                        
                        <button type="submit" class="btn btn-primary" id="btn1">Enregistrer</button>
                        <button type="submit" class="btn btn-primary" id="btn1">Annuler</button>
                        <button type="submit" class="btn btn-primary" id="btn2">Annuler la vente</button>

                    </div>
                
                </div>
                </div>
                <div class="col-12 col-sm-12 col-md-4 col-lg-4 col-xl-4 ">
                    <div class="mb-3">
                        
                    </div>
                </div>
            


        </Div>
    </Div>
</form>

  

</body>
</html>