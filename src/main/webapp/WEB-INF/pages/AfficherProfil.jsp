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
 <header>
        <div class="container-fluid">
            <div class="col 4 mx-auto text-center">
             <img src="img/eni.png" alt="eni" id="imgEni" class="mx-auto">
            

            </div>
        </div>
    </header>
	
	<form action="/ServletAfficherProfil" method="get">
	<div class="l">
    <div class="col-3 col-md-3 col-sm-10  mx-auto text-center">
        <div class="mb-3">
	<div>
		<label for="pseudo">Pseudo : </label>
		<input type="text" name="pseudo" id="pseudo">
	</div>
	<div>
		<label for="nom">Nom : </label>
		<input type="text" name="nom" id="nom">
	</div>
	<div>
		<label for="prenom">Prénom : </label>
		<input type="text" name="prenom" id="prenom">
	</div>
	<div>
		<label for="email">Email : </label>
		<input type="text" name="email" id="email">
	</div>
	<div>
		<label for="telephone">Téléphone : </label>
		<input type="text" name="telephone" id="telephone">
	</div>
	<div>
		<label for="rue">Rue : </label>
		<input type="text" name="rue" id="rue">
	</div>
	<div>
		<label for="codePostal">Code postal : </label>
		<input type="text" name="codePostal" id="codePostal">
	</div>
	<div>
		<label for="ville">Ville : </label>
		<input type="text" name="ville" id="ville">
	</div>
	   </div>
    
    </div>
	
		<c:forEach var="utilisateur" items="${utilisateurs}">
			<tr>
				<td>${utilisateur.pseudo}</td>
				<td>${utilisateur.nom}</td>
				<td>${utilisateur.prenom}</td>
				<td>${utilisateur.email}</td>
				<td>${utilisateur.telephone}</td>
				<td>${utilisateur.codePostal}</td>
				<td>${utilisateur.ville}</td>
				<td><a href="<%=request.getContextPath()%>/catalogue?action=supprimer&idPizza=${pizza.id}">Supprimer</a> - 
				<a href="<%=request.getContextPath()%>/catalogue?action=modifier&idPizza=${pizza.id}">Modifier</a></td>				    
			</tr>
	</c:forEach>
	</form>
</body>
</html>