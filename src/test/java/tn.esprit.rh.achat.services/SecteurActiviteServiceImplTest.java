package tn.esprit.rh.achat.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SecteurActiviteServiceImplTest {

    private SecteurActiviteServiceImpl secteurActiviteService;
    private SecteurActiviteRepository secteurActiviteRepository;

    @Before
    public void setUp() {
        secteurActiviteRepository = mock(SecteurActiviteRepository.class);
        secteurActiviteService = new SecteurActiviteServiceImpl();
    }

    @Test
    public void testRetrieveAllSecteurActivite() {
        when(secteurActiviteRepository.findAll()).thenReturn(Arrays.asList(new SecteurActivite(), new SecteurActivite()));

        List<SecteurActivite> secteurs = secteurActiviteService.retrieveAllSecteurActivite();

        assertEquals(2, secteurs.size());
    }

    @Test
    public void testAddSecteurActivite() {
        SecteurActivite secteur = new SecteurActivite();
        when(secteurActiviteRepository.save(secteur)).thenReturn(secteur);

        SecteurActivite addedSecteur = secteurActiviteService.addSecteurActivite(secteur);

        assertNotNull(addedSecteur);
    }

    @Test
    public void testDeleteSecteurActivite() {
        Long id = 1L;

        secteurActiviteService.deleteSecteurActivite(id);

        verify(secteurActiviteRepository).deleteById(id);
    }

    @Test
    public void testUpdateSecteurActivite() {
        SecteurActivite secteur = new SecteurActivite();
        when(secteurActiviteRepository.save(secteur)).thenReturn(secteur);

        SecteurActivite updatedSecteur = secteurActiviteService.updateSecteurActivite(secteur);

        assertNotNull(updatedSecteur);
    }

    @Test
    public void testRetrieveSecteurActivite() {
        Long id = 1L;
        SecteurActivite secteur = new SecteurActivite();
        when(secteurActiviteRepository.findById(id)).thenReturn(Optional.of(secteur));

        SecteurActivite retrievedSecteur = secteurActiviteService.retrieveSecteurActivite(id);

        assertNotNull(retrievedSecteur);
    }
}
