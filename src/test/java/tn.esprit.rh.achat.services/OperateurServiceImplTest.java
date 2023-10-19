package tn.esprit.rh.achat.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OperateurServiceImplTest {

 private OperateurServiceImpl operateurService;
 private OperateurRepository operateurRepository;

 @Before
 public void setUp() {
  operateurRepository = mock(OperateurRepository.class);
  operateurService = new OperateurServiceImpl();
 }

 @Test
 public void testRetrieveAllOperateurs() {
  when(operateurRepository.findAll()).thenReturn(Arrays.asList(new Operateur(), new Operateur()));

  List<Operateur> operateurs = operateurService.retrieveAllOperateurs();

  assertEquals(2, operateurs.size());
 }

 @Test
 public void testAddOperateur() {
  Operateur operateur = new Operateur();
  when(operateurRepository.save(operateur)).thenReturn(operateur);

  Operateur addedOperateur = operateurService.addOperateur(operateur);

  assertNotNull(addedOperateur);
 }

 @Test
 public void testDeleteOperateur() {
  Long id = 1L;

  operateurService.deleteOperateur(id);

  verify(operateurRepository).deleteById(id);
 }

 @Test
 public void testUpdateOperateur() {
  Operateur operateur = new Operateur();
  when(operateurRepository.save(operateur)).thenReturn(operateur);

  Operateur updatedOperateur = operateurService.updateOperateur(operateur);

  assertNotNull(updatedOperateur);
 }

 @Test
 public void testRetrieveOperateur() {
  Long id = 1L;
  Operateur operateur = new Operateur();
  when(operateurRepository.findById(id)).thenReturn(Optional.of(operateur));

  Operateur retrievedOperateur = operateurService.retrieveOperateur(id);

  assertNotNull(retrievedOperateur);
 }
}
