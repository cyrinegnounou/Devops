package tn.esprit.rh.achat.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class OperateurServiceImplTest {

 @InjectMocks
 private OperateurServiceImpl operateurService;

 @Mock
 private OperateurRepository operateurRepository;
 @BeforeEach
 public void setup() {
  MockitoAnnotations.openMocks(this);
 }

 @Test
 void testaddOperateur() {
  Operateur operateur = new Operateur();

  // Configurer le mock pour que reglementRepository.save renvoie le Reglement que vous avez créé
  when(operateurRepository.save(operateur)).thenReturn(operateur);

  Operateur result = operateurService.addOperateur(operateur);

  assertEquals(operateur, result);
 }
 @Test
 public void testupdateOperateur() {
  Operateur operateur = new Operateur();
  when(operateurRepository.save(operateur)).thenReturn(operateur);

  Operateur updatedOperateur = operateurService.updateOperateur(operateur);

  assertNotNull(updatedOperateur);
 }
 @Test
 void testDeleteOperateur() {
  Long stockId = 1L;
  doNothing().when(operateurRepository).deleteById(stockId);
  operateurService.deleteOperateur(stockId);
  verify(operateurRepository).deleteById(stockId);
 }

 @Test
 public void testretrieveOperateur() {
  Long id = 1L;
  Operateur operateur = new Operateur();
  when(operateurRepository.findById(id)).thenReturn(Optional.of(operateur));

  Operateur retrievedOperateur = operateurService.retrieveOperateur(id);

  assertNotNull(retrievedOperateur);
 }
 @Test
 public void testretrieveAllOperateurs() {
  when(operateurRepository.findAll()).thenReturn(Arrays.asList(new Operateur(), new Operateur()));

  List<Operateur> operateurs = operateurService.retrieveAllOperateurs();

  assertEquals(2, operateurs.size());
 }

}
