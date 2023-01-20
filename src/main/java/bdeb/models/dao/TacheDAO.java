package bdeb.models.dao;

import bdeb.models.entities.Tache;
import bdeb.models.entities.Utilisateur;
import jakarta.persistence.*;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TacheDAO implements ITacheDAO {

    EntityManagerFactory factory = null;
    EntityManager manager = null;

    public TacheDAO() {
        factory = Persistence.createEntityManagerFactory("tpCatalina");
        manager = factory.createEntityManager();
    }

    @Override
    public Tache ajouterTache(Tache tache) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        try {
            manager.persist(tache);
            transaction.commit();
            return tache;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Tache assignerTache(int utilisateurID, Tache tache) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        try {
            Utilisateur utilisateur = manager.find(Utilisateur.class, utilisateurID);
            tache.setUtilisateur(utilisateur);
            manager.persist(tache);
            transaction.commit();
            return tache;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Tache> trouverTache(int tacheID) {

        Query query = manager.createQuery("SELECT t FROM Tache t WHERE t.tacheID=" + tacheID);
        return query.getResultList();
    }

    @Override
    public List<Tache> trouverTaches() {

        Query query = manager.createQuery("SELECT t FROM Tache t");
        return query.getResultList();
    }

    @Override
    public Set<Tache> assignerTacheRepetitive(Tache tache, int utilisateurID, int nbRepet) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Set<Tache> result = new HashSet<Tache>();

        try {
            Utilisateur utilisateur = manager.find(Utilisateur.class, utilisateurID);
            tache.setUtilisateur(utilisateur);
            for (int i = 0; i <= nbRepet; i++) {
                Tache repetitive = new Tache(tache.getDescription(), tache.getDateCreation().plusDays(i * 7),
                        tache.getHeureCreation(), tache.getDuree(), tache.getUtilisateur());
                manager.persist(repetitive);
                result.add(repetitive);
            }
            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String assignerTacheConstraint(Tache tache, int utilisateurID) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        //Set<Tache> result = new HashSet<Tache>();

        try {
            Utilisateur utilisateur = manager.find(Utilisateur.class, utilisateurID);
            tache.setUtilisateur(utilisateur);
            Query query = manager.createQuery("SELECT t FROM Tache t where t.utilisateur.utilisateurID=" + utilisateurID);
            List<Tache> result = query.getResultList();

            for (int i = 0; i < result.size(); i++) {
                Tache item = result.get(i);
                if (!item.getDateCreation().isEqual(tache.getDateCreation())) {
                    manager.persist(tache);

                } else if (item.getDateCreation().isEqual(tache.getDateCreation())
                        && item.getHeureCreation().isBefore(tache.getHeureCreation()) ||
                        item.getHeureCreation().isAfter(tache.getHeureCreation().plusHours(tache.getDuree()))) {
                    manager.persist(tache);
                } else {
                    return "Chevauchement d'horaire";
                }
            }
            transaction.commit();
            return "Tache assigne avec succes, tenant compte des contraintes.";
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Set<Tache> trouverTachesUtilisateur(int utilisateurID) {
        Utilisateur utilisateur = manager.find(Utilisateur.class, utilisateurID);
        if (utilisateur != null) {
            return utilisateur.getTacheSet();
        }
        return null;
    }

    @Override
    public String supprimerTache(int tacheID) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            Tache tache = manager.find(Tache.class, tacheID);
            if (tache != null) {
                manager.remove(tache);
            }
            else{
                return "Tache avec inexistante!";
            }

            transaction.commit();
            return "Tache supprime avec succes!";
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;

    }
}
