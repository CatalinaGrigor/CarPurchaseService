package bdeb.models.dao;

import bdeb.models.entities.Utilisateur;
import jakarta.persistence.*;

import java.util.List;

public class UtilisateurDAO implements IUtilisateurDAO{

    EntityManagerFactory factory=null;
    EntityManager manager=null;

    public UtilisateurDAO() {
        factory= Persistence.createEntityManagerFactory("tpCatalina");
        manager= factory.createEntityManager();
    }

    @Override
    public Utilisateur ajouterUtilisateur(String nomComplet) {
        EntityTransaction transaction= manager.getTransaction();
        transaction.begin();
        Utilisateur nouveauUtilisateur=new Utilisateur(nomComplet);
        try{
            manager.persist(nouveauUtilisateur);
            transaction.commit();
            return nouveauUtilisateur;
        }
        catch(Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Utilisateur> trouverUtilisateur(int utilisateurID) {
        Query query= manager.createQuery("SELECT u FROM Utilisateur u WHERE u.utilisateurID=" +utilisateurID);
        return query.getResultList();
    }

    @Override
    public List<Utilisateur> trouverUtilisateurs() {
        Query query= manager.createQuery("SELECT u FROM Utilisateur u");
        return query.getResultList();
    }
}
