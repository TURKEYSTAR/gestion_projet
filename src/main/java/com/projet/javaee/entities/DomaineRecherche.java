package com.projet.javaee.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "domaine_recherche")
public class DomaineRecherche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDomaine;

    @Column(nullable = false, unique = true)
    private String nomDomaine;

    public DomaineRecherche() {
    }

    public DomaineRecherche(String nomDomaine) {
        this.nomDomaine = nomDomaine;
    }

    public DomaineRecherche(Long idDomaine, String nomDomaine) {
        this.idDomaine = idDomaine;
        this.nomDomaine = nomDomaine;
    }

    public Long getIdDomaine() {
        return idDomaine;
    }

    public void setIdDomaine(Long idDomaine) {
        this.idDomaine = idDomaine;
    }

    public String getNomDomaine() {
        return nomDomaine;
    }

    public void setNomDomaine(String nomDomaine) {
        this.nomDomaine = nomDomaine;
    }
}
