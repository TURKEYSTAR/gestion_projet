package com.projet.javaee.repositories;

import com.projet.javaee.entities.Participation;
import com.projet.javaee.model.ParticipationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, ParticipationId> {
    // Trouver toutes les participations d'un utilisateur spécifique
    List<Participation> findByUtilisateur_IdUtilisateur(Long idUtilisateur);

    // Trouver tous les participants d'un projet spécifique
    List<Participation> findByProjet_ProjectId(String projectId);
}
