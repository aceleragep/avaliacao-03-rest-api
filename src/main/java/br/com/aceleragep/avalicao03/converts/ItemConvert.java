package br.com.aceleragep.avalicao03.converts;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aceleragep.avalicao03.dtos.inputs.ItemInput;
import br.com.aceleragep.avalicao03.dtos.outputs.ItemOutput;
import br.com.aceleragep.avalicao03.entities.ItemEntity;

@Component
public class ItemConvert {

	@Autowired
	private ModelMapper modelMapper;

	public ItemEntity newInputToEntity(ItemInput itemInput) {
		return modelMapper.map(itemInput, ItemEntity.class);
	}

	public ItemOutput entityToOutput(ItemEntity itensEntity) {
		return modelMapper.map(itensEntity, ItemOutput.class);
	}

	public void copyInputToEntity(@Valid ItemInput itemInput, ItemEntity itemEntity) {
		modelMapper.map(itemInput, itemEntity);
	}

	public List<ItemOutput> entityToOutput(List<ItemEntity> itensEntities) {
		return itensEntities.stream().map(this::entityToOutput).collect(Collectors.toList());
	}

}
