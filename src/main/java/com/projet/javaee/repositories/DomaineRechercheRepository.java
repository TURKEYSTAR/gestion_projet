package com.projet.javaee.repositories;

import com.projet.javaee.entities.DomaineRecherche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DomaineRechercheRepository extends JpaRepository<DomaineRecherche, Long> {
    Optional<DomaineRecherche> findByNomDomaine(String nomDomaine);
}
