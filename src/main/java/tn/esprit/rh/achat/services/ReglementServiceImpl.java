package tn.esprit.rh.achat.services;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;

import java.util.Date;
import java.util.List;

@Service
public class ReglementServiceImpl implements IReglementService {

	final
	FactureRepository factureRepository;
	final
	ReglementRepository reglementRepository;

	public ReglementServiceImpl(FactureRepository factureRepository, ReglementRepository reglementRepository) {
		this.factureRepository = factureRepository;
		this.reglementRepository = reglementRepository;
	}

	@Override
	public List<Reglement> retrieveAllReglements() {
		return (List<Reglement>) reglementRepository.findAll();
	}

	@Override
	public Reglement addReglement(Reglement r) {
        reglementRepository.save(r);
		return r;
	}

	@Override
	public Reglement retrieveReglement(Long id) {
		return reglementRepository.findById(id).orElse(null);

	}

	@Override
	public List<Reglement> retrieveReglementByFacture(Long idFacture) {
		return reglementRepository.retrieveReglementByFacture(idFacture);

//
	}

	@Override
	public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {
		return reglementRepository.getChiffreAffaireEntreDeuxDate( startDate, endDate);
	}

}
