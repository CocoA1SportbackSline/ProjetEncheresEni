<%@ include file="/WEB-INF/fragment/header.jsp"%>
<main>
	<form action="Connexion" method="post">

		<div class="container-fluid" style="height: auto;">
			<div class="l">
				<div class="col-3 col-md-3 col-sm-10  mx-auto text-center">
					<div class="mb-3">
						<label for="pseudoform" class="form-label">Pseudo</label> <input
							type="text" class="form-control" id="pseudoform"
							placeholder="Votre pseudo" name="pseudoform" required>
					</div>
					<div class="mb-3">
						<label for="passwordform" class="form-label">Mot de passe</label>
						<input type="password" class="form-control" id="passwordform"
							placeholder="Votre mot de passe" name="passwordform" required>
					</div>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="checkbox" value=""
						id="flexCheckDefault"> <label class="form-check-label"
						for="flexCheckDefault"> Se souvenir de moi </label>
				</div>
				<div class="m">
					<a href="url a mettre">Mot de passe oublié</a>
				</div>
				<div class="m">
					<button type="submit" value="Connexion" name="connexion">Connexion</button>
				</div>
				<div class="m">
					<a href="SInscrire">Pas encore de compte, cliquez ici</a>
				</div>
				<p style="color: red">${listError}</p>
			</div>
		</div>
	</form>
</main>
<%@ include file="/WEB-INF/fragment/footer.jsp"%>