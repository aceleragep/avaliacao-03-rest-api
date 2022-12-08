package br.com.aceleragep.avalicao03.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tb_itens")
public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "titulo", length = 100, nullable = false)
	private String titulo;

	@Column(name = "concluido", nullable = false)
	private boolean concluido;
	
	@JoinColumn(name = "listas_id", nullable = false)
	@ManyToOne
	private ListasEntity lista;

}
