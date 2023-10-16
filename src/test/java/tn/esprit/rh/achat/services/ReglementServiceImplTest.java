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
}
