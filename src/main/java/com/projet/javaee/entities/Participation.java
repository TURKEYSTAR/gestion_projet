package com.projet.javaee.entities;

import com.projet.javaee.model.ParticipationId;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "participation")
public class Participation {

    @EmbeddedId
    private ParticipationId id = new ParticipationId();

    @ManyToOne
    @MapsId("idUtilisateur")
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Projet projet;

    private LocalDate dateAffectation;

    // Constructeurs
    public Participation() {
    }

    public Participation(Utilisateur user, Projet projet) {
        this.utilisateur = user;
        this.projet = projet;
        this.id = new ParticipationId(user.getIdUtilisateur(), projet.getProjectId());
        this.dateAffectation = LocalDate.now();
    }

    // Getters et Setters
    public ParticipationId getId() {
        return id;
    }

    public void setId(ParticipationId id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public LocalDate getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(LocalDate dateAffectation) {
        this.dateAffectation = dateAffectation;
    }
}
