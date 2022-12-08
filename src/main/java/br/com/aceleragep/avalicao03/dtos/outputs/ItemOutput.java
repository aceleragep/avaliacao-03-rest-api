package br.com.aceleragep.avalicao03.dtos.outputs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemOutput {
	private Long id;
	private String titulo;
	private boolean concluido;
	private ListasOutput lista;
}
