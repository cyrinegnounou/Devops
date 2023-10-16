package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.ReglementRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ReglementServiceImplTest {
    @InjectMocks
    private ReglementServiceImpl reglementService;

    @Mock
    private ReglementRepository reglementRepository;

    @Test
    public void testRetrieveAllReglements() {
        // Create fictitious data for testing
        Reglement reglement1 = new Reglement();
        Reglement reglement2 = new Reglement();
        List<Reglement> reglements = Arrays.asList(reglement1, reglement2);

        // Mock the behavior of the reglementRepository
        when(reglementRepository.findAll()).thenReturn(reglements);

        // Call the method under test
        reglementService.retrieveAllReglements();
    }

    @Test
    public void testAddReglement() {
        // Create a fictitious Reglement object for testing
        Reglement reglement = new Reglement();

        // Call the method under test
        reglementService.addReglement(reglement);

        // Verify that the save method was called once on the reglementRepository
        verify(reglementRepository, times(1)).save(reglement);
    }
}


