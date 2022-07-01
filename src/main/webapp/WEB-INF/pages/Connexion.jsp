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

    <header>
        <div class="container-fluid">
            <div class="col 4 mx-auto text-center">
           
             <img src="Img/eni.png" alt="eni" id="imgEni" class="mx-auto">
            

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
        <a href="url a mettre">Mot de passe oublié</a>
        </div>
<div class="m">
    <input type="submit" value="Connexion" name="connexion">
</div>

<div class="m">

    <a href="moProfil.html">Pas encore de compte, cliquez ici</a>
</div>
</div class="error">

		listError="${listError}"
<div>

</div>
</form>
</body>
</html>