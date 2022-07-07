<%@ include file="/WEB-INF/fragment/header.jsp"%>
<main id="bg">

<div id="form">
	<h1 align="center">Mon Profil</h1>

	<div class="text-danger row text-center">
		<c:forEach var="item" items="${ listError }">
			<p>${ item }</p>
		</c:forEach>
	</div>

	<form action="<%=request.getContextPath()%>/MonProfil" method="post">
		<div class="col-12">
			<div class="row my-2">
				<div class="col-0 col-sm-2 col-md-2 col-lg-3 col-xl-3"></div>
				<div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3">
					<div class="mb-3">
						<label for="idPseudo">Pseudo : </label> <input
							class="form-control" type="text" id="idPseudo" name="spseudo"
							value="${ myUser.pseudo }">
					</div>
				</div>
				<div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3">
					<div class="mb-3">
						<label for="idNom">Nom : </label> <input class="form-control"
							type="text" id="idNom" name="snom" value="${ myUser.nom }">
					</div>
				</div>
			</div>

			<div class="row my-2">
				<div class="col-0 col-sm-2 col-md-2 col-lg-3 col-xl-3"></div>
				<div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3">
					<div class="mb-3">
						<label for="idPrenom">Prenom : </label> <input
							class="form-control" type="text" id="idPrenom" name="sprenom"
							value="${ myUser.prenom }">
					</div>
				</div>
				<div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3">
					<div class="mb-3">
						<label for="idemail">Email : </label> <input class="form-control"
							type="text" id="idemail" name="semail" value="${ myUser.email }">
					</div>
				</div>
			</div>

			<div class="row my-2">
				<div class="col-0 col-sm-2 col-md-2 col-lg-3 col-xl-3"></div>
				<div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3">
					<div class="mb-3">
						<label for="idtelephone">Telephone : </label> <input
							class="form-control" type="text" id="idtelephone"
							name="stelephone" value="${ myUser.telephone }">
					</div>
				</div>
				<div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3">
					<div class="mb-3">
						<label for="idrue">Rue : </label> <input class="form-control"
							type="text" id="idrue" name="srue" value="${ myUser.rue }">
					</div>
				</div>
			</div>

			<div class="row my-2">
				<div class="col-0 col-sm-2 col-md-2 col-lg-3 col-xl-3"></div>
				<div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3">
					<div class="mb-3">
						<label for="idcodepostal">Code Postal : </label> <input
							class="form-control" type="text" id="idcodepostal"
							name="scodePostal" value="${ myUser.codePostal }">
					</div>
				</div>
				<div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3">
					<div class="mb-3">
						<label for="idville">Ville : </label> <input class="form-control"
							type="text" id="idville" name="sville" value="${ myUser.ville }">
					</div>
				</div>
			</div>

			<div class="row my-2">
				<div class="col-0 col-sm-2 col-md-2 col-lg-3 col-xl-3"></div>
				<div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3">
					<div class="mb-3">
						<label for="idmdpact">Mot de passe actuel : </label> <input
							class="form-control" type="password" id="idmdpact"
							name="spasswordact">
					</div>
				</div>
				<div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3">
					<div class="mb-3">
						<label for="idmdpnouv">Nouveau mot de passe : </label> <input
							class="form-control" type="password" id="idmdpnouv"
							name="spasswordnew">
					</div>
				</div>
			</div>
			<div class="row my-2">
				<div class="col-0 col-sm-2 col-md-2 col-lg-3 col-xl-3"></div>
				<div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3">
					<div class="mb-3">
						<label for="idmdpconf">Confirmation : </label> <input
							class="form-control" type="password" id="idmdpconf"
							name="spasswordconf">
					</div>
				</div>
				<div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3">
					<div class="mb-3"></div>
				</div>
				<div class="row my-2">
					<div class="col-0 col-sm-2 col-md-2 col-lg-3 col-xl-3"></div>
					<div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3">
						<div class="mb-3">
							<p>Crédit :</p>
						</div>
						<div class="col-4">
							<p>${ myUser.credit }</p>
						</div>
					</div>
					<div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3">
						<div class="mb-3"></div>
					</div>







					<div class="row my-1">
						<div class="col-3 offset-3">
							<button class="btn btn-success me-2" type="submit" name="supdate"
								value="ok">Enregistrer</button>
						</div>
						<div class="col-3">
							<button class="btn btn-danger ms-2" type="submit" name="sdelete"
								value="ok">Supprimer mon compte</button>
						</div>
					</div>
				</div>
			</div>
	</form>
</main>
<%@ include file="/WEB-INF/fragment/footer.jsp"%>