$(document).ready(function () {
                
                    var session = new Object();
            session.todo = "recupererInfoPersonne";
            $.ajax({
            type: 'POST',
                    url: './ActionServlet',
                    data: session,
                    timeout: 3000,
                    dataType:'json',
                    success: function (data) {
                    console.log(data);
                    if (!data.session){
                    window.location.replace(
                            "./index.html");
            }
            else{
                    document.getElementById("deco").addEventListener("click", function () {
                        var deco=new Object();
                        deco.todo="seDeconnecter";
                    $.ajax({
                    type: 'POST',
                            url: './ActionServlet',
                            data: deco,
                            timeout: 3000,
                            dataType:'json',
                            success: function (data) {
                            console.log(data);
                            if (data.deconnexion){
                            window.location.replace(
                                    "./index.html");
            }
            else{

                                    $("#erreur").modal();
                    }
                    },
                    error: function (data) {
                    console.log(data);
                                    $("#erreur").modal();
                }
                });
                });
                     document.getElementById("bonjour").innerHTML="Bonjour, "+ data.prenom;
                                    var filtres = new Object();
                    filtres.todo = "afficherFilActualite";
                    //Type
                    filtres.type = "demande";
                    //Catégorie
                    filtres.categorie = "";
                    //Nom objet
                    filtres.objet = "";
                    //Date
                    filtres.date = "";
                    //Heure
                    filtres.time = "";
                    //Durée
                    filtres.duree = "";
                    //Unité durée
                    filtres.uniteDuree = "";
                    //Localisation
                    filtres.localisation = "";
                    //nbPts
                    filtres.nbPts = "";
                    //Unité prix
                    filtres.unitePrix = "";
                    console.log(filtres);
                    $.ajax({
                    type: 'GET',
                            url: './ActionServlet',
                            data: filtres,
                            timeout: 3000,
                            dataType:'json',
                            success: function (data) {
                            console.log(data);
                            if(data.Annonces.length==0){
                                         $('#fil').append('<p>Aucune annonce ne correspond à votre recherche</p>')
                                     }
                            for (var i in data.Annonces)
                            {
                           var annonce = '<div class="card mb-3"><h3 class="card-header">';
                           if(i<data.nbPropositions && !data.Annonces[i].memePersonne){
                               annonce+='<button class="btn"\n\
                                                onclick="'+'"'
                                                annonce+='><i class="fa fa-heart fa-2x" title="Susceptible de vous intéresser" style="color:red" ></i></button>';
                            }
                            if (data.Annonces[i].typeAnnonce == "offre"){
                            annonce += '<span style="color:#007bff" >OFFRE</span>';
                            }
                            else if (data.Annonces[i].typeAnnonce == "demande"){
                            annonce += '<span style="color:#f47d42" >DEMANDE</span>';
                            }

                            annonce += ' - Prêt - ' + data.Annonces[i].nomObjet;
                            
                            
                            annonce += '</h3><div class="card-body"><h6 class="card-subtitle text-muted">' + data.Annonces[i].categorie + '</h6>';
                            if (data.Annonces[i].images != undefined && data.Annonces[i].images.length != 0){
                            annonce += '<div id="carousel' + i + '" class="carousel slide" data-ride="carousel">\n\
                                        <div class="carousel-inner">\n\
                                        <div class="carousel-item active">\n\
                                    <img class="d-block w-100 center"  style="max-width:50%;max-height:20%" src="' + data.Annonces[i].images[0] + '" alt="First slide"></div>';
                            for (var j = 1; j < data.Annonces[i].images.length; j++){
                            annonce += '<div class="carousel-item">\n\
                                                   <img class="d-block w-100 center" style="max-width:50%;max-height:20%" src="' + data.Annonces[i].images[j] + '" alt="Second slide">\n\
                                                   </div>';
                            }
                            
                            annonce += '</div>\n\
                            <a class="carousel-control-prev" href="#carousel' + i + '" role="button" data-slide="prev">\n\
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>\n\
                                <span class="sr-only">Previous</span>\n\
                            </a>\n\
                            <a class="carousel-control-next" href="#carousel' + i + '" role="button" data-slide="next">\n\
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>\n\
                            <span class="sr-only">Next</span>\n\
                            </a>\n\
                            </div>';
                            }
                            else{
                                annonce += '<img class="d-block w-100 center" style="max-width:50%;max-height:20%" src="http://cliquecities.com/assets/no-image-e3699ae23f866f6cbdf8ba2443ee5c4e.jpg" >';
                            }
                            annonce += '</div><div><div class="card"><div class="card-header">\n\
                <h5 class="mb-0">\n\
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse' + i + '" aria-expanded="true" aria-controls="collapse' + i + '" id="accordion' + i + '">\n\
                Détails\n\
                </button>\n\
                    </h5>\n\
                        </div>\n\
                        <div id="collapse' + i + '" class="collapse" aria-labelledby="headingOne" data-parent="#accordion' + i + '">';
                            if (data.Annonces[i].description != undefined  && data.Annonces[i].description != ""){
                            annonce += '<div class="card-body">' + data.Annonces[i].description + '</div>';
                            }
                            var noteAuteur;
                            if(data.Annonces[i].noteMoyenneAuteur != -1){noteAuteur = data.Annonces[i].noteMoyenneAuteur }else{noteAuteur='Non noté'}
                    
                            annonce += '<ul class="list-group list-group-flush"><li class="list-group-item">Date de disponibilité : ' + data.Annonces[i].date + ' ' + data.Annonces[i].time + '</li>\n\
                                <li class="list-group-item">Durée de disponibilité : ' + data.Annonces[i].duree + ' ' + data.Annonces[i].uniteDuree + '</li>\n\
                            <li class="list-group-item">Prix : ' + data.Annonces[i].nbPts + ' points par ' + data.Annonces[i].unitePrix + '</li>\n\
                                <li class="list-group-item">Localisation : ' + data.Annonces[i].localisation + '</li>\n\
                                </ul></div></div><div class="card-footer text-muted">Contact : '+data.Annonces[i].auteur +'<br/>Note : '+noteAuteur+'<br/>Le ' + data.Annonces[i].datePublication + ' à ' + data.Annonces[i].timePublication; 
                                if(!data.Annonces[i].memePersonne){
                                    annonce+='<div class="text-right"><a href="#" onclick="$.ajax({\n\
                                type:'+" 'POST',\n\
                                        url: './ActionServlet',\n\
                                        data: {todo : 'signalerAnnonce', idAnnonce : "+ data.Annonces[i].idAnnonce +" },\n\
                                        timeout: 3000,\n\
                                        dataType:'json',\n\
                                        success: function (data) {\n\
                                        console.log(data);\n\
                                        if (data.signale){"+'\n\
                                            $('+"'#validationSignaler'"+').modal();\n\
                                        }\n\
                                        else{\n\
                                                $('+"'#erreur'"+').modal();\n\
                                        }\n\
                                        },\n\
                                        error: function () {\n\
                                                $('+"'#erreur'"+').modal();\n\
                                        }\n\
                                    });"  > Signaler</a></div>';
                                }
                                
                            annonce +='</div></div>';                                    
                            $('#fil').append(annonce);
                    }
                    },
                    error: function (data) {
                                    console.log(data);
                            $("#erreur").modal();
                    }
                    });
                            }
                            },
                        error: function () {
                                    $("#erreur").modal();
                }
                });
                    
                    
                    });