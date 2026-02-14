package com.projet.javaee.service;

import com.projet.javaee.entities.Utilisateur;
import com.projet.javaee.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur findOrCreateUser(String email, String nom, String prenom) {
        return utilisateurRepository.findByEmail(email)
                .orElseGet(() -> {
                    Utilisateur newUser = new Utilisateur();
                    newUser.setEmail(email);
                    newUser.setNom(nom);
                    newUser.setPrenom(prenom);
                    // Par défaut, on peut mettre le rôle CANDIDAT ici
                    return utilisateurRepository.save(newUser);
                });
    }
}
