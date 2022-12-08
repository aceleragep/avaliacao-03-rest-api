package br.com.aceleragep.avalicao03.converts;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aceleragep.avalicao03.dtos.inputs.ListasInput;
import br.com.aceleragep.avalicao03.dtos.outputs.ListasOutput;
import br.com.aceleragep.avalicao03.entities.ListasEntity;

@Component
public class ListasConvert {

	@Autowired
	private ModelMapper modelMapper;

	public ListasEntity newInputToEntity(ListasInput listasInput) {
		return modelMapper.map(listasInput, ListasEntity.class);
	}

	public ListasOutput entityToOutput(ListasEntity listasEntity) {
		return modelMapper.map(listasEntity, ListasOutput.class);
	}

	public void copyInputToEntity(@Valid ListasInput listasInput, ListasEntity listaEncontrada) {
		modelMapper.map(listasInput, listaEncontrada);
	}

	public List<ListasOutput> entityToOutput(List<ListasEntity> listasEntities) {
		return listasEntities.stream().map(this::entityToOutput).collect(Collectors.toList());
	}

}
