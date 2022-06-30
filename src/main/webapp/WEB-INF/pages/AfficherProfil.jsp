<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="/ServletAfficherProfil" method="get">
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
	
		<c:forEach var="utilisateur" items="${utilisateurss}">
			<tr>
				<td>${utilisateur.pseudo}</td>
				<td>${utilisateur.nom}</td>
				<td>${utilisateur.prenom}</td>
				<td>${utilisateur.email}</td>
				<td>${utilisateur.pseudo}</td>
				<td>${utilisateur.pseudo}</td>
				<td><a href="<%=request.getContextPath()%>/catalogue?action=supprimer&idPizza=${pizza.id}">Supprimer</a> - 
				<a href="<%=request.getContextPath()%>/catalogue?action=modifier&idPizza=${pizza.id}">Modifier</a></td>				    
			</tr>
	</c:forEach>
	</form>
</body>
</html>