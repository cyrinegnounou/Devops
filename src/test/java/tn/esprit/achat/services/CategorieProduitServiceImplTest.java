package tn.esprit.achat.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RunWith(MockitoJUnitRunner.class)
public class CategorieProduitServiceImplTest {
    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;
    @Mock
    private CategorieProduitRepository categorieProduitRepository;
    @Test

    public void testretrieveAllCategorieProduits(){
        List<CategorieProduit> sampleProduit = Arrays.asList(new CategorieProduit(), new CategorieProduit());

        when(categorieProduitRepository.findAll()).thenReturn(sampleProduit);

        List<CategorieProduit> result = categorieProduitService.retrieveAllCategorieProduits();

        verify(categorieProduitRepository, times(1)).findAll();

        assertEquals(sampleProduit, result);
    }
    @Test
    public void testaddCategorieProduit(){
        CategorieProduit sampleProduit = new CategorieProduit();

        when(categorieProduitRepository.save(sampleProduit)).thenReturn(sampleProduit);

        CategorieProduit result = categorieProduitService.addCategorieProduit(sampleProduit);

        verify(categorieProduitRepository, times(1)).save(sampleProduit);

        assertEquals(sampleProduit, result);
    }
    @Test
    public void testupdateCategorieProduit(){
        CategorieProduit sampleProduit = new CategorieProduit();

        when(categorieProduitRepository.save(sampleProduit)).thenReturn(sampleProduit);

        CategorieProduit result = categorieProduitService.updateCategorieProduit(sampleProduit);

        verify(categorieProduitRepository, times(1)).save(sampleProduit);

        assertEquals(sampleProduit, result);
    }
    @Test
    public void testretrieveCategorieProduit(){
        CategorieProduit sampleProduit = new CategorieProduit();

        when(categorieProduitRepository.findById(sampleProduit.getIdCategorieProduit())).thenReturn(Optional.of(sampleProduit));

        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(sampleProduit.getIdCategorieProduit());

        verify(categorieProduitRepository, times(1)).findById(sampleProduit.getIdCategorieProduit());

        assertEquals(sampleProduit, result);
    }

    @Test
    public void testDeleteDepartement() {
        CategorieProduit sampleProduit = new CategorieProduit();

        when(categorieProduitRepository.findById(sampleProduit.getIdCategorieProduit())).thenReturn(Optional.of(sampleProduit));

        categorieProduitService.deleteCategorieProduit(sampleProduit.getIdCategorieProduit());

        verify(categorieProduitRepository, times(1)).findById(sampleProduit.getIdCategorieProduit());
        verify(categorieProduitService, times(1)).deleteCategorieProduit(sampleProduit.getIdCategorieProduit());
    }

    }




