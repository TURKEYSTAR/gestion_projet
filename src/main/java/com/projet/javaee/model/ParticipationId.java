package com.projet.javaee.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ParticipationId implements Serializable {
    private Long idUtilisateur;
    private String projectId;

    public ParticipationId() {}
    public ParticipationId(Long idUtilisateur, String projectId) {
        this.idUtilisateur = idUtilisateur;
        this.projectId = projectId;
    }

    // Equals et HashCode sont OBLIGATOIRES ici
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipationId that = (ParticipationId) o;
        return Objects.equals(idUtilisateur, that.idUtilisateur) &&
                Objects.equals(projectId, that.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur, projectId);
    }
}