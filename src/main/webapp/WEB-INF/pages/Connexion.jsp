<link rel="stylesheet" href="Css/bootstrap.min.css" />
<link rel="stylesheet" href="Css/Connexion.css" />

<%@ include file="/WEB-INF/fragment/header.jsp"%>

<form action="Connexion" method="post">

	<div class="container-fluid" style="height: auto;">	
		<div class="l">


	<div class="l">

			<div class="col-3 col-md-3 col-sm-10  mx-auto text-center">
				<div class="mb-3">
					<label for="pseudoform" class="form-label">Pseudo</label> 
					<input type="text" class="form-control" id="pseudoform" name="pseudoform" required>
				</div>
				<div class="mb-3">
					<label for="passwordform" class="form-label">Mot de passe</label> 
					<input type="password" class="form-control" id="passwordform" name="passwordform" required>
				</div>
			</div>
			<div class="m">
				<label	class="form-check-label" for="check">Se souvenir de moi</label>	
				
			</div>
			<div class="m">
				<a href="url a mettre">Mot de passe oublié</a>
			</div>
			<div class="m">
				<input type="submit" value="Connexion" name="connexion">
			</div>
	
			<div class="m">
	
				<a href="SInscrire">Pas encore de compte, cliquez ici</a>
			</div>
	
	
			<p style="color: red">${listError}</p>
	
	
		</div>
	</div>	
</form>
</body>
</html>