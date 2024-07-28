package com.example.controller;

import com.example.model.Stagiaire;
import com.example.repository.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stagiaire")
@CrossOrigin(origins = "http://localhost:5173")
public class StagiaireController {

    @Autowired
    private StagiaireRepository stagiaireRepository;

    @PostMapping
    public Stagiaire addStagiaire(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("email") String email,
            @RequestParam("telephone") int telephone,
            @RequestParam("cin") String cin,
            @RequestParam("cne") String cne,
            @RequestParam("assurance") MultipartFile assurance,
            @RequestParam("cv") MultipartFile cv,
            @RequestParam("attestation") MultipartFile attestation
    ) throws IOException {
        Stagiaire stagiaire = new Stagiaire();
        stagiaire.setNom(nom);
        stagiaire.setPrenom(prenom);
        stagiaire.setEmail(email);
        stagiaire.setPhone(telephone);
        stagiaire.setCin(cin);
        stagiaire.setCne(cne);
        stagiaire.setAssuranceData(assurance.getBytes());
        stagiaire.setCvData(cv.getBytes());
        stagiaire.setAttestationData(attestation.getBytes());

        return stagiaireRepository.save(stagiaire);
    }

    @GetMapping
    public List<Stagiaire> getAllStagiaires() {
        return stagiaireRepository.findAll();
    }

    @GetMapping("/{id}/cv")
    public ResponseEntity<byte[]> getCv(@PathVariable Long id) {
        Optional<Stagiaire> stagiaireOptional = stagiaireRepository.findById(id);
        if (stagiaireOptional.isPresent()) {
            Stagiaire stagiaire = stagiaireOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"cv.pdf\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(stagiaire.getCvData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/assurance")
    public ResponseEntity<byte[]> getAssurance(@PathVariable Long id) {
        Optional<Stagiaire> stagiaireOptional = stagiaireRepository.findById(id);
        if (stagiaireOptional.isPresent()) {
            Stagiaire stagiaire = stagiaireOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"assurance.pdf\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(stagiaire.getAssuranceData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/attestation")
    public ResponseEntity<byte[]> getAttestation(@PathVariable Long id) {
        Optional<Stagiaire> stagiaireOptional = stagiaireRepository.findById(id);
        if (stagiaireOptional.isPresent()) {
            Stagiaire stagiaire = stagiaireOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"attestation.pdf\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(stagiaire.getAttestationData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
