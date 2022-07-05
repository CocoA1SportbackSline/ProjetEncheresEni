<<<<<<< HEAD
<%@ include file="/WEB-INF/fragment/header.jsp" %>
=======

<%@ include file="/WEB-INF/fragment/header.jsp" %>

>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
<main>
<<<<<<< HEAD
    <div class="container-fluid" id="bg">
=======
	<div class="container-fluid" id="bg">
		<div class="row">

			<div class="col-md-2"></div>
				<div class="col-md-8 p-4 mb-4" id="form">
					<div class="row text-center">
						<h1>Vendre un article</h1>
						<br>
						<h2>Mon Article</h2>
						<c:forEach var="item" items="${listError}">
							<p class="my-3" style="color:red">${item}</p>
						</c:forEach>
					</div>
					<div class="row mb-4 ps-5 justify-content-center">
						<form class="row" action="<%= request.getContextPath() %>/VendreUnArticle" method="post" enctype="multipart/form-data" >
							<div class="col-md-3 p-1 mb-1">
								<div class="input-group mb-3">
									<label for="idarticle">Article </label>
								</div>
							</div>
							<div class="col-md-7 p-1 mb-1">
								<div class="input-group mb-3">
									<input type="text" class="form-control" id="idarticle"
									name="sarticle" value="${empty sarticle? '':sarticle}">
								</div>
							</div>
							<div class="col-md-3 p-1 mb-1">
								<div class="input-group mb-3">
									<label for="iddescription">Description </label>
								</div>
							</div>
							<div class="col-md-7 p-1 mb-1">
								<div class="input-group mb-3">
									<textarea class="form-control" id="iddescription" rows="3" 
									name="sdecscription">${empty sdecscription? '':sdecscription}</textarea>
								</div>
							</div>
							<div class="col-md-3 p-1 mb-1">
								<div class="input-group mb-3">
									<label for="idcategorie">Cat�gorie </label>
								</div>
							</div>
							<div class="col-md-7 p-1 mb-1">
								<div class="input-group mb-3">
									<select class="selectpicker" id="idcategorie" name="scategorie">
										<c:forEach var="item" items="${categories}">
											<option value="${item.noCategorie}">${item.libelle}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col-md-3 p-1 mb-1">
								<div class="input-group mb-3">
									<label for="idphoto">Photo de l'article </label>
								</div>
							</div>
							<div class="col-md-7 p-1 mb-1">
								<div class="input-group mb-3">
									<input type="file" class="form-control" id="idphoto" placeholder="a-tranformer-en-boutton-upload"
									name="sphoto" accept="image/png, image/jpeg">
								</div>
							</div>
							<div class="col-md-3 p-1 mb-1">
								<div class="input-group mb-3">
									<label for="idprix">Mise � prix </label>
								</div>
							</div>
							<div class="col-md-7 p-1 mb-1">
								<div class="input-group mb-3">
									<input type="number" min="0" class="form-control" id="idprix"
									name="sprix" value="${empty sprix? '':sprix}">
								</div>
							</div>
							<div class="col-md-3 p-1 mb-1">
								<div class="input-group mb-3">
									<label for="iddebutenchere">D�but de l'enchere </label>
								</div>
							</div>
							<div class="col-md-7 p-1 mb-1">
								<div class="input-group mb-3">
									<input type="date" class="form-control" id="iddebutenchere"
									name="sdate_debut" value="${empty sdate_debut? '':sdate_debut}">
									<input type="time" class="form-control" id="iddebutenchere"
									name="sheure_debut" value="${empty sheure_debut? '':sheure_debut}">
								</div>
							</div>
							<div class="col-md-3 p-1 mb-1">
								<div class="input-group mb-3">
									<label for="idfinenchere">Fin de l'enchere </label>
								</div>
							</div>
							<div class="col-md-7 p-1 mb-1">
								<div class="input-group mb-3">
									<input type="date" class="form-control" id="idfinenchere"
									name="sdate_fin" value="${empty sdate_fin? '':sdate_fin}">
									<input type="time" class="form-control" id="idfinenchere"
									name="sheure_fin" value="${empty sheure_fin? '':sheure_fin}">
								</div>
							</div>
							<div class="col-md-12 p-4">
								<div class="row text-center">
									<h3>Adresse retrait</h3>
									<div class="col-md-3 p-1 mb-1">
										<div class="input-group mb-3">
											<label for="idrue">Rue </label>
										</div>
									</div>
									<div class="col-md-7 p-1 mb-1">
										<div class="input-group mb-3">
											<c:if test="${empty srue}">
												<input type="text" class="form-control" id="idrue"
												name="srue" value="${sessionScope.myUser.rue}">
											</c:if>
											<c:if test="${!empty srue}">
												<input type="text" class="form-control" id="idrue"
												name="srue" value="${empty srue? '':srue}">
											</c:if>
										</div>
									</div>
									<div class="col-md-3 p-1 mb-1">
										<div class="input-group mb-3">
											<label for="idcodepostal">Code Postal </label>
										</div>
									</div>
									<div class="col-md-7 p-1 mb-1">
										<div class="input-group mb-3">
											<c:if test="${empty scode_postal}">
												<input type="number" class="form-control" id="idcodepostal"
												name="scode_postal" value="${sessionScope.myUser.codePostal}">
											</c:if>
											<c:if test="${!empty scode_postal}">
												<input type="number" class="form-control" id="idcodepostal"
												name="scode_postal" value="${empty scode_postal? '':scode_postal}">
											</c:if>
										</div>
									</div>
									<div class="col-md-3 p-1 mb-1">
										<div class="input-group mb-3">
											<label for="idville">Ville </label>
										</div>
									</div>
									<div class="col-md-7 p-1 mb-1">
										<div class="input-group mb-3">
											<c:if test="${empty sville}">
												<input type="text" class="form-control" id="idville"
												name="sville" value="${sessionScope.myUser.ville}">
											</c:if>
											<c:if test="${!empty sville}">
												<input type="text" class="form-control" id="idville"
												name="sville" value="${empty sville? '':sville}">
											</c:if>
										</div>
									</div>
									
								</div>
							</div>
							<div class="row p-4 mb-2 justify-content-center">
								<div class="col-md-2 text-center">	
									<input class="btn btn-success me-2" type="submit" value="Cr�er">
								</div>
								<div class="col-md-2 text-center">	
									<a href="<%=request.getContextPath()%>">
										<input class="btn btn-danger ms-2" type="button" value="Annuler">
									</a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

	</main>

</body>
</html>

<!-- <form action="VendreUnArticle" method="post">
  <div class="col-12">
>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
        <div class="row">
            <div class="col-md-2"></div>
                <div class="col-md-8 p-4 mb-4" id="form">
                    <div class="row text-center">
                        <h1>Vendre un article</h1>
                        <br>
                        <h2>Mon Article</h2>
                        <c:forEach var="item" items="${listError}">
                            <p class="my-3" style="color:red">${item}</p>
                        </c:forEach>
                    </div>
                    <div class="row mb-4 ps-5 justify-content-center">
                        <form class="row" action="<%= request.getContextPath() %>/VendreUnArticle" method="post" enctype="multipart/form-data" >
                            <div class="col-md-3 p-1 mb-1">
                                <div class="input-group mb-3">
                                    <label for="idarticle">Article </label>
                                </div>
                            </div>
                            <div class="col-md-7 p-1 mb-1">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" id="idarticle"
                                    name="sarticle" value="${empty sarticle? '':sarticle}">
                                </div>
                            </div>
                            <div class="col-md-3 p-1 mb-1">
                                <div class="input-group mb-3">
                                    <label for="iddescription">Description </label>
                                </div>
                            </div>
                            <div class="col-md-7 p-1 mb-1">
                                <div class="input-group mb-3">
                                    <textarea class="form-control" id="iddescription" rows="3"
                                    name="sdecscription">${empty sdecscription? '':sdecscription}</textarea>
                                </div>
                            </div>
                            <div class="col-md-3 p-1 mb-1">
                                <div class="input-group mb-3">
                                    <label for="idcategorie">Cat�gorie </label>
                                </div>
                            </div>
                            <div class="col-md-7 p-1 mb-1">
                                <div class="input-group mb-3">
                                    <select class="selectpicker" id="idcategorie" name="scategorie">
                                        <c:forEach var="item" items="${categories}">
                                            <option value="${item.noCategorie}">${item.libelle}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3 p-1 mb-1">
                                <div class="input-group mb-3">
                                    <label for="idphoto">Photo de l'article </label>
                                </div>
                            </div>
                            <div class="col-md-7 p-1 mb-1">
                                <div class="input-group mb-3">
                                    <input type="file" class="form-control" id="idphoto" placeholder="a-tranformer-en-boutton-upload"
                                    name="sphoto" accept="image/png, image/jpeg">
                                </div>
                            </div>
                            <div class="col-md-3 p-1 mb-1">
                                <div class="input-group mb-3">
                                    <label for="idprix">Mise � prix </label>
                                </div>
                            </div>
                            <div class="col-md-7 p-1 mb-1">
                                <div class="input-group mb-3">
                                    <input type="number" min="0" class="form-control" id="idprix"
                                    name="sprix" value="${empty sprix? '':sprix}">
                                </div>
                            </div>
                            <div class="col-md-3 p-1 mb-1">
                                <div class="input-group mb-3">
                                    <label for="iddebutenchere">D�but de l'enchere </label>
                                </div>
                            </div>
                            <div class="col-md-7 p-1 mb-1">
                                <div class="input-group mb-3">
                                    <input type="date" class="form-control" id="iddebutenchere"
                                    name="sdate_debut" value="${empty sdate_debut? '':sdate_debut}">
                                    <input type="time" class="form-control" id="iddebutenchere"
                                    name="sheure_debut" value="${empty sheure_debut? '':sheure_debut}">
                                </div>
                            </div>
                            <div class="col-md-3 p-1 mb-1">
                                <div class="input-group mb-3">
                                    <label for="idfinenchere">Fin de l'enchere </label>
                                </div>
                            </div>
                            <div class="col-md-7 p-1 mb-1">
                                <div class="input-group mb-3">
                                    <input type="date" class="form-control" id="idfinenchere"
                                    name="sdate_fin" value="${empty sdate_fin? '':sdate_fin}">
                                    <input type="time" class="form-control" id="idfinenchere"
                                    name="sheure_fin" value="${empty sheure_fin? '':sheure_fin}">
                                </div>
                            </div>
                            <div class="col-md-12 p-4">
                                <div class="row text-center">
                                    <h3>Adresse retrait</h3>
                                    <div class="col-md-3 p-1 mb-1">
                                        <div class="input-group mb-3">
                                            <label for="idrue">Rue </label>
                                        </div>
                                    </div>
                                    <div class="col-md-7 p-1 mb-1">
                                        <div class="input-group mb-3">
                                            <c:if test="${empty srue}">
                                                <input type="text" class="form-control" id="idrue"
                                                name="srue" value="${sessionScope.myUser.rue}">
                                            </c:if>
                                            <c:if test="${!empty srue}">
                                                <input type="text" class="form-control" id="idrue"
                                                name="srue" value="${empty srue? '':srue}">
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="col-md-3 p-1 mb-1">
                                        <div class="input-group mb-3">
                                            <label for="idcodepostal">Code Postal </label>
                                        </div>
                                    </div>
                                    <div class="col-md-7 p-1 mb-1">
                                        <div class="input-group mb-3">
                                            <c:if test="${empty scode_postal}">
                                                <input type="number" class="form-control" id="idcodepostal"
                                                name="scode_postal" value="${sessionScope.myUser.code_postal}">
                                            </c:if>
                                            <c:if test="${!empty scode_postal}">
                                                <input type="number" class="form-control" id="idcodepostal"
                                                name="scode_postal" value="${empty scode_postal? '':scode_postal}">
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="col-md-3 p-1 mb-1">
                                        <div class="input-group mb-3">
                                            <label for="idville">Ville </label>
                                        </div>
                                    </div>
                                    <div class="col-md-7 p-1 mb-1">
                                        <div class="input-group mb-3">
                                            <c:if test="${empty sville}">
                                                <input type="text" class="form-control" id="idville"
                                                name="sville" value="${sessionScope.myUser.ville}">
                                            </c:if>
                                            <c:if test="${!empty sville}">
                                                <input type="text" class="form-control" id="idville"
                                                name="sville" value="${empty sville? '':sville}">
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row p-4 mb-2 justify-content-center">
                                <div class="col-md-2 text-center">
                                    <input class="btn btn-success me-2" type="submit" value="Cr�er">
                                </div>
                                <div class="col-md-2 text-center">
                                    <a href="<%=request.getContextPath()%>">
                                        <input class="btn btn-danger ms-2" type="button" value="Annuler">
                                    </a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
<<<<<<< HEAD
        </div>
    </main>
</body>
</html>
=======
            
            
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
                        <label for="Categorie" >Cat�gorie :</label>
                    <select class="form-select" aria-label="Default select example">
                            <option selected>Toutes</option>
                            <option value="1">Informatique</option>
                            <option value="2">Ameublement</option>
                            <option value="3">V�tement</option>
                            <option value="4">Sport&Loisirs</option>
                    </select>
                     
                
                    <div>

                        <label for="uploadImg" class="form-label">Photo de l'article :</label>
                             <input type="file" />
                    </div>
                    <div id="a">

                        Mise � prix :  <input type="number" step="0" value="0" min="0" >
                    </div>
                    <div id="a">
                        <label>
                            D�but de l'ench�re:
                            <input type="date" name="debutEnchere">
                        </label>
                    </div>
                    <div id="a">
                        <label>
                            Fin de l'ench�re : 
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
-->
>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
