package com.projet.javaee.controller;

import com.projet.javaee.entities.Projet;
import com.projet.javaee.service.CsvService;
import com.projet.javaee.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/projets")
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @Autowired
    private CsvService csvService;

    // Affiche la liste des projets
    @GetMapping
    public String listeProjets(Model model, Principal principal) {
        // Principal contient les infos de l'utilisateur connecté via OAuth
        // Pour l'instant, si on n'a pas encore fini OAuth, on simule :
        List<Projet> projets = projetService.getAllProjets();

        // Plus tard, on fera :
        // if (user.isCandidat()) { projets = projetService.getProjetsByUser(id); }

        model.addAttribute("projets", projets);
        return "projets/list"; // Cherchera src/main/resources/templates/projets/list.html
    }

    // Page d'importation CSV
    @GetMapping("/import")
    public String showImportForm() {
        return "projets/import"; // Renvoie vers import.jsp
    }

    // Traitement de l'importation
    @PostMapping("/import")
    public String importCsv(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Veuillez sélectionner un fichier CSV.");
            return "redirect:/projets/import";
        }

        try {
            csvService.save(file);
            redirectAttributes.addFlashAttribute("message", "Fichier importé avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Erreur lors de l'import : " + e.getMessage());
        }

        return "redirect:/projets";
    }
}
