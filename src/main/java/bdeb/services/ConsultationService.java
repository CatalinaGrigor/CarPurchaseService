package bdeb.services;

import bdeb.models.dao.ITacheDAO;
import bdeb.models.dao.IUtilisateurDAO;
import bdeb.models.dao.TacheDAO;
import bdeb.models.dao.UtilisateurDAO;
import bdeb.models.entities.Tache;
import bdeb.models.entities.Utilisateur;
import com.mysql.cj.util.Util;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Set;

@Path("consultation")
public class ConsultationService {

    IUtilisateurDAO utilisateurDAO=null;
    ITacheDAO tacheDAO=null;

    public ConsultationService() {
        utilisateurDAO=new UtilisateurDAO();
        tacheDAO=new TacheDAO();

    }

    @GET
    @Path("utilisateur")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> trouverUtilisateurs(){
        if(utilisateurDAO!=null){
            return utilisateurDAO.trouverUtilisateurs();
        }
        return null;
    }

    @GET
    @Path("utilisateur/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> trouverUtilisateur(@PathParam("id") int id){
        if(utilisateurDAO!=null){
            return utilisateurDAO.trouverUtilisateur(id);
        }
        return null;
    }


    @GET
    @Path("tache")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tache> trouveTaches(){
        if(tacheDAO!=null){
            return tacheDAO.trouverTaches();
        }
        return null;
    }
    @GET
    @Path("tache/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tache> trouverTache(@PathParam("id") int id){
        if(tacheDAO!=null){
            return tacheDAO.trouverTache(id);
        }
        return null;
    }

    @GET
    @Path("utilisateur/{id}/taches")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Tache> trouverTachesUtilisateur (@PathParam("id") int id){ //id de la banque
        if(tacheDAO!=null){
            return tacheDAO.trouverTachesUtilisateur(id);
        }
        return null;
    }


}
