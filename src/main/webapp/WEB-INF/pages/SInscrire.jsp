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
                     <label for="spseudo" class="form-label">Pseudo</label>
                     <input type="form-control" class="form-control" id="spseudo" name="spseudo" required >
                </div>
            </div>
            <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3 ">
                <div class="mb-3">
                   <label for="nomForm" class="form-label">Nom</label>
                  <input type="form-control" class="form-control" id="nomForm" name="nomForm" required>
                </div>
            </div>
        </div>
    <div class="row">
        <div class="col-0 col-sm-0 col-md-0 col-lg-3 col-xl-3 "></div>
            <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3  ">
                <div class="mb-3">
                     <label for="PrenomForm" class="form-label">Prenom</label>
                    <input type="form-control" class="form-control" id="PrenomForm" name="prenomForm" required>
                </div>    
            </div>
        
        <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3 ">
            <div class="mb-3">
                <label for="emailForm" class="form-label">Adresse email</label>
                <input type="email" class="form-control" id="emailForm" aria-describedby="emailHelp" name="emailForm" required>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-0 col-sm-0 col-md-0 col-lg-3 col-xl-3 "></div>
        
        <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3  ">
            <div class="mb-3">
                <label for="telephoneForm" class="form-label">Téléphone</label>
                <input type="form-control" class="form-control" id="telephoneForm" name="telephoneForm" required >
                
            </div>
        </div>
        <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3  ">
            <div class="mb-3">
                <label for="rueForm" class="form-label">Rue</label>
                <input type="form-control" class="form-control" id="rueForm" name="rueForm" required>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-0 col-sm-0 col-md-0 col-lg-3 col-xl-3 "></div>
        <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3  ">
        
            <div class="mb-3">
                <label for="postalForm" class="form-label">Code postal</label>
                <input type="form-control" class="form-control" id="postalForm" name="postalForm" required >
                
            </div>
        </div>
        <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3  ">
            <div class="mb-3">
                <label for="villeForm" class="form-label">Ville</label>
                <input type="form-control" class="form-control" id="villeForm" name="villeForm" required>
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
                
                <button type="submit"  value="SIscrire" name="SInscrire" class="btn btn-primary">Valider</button>
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




</body>
</html>