package bdeb.models.dao;

import bdeb.models.entities.Utilisateur;

import java.util.List;

public interface IUtilisateurDAO {
    Utilisateur ajouterUtilisateur(String nomComplet);

    List<Utilisateur> trouverUtilisateur(int id);

    public List<Utilisateur> trouverUtilisateurs();
}
