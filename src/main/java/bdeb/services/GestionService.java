package bdeb.services;

import bdeb.models.dao.ITacheDAO;
import bdeb.models.dao.IUtilisateurDAO;
import bdeb.models.dao.TacheDAO;
import bdeb.models.dao.UtilisateurDAO;
import bdeb.models.entities.Tache;
import bdeb.models.entities.Utilisateur;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Set;

@Path("gestion")
public class GestionService {
    IUtilisateurDAO utilisateurDAO=null;
    ITacheDAO tacheDAO=null;

    public GestionService() {
        utilisateurDAO=new UtilisateurDAO();
        tacheDAO=new TacheDAO();

    }


    @POST
    @Path("utilisateur/ajout")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur ajouterUtilisateur(String nomComplet){

        if(utilisateurDAO!=null){
            return utilisateurDAO.ajouterUtilisateur(nomComplet);
        }
        return null;
    }

    @POST
    @Path("tache/ajout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Tache ajouterTache(Tache tache){
        if(tacheDAO!=null){
            return tacheDAO.ajouterTache(tache);
        }
        return null;
    }
    @POST
    @Path("tache/ajout/{id}") //utilisateurID
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Tache assignerTache(@PathParam(("id")) int utilisateurID, Tache tache){
        if(tacheDAO!=null){
            return tacheDAO.assignerTache(utilisateurID,tache);
        }
        return null;
    }

    @POST
    @Path("tache/ajoutRepet/{id}/{nb}") //utilisateurID
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Tache> assignerTacheRepetitive(Tache tache, @PathParam(("id")) int utilisateurID, @PathParam(("nb")) int nbRepet){
        if(tacheDAO!=null){
            return tacheDAO.assignerTacheRepetitive(tache, utilisateurID, nbRepet);
        }
        return null;
    }

    @POST
    @Path("tache/ajoutConstraint/{id}") //utilisateurID
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String assignerTacheConstraint(Tache tache, @PathParam(("id")) int utilisateurID){
        if(tacheDAO!=null){
            return tacheDAO.assignerTacheConstraint(tache, utilisateurID);
        }
        return null;
    }

    @DELETE
    @Path("tache/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String supprimerTache(@PathParam("id") int id) {
        if (tacheDAO != null) {
            return tacheDAO.supprimerTache(id);
        }
        return null;
    }


}
