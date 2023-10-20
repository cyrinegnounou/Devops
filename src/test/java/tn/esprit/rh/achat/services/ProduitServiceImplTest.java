package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ProduitServiceImplTest {

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    @InjectMocks
    private ProduitServiceImpl produitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testretrieveAllProduits() {
        // Arrange
        List<Produit> produits = new ArrayList<>();
        produits.add(new Produit());
        produits.add(new Produit());
        when(produitRepository.findAll()).thenReturn(produits);

        // Act
        List<Produit> result = produitService.retrieveAllProduits();

        // Assert
        verify(produitRepository, times(1)).findAll();
        assert !result.isEmpty();
    }

    @Test
    void testaddProduit() {
        // Arrange
        Produit produit = new Produit();

        // Act
        produitService.addProduit(produit);

        // Assert
        verify(produitRepository, times(1)).save(produit);
    }

    @Test
    void testdeleteProduit() {
        // Arrange
        Long produitId = 1L;

        // Act
        produitService.deleteProduit(produitId);

        // Assert
        verify(produitRepository, times(1)).deleteById(produitId);
    }

    @Test
    void testupdateProduit() {
        // Arrange
        Produit produit = new Produit();

        // Act
        produitService.updateProduit(produit);

        // Assert
        verify(produitRepository, times(1)).save(produit);
    }

    @Test
    void testretrieveProduit() {
        // Arrange
        Long produitId = 1L;
        Produit produit = new Produit();
        when(produitRepository.findById(produitId)).thenReturn(Optional.of(produit));

        // Act
        Produit result = produitService.retrieveProduit(produitId);

        // Assert
        verify(produitRepository, times(1)).findById(produitId);
        assert result != null;
    }

    @Test
    void testassignProduitToStock() {
        // Arrange
        Long idProduit = 1L;
        Long idStock = 1L;
        Produit produit = new Produit();
        Stock stock = new Stock();
        when(produitRepository.findById(idProduit)).thenReturn(Optional.of(produit));
        when(stockRepository.findById(idStock)).thenReturn(Optional.of(stock));

        // Act
        produitService.assignProduitToStock(idProduit, idStock);

        // Assert
        verify(produitRepository, times(1)).save(produit);
        assert produit.getStock() == stock;
    }
}
