package com.projet.javaee.config;

import com.projet.javaee.entities.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class CustomUserDetails implements OAuth2User, UserDetails {

    private final Utilisateur utilisateur;
    private final Map<String, Object> attributes;

    // Constructeur utilisé par le CustomOAuth2UserService
    public CustomUserDetails(Utilisateur utilisateur, Map<String, Object> attributes) {
        this.utilisateur = utilisateur;
        this.attributes = attributes;
    }

    // --- C'est ICI que la magie des Rôles opère ---
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // On prend le nom du rôle en base (ex: "Administrateur")
        // Et on ajoute "ROLE_" devant pour Spring Security (ex: "ROLE_ADMINISTRATEUR")
        String nomRole = utilisateur.getRole().getNomRole().toUpperCase();
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + nomRole));
    }

    // --- Méthodes OAuth2User (Pour récupérer les infos Google) ---
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return utilisateur.getEmail(); // On utilise l'email comme identifiant principal
    }

    // --- Getter pour récupérer ton objet Utilisateur dans les contrôleurs ---
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    // --- Méthodes UserDetails (Obligatoires mais peu utilisées en OAuth pur) ---
    @Override
    public String getPassword() {
        return null; // Pas de mot de passe car géré par Google
    }

    @Override
    public String getUsername() {
        return utilisateur.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}