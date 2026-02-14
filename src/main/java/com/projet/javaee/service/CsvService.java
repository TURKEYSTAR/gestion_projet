package com.projet.javaee.service;

import com.projet.javaee.entities.Projet;
import com.projet.javaee.entities.DomaineRecherche;
import com.projet.javaee.model.StatutProjet;
import com.projet.javaee.repositories.DomaineRechercheRepository;
import com.projet.javaee.repositories.ProjetRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    @Autowired
    private ProjetRepository projetRepository;

    @Autowired
    private DomaineRechercheRepository domaineRepository;

    public void save(MultipartFile file) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Projet> projets = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (CSVRecord csvRecord : csvRecords) {
                // 1. Gérer le Domaine (le créer s'il n'existe pas)
                String nomDomaine = csvRecord.get("domaine");
                DomaineRecherche domaine = domaineRepository.findByNomDomaine(nomDomaine)
                        .orElseGet(() -> domaineRepository.save(new DomaineRecherche(nomDomaine)));

                // 2. Créer l'objet Projet
                Projet projet = new Projet();
                projet.setProjectId(csvRecord.get("project_id"));
                projet.setTitreProjet(csvRecord.get("titre_projet"));
                projet.setDescription(csvRecord.get("description"));
                projet.setDateDebut(LocalDate.parse(csvRecord.get("date_debut"), formatter));
                projet.setDateFin(LocalDate.parse(csvRecord.get("date_fin"), formatter));
                projet.setStatutProjet(StatutProjet.valueOf(csvRecord.get("statut_projet").toUpperCase().replace(" ", "_")));
                projet.setBudgetEstime(Double.parseDouble(csvRecord.get("budget_estime")));
                projet.setInstitution(csvRecord.get("institution"));
                projet.setResponsableProjet(csvRecord.get("responsable_projet"));
                projet.setNiveauAvancement(Integer.parseInt(csvRecord.get("niveau_avancement")));
                projet.setDomaine(domaine);

                projets.add(projet);
            }
            projetRepository.saveAll(projets);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du traitement du fichier CSV: " + e.getMessage());
        }
    }
}
