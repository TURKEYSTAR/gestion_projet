package com.projet.javaee.repositories;

import com.projet.javaee.entities.Projet;
import com.projet.javaee.model.StatutProjet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, String> { // String car l'ID est un String (CSV)
    // 1. Pour le Candidat : Trouver uniquement les projets où il participe
    @Query("SELECT p FROM Projet p JOIN p.participants part WHERE part.utilisateur.idUtilisateur = :userId")
    List<Projet> findProjetsByUtilisateurId(@Param("userId") Long userId);

    // 2. Pour le Tableau de bord : Compter les projets par statut
    long countByStatutProjet(StatutProjet statut);

    // 3. Pour le Graphique "Projets par domaine"
    long countByDomaine_NomDomaine(String nomDomaine);

    // 4. Trouver les projets d'un domaine spécifique (Filtre)
    List<Projet> findByDomaine_NomDomaine(String nomDomaine);
}
