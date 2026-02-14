package com.projet.javaee.entities;

import com.projet.javaee.model.StatutProjet;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "projet")
public class Projet {

    @Id
    @Column(name = "project_id")
    private String projectId;

    @Column(nullable = false)
    private String titreProjet;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    private StatutProjet statutProjet;

    private Double budgetEstime;
    private String institution;
    private String responsableProjet;
    private Integer niveauAvancement; // 0 à 100

    @ManyToOne
    @JoinColumn(name = "id_domaine", nullable = false)
    private DomaineRecherche domaine;

    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    private List<Participation> participants;

    public Projet() {
    }

    public Projet(String projectId, String titreProjet, String description, LocalDate dateDebut, LocalDate dateFin, StatutProjet statutProjet, Double budgetEstime, String institution, String responsableProjet, Integer niveauAvancement) {
        this.projectId = projectId;
        this.titreProjet = titreProjet;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statutProjet = statutProjet;
        this.budgetEstime = budgetEstime;
        this.institution = institution;
        this.responsableProjet = responsableProjet;
        this.niveauAvancement = niveauAvancement;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTitreProjet() {
        return titreProjet;
    }

    public void setTitreProjet(String titreProjet) {
        this.titreProjet = titreProjet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public StatutProjet getStatutProjet() {
        return statutProjet;
    }

    public void setStatutProjet(StatutProjet statutProjet) {
        this.statutProjet = statutProjet;
    }

    public Double getBudgetEstime() {
        return budgetEstime;
    }

    public void setBudgetEstime(Double budgetEstime) {
        this.budgetEstime = budgetEstime;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getResponsableProjet() {
        return responsableProjet;
    }

    public void setResponsableProjet(String responsableProjet) {
        this.responsableProjet = responsableProjet;
    }

    public Integer getNiveauAvancement() {
        return niveauAvancement;
    }

    public void setNiveauAvancement(Integer niveauAvancement) {
        this.niveauAvancement = niveauAvancement;
    }

    public DomaineRecherche getDomaine() {
        return domaine;
    }

    public void setDomaine(DomaineRecherche domaine) {
        this.domaine = domaine;
    }

    public List<Participation> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participation> participants) {
        this.participants = participants;
    }
}
