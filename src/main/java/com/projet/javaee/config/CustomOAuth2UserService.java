package com.projet.javaee.config;

import com.projet.javaee.entities.Role;
import com.projet.javaee.entities.Utilisateur;
import com.projet.javaee.repositories.RoleRepository;
import com.projet.javaee.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UtilisateurRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        String email = oauth2User.getAttribute("email");

        Utilisateur user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    Utilisateur newUser = new Utilisateur();
                    newUser.setEmail(email);
                    // On gère les cas où Google ne renvoie pas family_name ou given_name
                    String nom = oauth2User.getAttribute("family_name");
                    String prenom = oauth2User.getAttribute("given_name");
                    newUser.setNom(nom != null ? nom : (String) oauth2User.getAttribute("name"));
                    newUser.setPrenom(prenom != null ? prenom : "");

                    // Sécurité pour le rôle
                    Role role = roleRepository.findByNomRole("Candidat")
                            .orElseThrow(() -> new OAuth2AuthenticationException("Le rôle 'Candidat' n'existe pas en BDD !"));

                    newUser.setRole(role);
                    return userRepository.save(newUser);
                });

        return new CustomUserDetails(user, oauth2User.getAttributes());
    }
}
