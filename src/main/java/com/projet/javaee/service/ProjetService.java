package com.projet.javaee.service;

import com.projet.javaee.entities.Projet;
import com.projet.javaee.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProjetService {
    @Autowired
    private ProjetRepository projetRepository;

    public List<Projet> getAllProjets() { return projetRepository.findAll(); }

    public List<Projet> getProjetsByUser(Long userId) {
        return projetRepository.findProjetsByUtilisateurId(userId);
    }

    // Utile pour tes graphiques
    public Map<String, Long> getStatsParStatut() {
        return projetRepository.findAll().stream()
                .collect(Collectors.groupingBy(p -> p.getStatutProjet().name(), Collectors.counting()));
    }
}
