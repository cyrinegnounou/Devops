package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class ReglementServiceImplTest {

    private ReglementServiceImpl reglementService;

    @Mock
    private ReglementRepository reglementRepository;



    @Test
    public void testRetrieveAllReglements() {
        // Créez une liste factice de réglements pour simuler la base de données
        List<Reglement> reglements = Arrays.asList(new Reglement(), new Reglement());
        when(reglementRepository.findAll()).thenReturn(reglements);

        List<Reglement> result = reglementService.retrieveAllReglements();

        assertEquals(2, result.size());
    }

    @Test
    public void testAddReglement() {
        Reglement reglement = new Reglement();
        when(reglementRepository.save(any(Reglement.class))).thenReturn(reglement);

        Reglement result = reglementService.addReglement(reglement);

        assertEquals(reglement, result);
    }

    @Test
    public void testRetrieveReglement() {
        Long reglementId = 1L;
        Reglement reglement = new Reglement();
        when(reglementRepository.findById(reglementId)).thenReturn(java.util.Optional.of(reglement));

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
