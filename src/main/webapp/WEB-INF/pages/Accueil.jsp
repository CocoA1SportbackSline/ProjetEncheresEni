<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil</title>
    <link rel="stylesheet" href="Css/bootstrap.min.css" />
    <link rel="stylesheet" href="Css/Accueil.css" />
</head>
<body>


<main id="bg">
        <div class="container pt-4" id="form">
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10 p-4">
                    <h1>Liste des Enchères</h1>
                    <form action="Accueil" method="post">
                        <div class="row mt-5">
                            <p>Filtres :</p>
                        </div>
                        <div class="row">
                            <div class="row">
                                <div class="col-6 inner-addon left-addon mb-4">
                                    <i class="glyphicon glyphicon-search"></i>
                                    <input class="form-control" type="text" name="skeyword" placeholder="Le nom de l'article contient">
                                </div>
                                <div class=" mb-3">
                                    <label>Catégorie : </label>
                                    <select class="selectpicker" name="scategorie">
                                        <option value="0">Toutes</option>
                                        <c:forEach var="cat" items="${listeCategorie}">
                                            <option value="${cat.noCategorie}">${cat.libelle}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <c:if test="${!empty sessionScope.myUser}">
                                    <div class="col-6 mt-3">
                                        <p>Achats</p>
                                        <div class="ms-3">
                                            <input class="form-check-input" type="radio" name="sachatsVentes" value="enchereOuverte" id="checkAchat1">
                                            <label for="checkAchat1">Enchères ouvertes</label> <br>
                                            <input class="form-check-input" type="radio" name="sachatsVentes" value="mesEncheres" id="checkAchat2">
                                            <label for="checkAchat2">Mes enchères</label> <br>
                                            <input class="form-check-input" type="radio" name="sachatsVentes" value="mesEncheresRemportees" id="checkAchat3">
                                            <label for="checkAchat3">Mes enchères remportées</label> <br>
                                        </div>
                                    </div>
                                    <div class="col-6 mt-3">
                                        <p>Mes Ventes</p>
                                        <div class="ms-3">
                                            <input class="form-check-input borderCheckBox" type="radio" name="sachatsVentes" value="mesVenteEnCours" id="checkVente1">
                                            <label for="checkVente1">Mes ventes en cours</label> <br>
                                            <input class="form-check-input borderCheckBox" type="radio" name="sachatsVentes" value="ventesNonDebutees" id="checkVente2">
                                            <label for="checkVente2">Ventes non débutées</label> <br>
                                            <input class="form-check-input borderCheckBox" type="radio" name="sachatsVentes" value="ventesTerminees" id="checkVente3">
                                            <label for="checkVente3">Ventes terminées</label> <br>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div class="mt-5">
                            <button class="btn btn-success me-2" type="submit">Rechercher</button>
                        </div>
                    </form>
                    </div>
                </div>
            </div>
            <div class="displayFlexRow mt-3">
                <c:if test="${!empty listeArticle}">
                    <div class="row" style="justify-content: center">
                        <c:forEach var="art" items="${listeArticle}">
                            <form class="m-3 box-vente" action="${empty sessionScope.myUser ? '' : './DetailEnchere' }" method="post">
                                <button class="btn btn-outline-success btn-vente" style="border: none" type="submit" name="snoarticle" value="${ art.article.noArticle}">
                                    <div class="row m-3">
                                        <div class="col-md-6">
                                            <img width="300" height="300" src="
                                            <c:url value="public/imageArticle/${empty art.article.image ? 'image-not-found.png' : art.article.image}">
                                            </c:url>" alt="image de l'article" class="img-thumbnail">
                                        </div>
                                        <div class="col-md-6" style="font-size:20px">
                                            <p style="font-weight: bold;text-decoration: underline">
                                            ${art.article.nomArticle}</p>
                                            <p>Prix : ${empty art.meilleurEnchere ? art.article.prixInitial : art.meilleurEnchere.montantEnchere} points</p>
                                            <p>Fin de l'enchère :  ${art.dateFin}</p>
                                            <p>Vendeur : ${art.user.pseudo}</p>
                                        </div>
                                    </div>
                                </button>
                            </form>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </main>
	
</body>
</html>