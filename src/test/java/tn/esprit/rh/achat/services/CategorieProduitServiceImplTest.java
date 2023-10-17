package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategorieProduitServiceImplTest {

    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllCategorieProduits() {
        List<CategorieProduit> categorieProduits = new ArrayList<>();
        // Add logic to populate categorieProduits list as needed for the test
        when(categorieProduitRepository.findAll()).thenReturn(categorieProduits);

        List<CategorieProduit> result = categorieProduitService.retrieveAllCategorieProduits();

        assertEquals(categorieProduits, result);
    }

    @Test
    void testAddCategorieProduit() {
        CategorieProduit categorieProduit = new CategorieProduit();
        // Add logic to set properties of categorieProduit as needed for the test
        when(categorieProduitRepository.save(categorieProduit)).thenReturn(categorieProduit);

        CategorieProduit result = categorieProduitService.addCategorieProduit(categorieProduit);

        assertEquals(categorieProduit, result);
    }
    @Test
    void testDeleteCategorieProduit() {
        Long id = 1L;
        categorieProduitService.deleteCategorieProduit(id);

        verify(categorieProduitRepository, times(1)).deleteById(id);
    }

    @Test
    void testUpdateCategorieProduit() {
        CategorieProduit categorieProduit = new CategorieProduit();
        // Add logic to set properties of categorieProduit as needed for the test
        when(categorieProduitRepository.save(categorieProduit)).thenReturn(categorieProduit);

        CategorieProduit result = categorieProduitService.updateCategorieProduit(categorieProduit);

        assertEquals(categorieProduit, result);
    }

    @Test
    void testRetrieveCategorieProduit() {
        Long id = 1L;
        CategorieProduit categorieProduit = new CategorieProduit();
        // Add logic to set properties of categorieProduit as needed for the test
        when(categorieProduitRepository.findById(id)).thenReturn(Optional.of(categorieProduit));

        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(id);

        assertEquals(categorieProduit, result);
    }

}
