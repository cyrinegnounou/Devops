package tn.esprit.rh.achat.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reglement implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReglement;
	private float montantPaye;
	private float montantRestant;
	private Boolean payee;
	@Temporal(TemporalType.DATE)
	private Date dateReglement;
	@ManyToOne
	@JsonIgnore
	private Facture facture;
	
}
