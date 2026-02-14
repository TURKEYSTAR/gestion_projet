package com.projet.javaee.repositories;

import com.projet.javaee.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    // Méthode critique pour OAuth : retrouver l'user via son email
    Optional<Utilisateur> findByEmail(String email);

    // Pour vérifier l'existence avant création
    Boolean existsByEmail(String email);
}
