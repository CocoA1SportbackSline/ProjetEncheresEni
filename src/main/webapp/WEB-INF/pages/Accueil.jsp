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
    <header>

        <div class="container-fluid">
            <div class="col 4 mx-auto text-center">
           
             <img src="Img/eni.png" alt="eni" id="imgEni" class="mx-auto">
            

            </div>
        </div>
    </header>


    <h1>Liste des enchères</h1>

    <form action="">
        <div class="col-12">
            <div class="row">
                <div class="col-0 col-sm-0 col-md-0 col-lg-3 col-xl-3 "></div>
                    <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3  ">
                        <div class="mb-3">
                             <label for="Filtres" class="form-label">Filtres</label>
                             <input type="form-control" class="form-control" id="Filtres" >
                        </div>

                        <div>
                            <label for="Categorie">Catégorie :</label>
                            <select class="form-select" aria-label="Default select example">
                                <option selected>Toutes</option>
                                <option value="1">Informatique</option>
                                <option value="2">Ameublement</option>
                                <option value="3">Vêtement</option>
                                <option value="4">Sport&Loisirs</option>
                              </select>


                        </div>
                    </div>
                    <div class="rechercher">
                    <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3 ">
                        <div class="mb-3">
                           
                          <button type="submit">Rechercher</button>
                        </div>
                    </div>
                    </div>
                   
                </div>
                     
                </div>
            </div>

            
        </div>
        <div class="card">
        <div class="col-12">
            <div class="row">
                
                    <div class="col-12 col-sm-12 col-md-5 col-lg-4 col-xl-3  ">
                         <div class="card" style="width: 18rem;">
                             <img src="Img/maison.jpg" class="card-img-top" alt="...">
                         <div class="card-body">
                          <h5 class="card-title">A SAISIR</h5>
                          <p class="card-text">Maison 180m2 6 Pièces 2SDB sur 2000m2 de terrain</p>
                          <a href="" class="btn btn-primary">Encherir</a>
                    </div>
                
            </div>
        </div>
        </div>
    </form>
</body>
</html>