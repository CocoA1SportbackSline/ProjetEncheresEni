<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Créer compte</title>
    <link rel="stylesheet" href="Css/bootstrap.min.css" />
    <link rel="stylesheet" href="Css/SInscrire.css" />
</head>
<body>
    <header>
        <div class="container-fluid">
            <div class="col 4 mx-auto text-center">
           
             <img src="Img/eni.png" alt="eni" id="imgEni" class="mx-auto">
            

            </div>
        </div>
    </header>
    <h1>Création de votre profil</h1>

<div class="container-fluid">
    <div class="container">
        

<form action="SInscrire" method="post">
  <div class="col-12">
    <div class="row">
        <div class="col-0 col-sm-2 col-md-2 col-lg-3 col-xl-3 "></div>
            <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3  ">
                <div class="mb-3">
                     <label for="Pseudo" class="form-label">Pseudo</label>
                     <input type="form-control" class="form-control" id="Pseudo" name="speudo" required >
                </div>
            </div>
            <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3 ">
                <div class="mb-3">
                   <label for="nom" class="form-label">Nom</label>
                  <input type="form-control" class="form-control" id="nom" name="nomForm" required>
                </div>
            </div>
        </div>
    <div class="row">
        <div class="col-0 col-sm-0 col-md-0 col-lg-3 col-xl-3 "></div>
            <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3  ">
                <div class="mb-3">
                     <label for="Prenom" class="form-label">Prenom</label>
                    <input type="form-control" class="form-control" id="Prenom" name="prenomForm" required>
                </div>    
            </div>
        
        <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3 ">
            <div class="mb-3">
                <label for="inputEmail" class="form-label">Adresse email</label>
                <input type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp" name="emailForm" required>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-0 col-sm-0 col-md-0 col-lg-3 col-xl-3 "></div>
        
        <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3  ">
            <div class="mb-3">
                <label for="telephone" class="form-label">Téléphone</label>
                <input type="form-control" class="form-control" id="telephone" name="telephoneForm" required >
                
            </div>
        </div>
        <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3  ">
            <div class="mb-3">
                <label for="rue" class="form-label">Rue</label>
                <input type="form-control" class="form-control" id="rue" name="rueForm" required>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-0 col-sm-0 col-md-0 col-lg-3 col-xl-3 "></div>
        <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3  ">
        
            <div class="mb-3">
                <label for="codePostal" class="form-label">Code postal</label>
                <input type="form-control" class="form-control" id="codePostal" name="postalForm" required >
                
            </div>
        </div>
        <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3  ">
            <div class="mb-3">
                <label for="ville" class="form-label">Ville</label>
                <input type="form-control" class="form-control" id="ville" name="villeForm" required>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-0 col-sm-0 col-md-0 col-lg-3 col-xl-3 "></div>
        
        <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3  ">
            <div class="mb-3">
                <label for="mdp" class="form-label">Mot de passe</label>
                <input type="password" class="form-control" id="mdp" name="mdp" required >
                
            </div>
        </div>
        <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3  ">
            <div class="mb-3">
                <label for="comfirmation" class="form-label">Confirmation</label>
                <input type="password" class="form-control" id="Confirmation" name="confirmation" required>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-0 col-sm-0 col-md-0 col-lg-3 col-xl-3 "></div>
        <div class="col-6 col-sm-6 col-md-5 col-lg-4 col-xl-3  ">
            <div class="mb-3">
                
                <button type="submit" class="btn btn-primary">Valider</button>
            </div>
        </div>
        <div class="col-6 col-sm-6 col-md-5 col-lg-4 col-xl-3  ">
            <div class="mb-3">
                <button type="button" class="btn btn-primary">Annuler</button>
            </div>
        </div>
    </div>
   </div> 
  
</form>
<!--
<div class="container-fluid" id="bg">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8 p-4" id="form">
                    <div class="row text-center">
                        <h1>Créer un compte</h1>
                        <br>
                        <h2>Mon profil</h2>
                        <c:forEach var="item" items="${listeDesErreurs}">
                            <p class="my-3" style="color:red">${item}</p>
                        </c:forEach>
                    </div>
                    <div class="row mb-4" id="form-sinscrire">
                        <form class="row" action="<%= request.getContextPath() %>/SInscrire" method="post">
                            <div class="col-md-6 p-3 mb-4">
                                <div class="input-group mb-3">
                                	<input type="text" class="form-control" name="pseudo" placeholder="Pseudo" value="${ empty pseudoForm ? '' : pseudoForm }">
                                </div>
                                <div class="input-group mb-3">
                                	<input type="text" class="form-control" name="prenom" placeholder="Prénom" 
                                	aria-label="Prenom" aria-describedby="basic-addon1" value="${ empty prenomForm? '' : prenomForm }">
                                </div>
                                <div class="input-group mb-3">
                                	<input type="text" class="form-control" name="telephone" placeholder="Téléphone" 
                                	aria-label="Telephone" aria-describedby="basic-addon1" value="${ empty telephoneForm? '' : telephoneForm }">
                                </div>
                                <div class="input-group mb-3">
                                	<input type="number" class="form-control" name="postal" placeholder="Code postal" 
                                	aria-label="Code postal" aria-describedby="basic-addon1" value="${ empty postalForm? '' : postalForm }">
                                </div>
                                <div class="input-group mb-3">
                                	<input type="password" class="form-control" name="mdp" placeholder="Mot de passe" 
                                	aria-label="Mot de passe" aria-describedby="basic-addon1">
                                </div>
                            </div>
                            <div class="col-md-6 p-3 mb-4">
                                <div class="input-group mb-3">
                                	<input type="text" class="form-control" name="nom" placeholder="Nom" aria-label="Nom" 
                                	aria-describedby="basic-addon1" value="${ empty nomForm? '' : nomForm }">
                                </div>
                                <div class="input-group mb-3">
                                	<input type="email" class="form-control" name="email" placeholder="Email" 
                                	aria-label="Email" aria-describedby="basic-addon1" value="${ empty emailForm? '' : emailForm }">
                                </div>
                                <div class="input-group mb-3">
                                	<input type="text" class="form-control" name="rue" placeholder="Rue" 
                                	aria-label="Rue" aria-describedby="basic-addon1" value="${ empty rueForm? '' : rueForm }">
                                </div>
                                <div class="input-group mb-3">
                                	<input type="text" class="form-control" name="ville" placeholder="Ville" 
                                	aria-label="Ville" aria-describedby="basic-addon1" value="${ empty villeForm? '' : villeForm }">
                                </div>
                                <div class="input-group mb-3">
                                	<input type="password" class="form-control" name="confirmation" 
                                	placeholder="Confirmation" aria-label="Confirmation" aria-describedby="basic-addon1">
                                </div>
                            </div>
                            <div class="text-center">
                                <div>
                                    <input class="btn btn-success me-2" type="submit" value="Creer">
                                    <a href="<%=request.getContextPath()%>">
                                        <input class="btn btn-danger ms-2" type="button" value="Annuler">
                                    </a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>
  -->


</body>
</html>