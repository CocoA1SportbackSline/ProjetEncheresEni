<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Css/bootstrap.min.css" />
<title>Insert title here</title>
</head>
<body>
	<header class="container-fluid">
		<div class="row" id="menu">
			<div class="col-md-1"></div>
			<div class="col-md-3">
				<a href="./Accueil"> <img alt="logo"
					src='<c:url value="public/images/logo-EniEncheres-long.png"></c:url>'>
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
						<p>${ empty sessionScope.myUser ? '' : '<a class="nav-link active" href="./MonProfil">Mon profil</a>' }</p>
					</div>
					<div class="col-md-3 btn-mobile">
						<p>${ empty sessionScope.myUser ? '<a class="nav-link active" href="./Connexion">S\'inscrire -<br> Se Connecter</a>' : '<a class="nav-link active"  href="./Deconnexion">Se Déconnecter</a>' }</p>
					</div>
				</div>
			</div>
		</div>
	</header>