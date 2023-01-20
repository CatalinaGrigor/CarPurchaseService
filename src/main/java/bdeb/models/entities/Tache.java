package bdeb.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name="Tache")
public class Tache {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tacheID")
    private int tacheID;

    @Column(name="description")
    private String description;

    @Column(name="dateCreation")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)

    private LocalDate dateCreation;



    @Column(name="heureCreation")
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime heureCreation;

    @Column(name="duree")
    private int duree;

    @JoinColumn(name="utilisateurID", referencedColumnName = "utilisateurID")
    @ManyToOne
    @JsonIgnore
    private Utilisateur utilisateur;

    public Tache() {
//
    }

    public Tache(String description, LocalDate dateCreation, LocalTime heureCreation, int duree) {
//        this.TacheID = tacheID;
        this.description = description;
        this.dateCreation = dateCreation;
        this.heureCreation=heureCreation;
        this.duree = duree;


    }

    public Tache(String description, LocalDate dateCreation, LocalTime heureCreation, int duree, Utilisateur utilisateur) {
        this.description = description;
        this.dateCreation = dateCreation;
        this.heureCreation=heureCreation;
        this.duree = duree;
        this.utilisateur = utilisateur;
    }

    public LocalTime getHeureCreation() {
        return heureCreation;
    }

    public void setHeureCreation(LocalTime heureCreation) {
        this.heureCreation = heureCreation;
    }

    public int getTacheID() {
        return tacheID;
    }

    public void setTacheID(int tacheID) {
        this.tacheID = tacheID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

   public int setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
       return 0;
   }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
}
