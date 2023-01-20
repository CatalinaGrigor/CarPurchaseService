package bdeb.models.dao;

import bdeb.models.entities.Tache;

import java.util.List;
import java.util.Set;

public interface ITacheDAO {

    Tache ajouterTache(Tache tache);
    Tache assignerTache(int utilisateurID, Tache tache);

    List<Tache> trouverTache(int idTache);

    Set<Tache> trouverTachesUtilisateur(int idUtilisateur);

    String supprimerTache(int idTache);


    List<Tache> trouverTaches();

    Set<Tache> assignerTacheRepetitive(Tache tache, int utilisateurID, int nbRepet);

    String assignerTacheConstraint(Tache tache, int utilisateurID);
}
