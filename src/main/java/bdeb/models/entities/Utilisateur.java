package bdeb.models.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="utilisateurID")
    private int utilisateurID;

    @Column(name="nomComplet")
    private String nomComplet;

    @OneToMany(mappedBy = "utilisateur")
    private Set<Tache> tacheSet;

    public Utilisateur() {
        tacheSet=new HashSet<>();
    }

    public Utilisateur(int utilisateurID, String nomComplet) {
        this.utilisateurID = utilisateurID;
        this.nomComplet = nomComplet;
       // this.tacheSet = tacheSet;
        tacheSet=new HashSet<>();
    }

    public Utilisateur(String nomComplet) {
        this.nomComplet = nomComplet;
        // this.tacheSet = tacheSet;
        tacheSet=new HashSet<>();
    }

    public int getUtilisateurID() {
        return utilisateurID;
    }

    public void setUtilisateurID(int utilisateurID) {
        utilisateurID = utilisateurID;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        nomComplet = nomComplet;
    }

    public Set<Tache> getTacheSet() {
        return tacheSet;
    }

    public void setTacheSet(Set<Tache> tacheSet) {
        this.tacheSet = tacheSet;
    }
}
