package br.com.aceleragep.avalicao03.dtos.inputs;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemInput {
	@NotEmpty(message = "O título não pode ser vazio")
	@Length(min = 1, max = 100, message = "O título de ter no máximo 100 caracteres")
	private String titulo;
}
