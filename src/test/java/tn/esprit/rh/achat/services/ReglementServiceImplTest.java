package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class ReglementServiceImplTest {
    @InjectMocks
    private ReglementServiceImpl reglementService;

    @Mock
    private FactureRepository factureRepository;

    @Mock
    private ReglementRepository reglementRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddReglement() {
        Reglement reglement = new Reglement();

        // Configurer le mock pour que reglementRepository.save renvoie le Reglement que vous avez créé
        when(reglementRepository.save(reglement)).thenReturn(reglement);

        Reglement result = reglementService.addReglement(reglement);

        assertEquals(reglement, result);
    }
    @Test
    public void testRetrieveAllReglements() {
        List<Reglement> reglements = Arrays.asList(new Reglement(), new Reglement());
        when(reglementRepository.findAll()).thenReturn(reglements);

        List<Reglement> result = reglementService.retrieveAllReglements();

        assertEquals(2, result.size());
    }
    @Test
    public void testRetrieveReglement() {
        Long reglementId = 1L;
        Reglement reglement = new Reglement();
        when(reglementRepository.findById(reglementId)).thenReturn(Optional.of(reglement));

        Reglement result = reglementService.retrieveReglement(reglementId);

        assertEquals(reglement, result);
    }
    @Test
    public void testRetrieveReglementByFacture() {
        Long factureId = 1L;
        List<Reglement> reglements = Arrays.asList(new Reglement(), new Reglement());
        when(reglementRepository.retrieveReglementByFacture(factureId)).thenReturn(reglements);

        List<Reglement> result = reglementService.retrieveReglementByFacture(factureId);

        assertEquals(2, result.size());
    }
    @Test
    public void testGetChiffreAffaireEntreDeuxDate() {
        Date startDate = new Date();
        Date endDate = new Date();
        float chiffreAffaire = 1000.0f;
        when(reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(chiffreAffaire);

        float result = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);

        assertEquals(chiffreAffaire, result, 0.01);
    }
}
