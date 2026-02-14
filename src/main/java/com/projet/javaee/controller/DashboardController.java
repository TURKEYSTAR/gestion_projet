package com.projet.javaee.controller;

import com.projet.javaee.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class DashboardController {

    @Autowired
    private ProjetService projetService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Données pour les graphiques
        Map<String, Long> statsStatut = projetService.getStatsParStatut();

        model.addAttribute("statsStatut", statsStatut);
        model.addAttribute("totalProjets", projetService.getAllProjets().size());

        return "admin/dashboard"; // Renvoie vers templates/admin/dashboard.html
    }
}
