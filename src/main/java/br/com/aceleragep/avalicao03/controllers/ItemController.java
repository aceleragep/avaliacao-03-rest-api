package br.com.aceleragep.avalicao03.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.aceleragep.avalicao03.converts.ItemConvert;
import br.com.aceleragep.avalicao03.dtos.inputs.ItemInput;
import br.com.aceleragep.avalicao03.dtos.outputs.ItemOutput;
import br.com.aceleragep.avalicao03.entities.ItemEntity;
import br.com.aceleragep.avalicao03.entities.ListasEntity;
import br.com.aceleragep.avalicao03.services.ItemService;
import br.com.aceleragep.avalicao03.services.ListasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Itens", description = "Gerencie os itens das suas listas")
@RestController
@RequestMapping("/api/itens")
public class ItemController {

	@Autowired
	private ListasService listasService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemConvert itemConvert;

	@Operation(summary = "Cadastra um item em uma lista", description = " detalhada.")
	@PostMapping("/lista/{listasId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ItemOutput cadastra(
			@Parameter(description = "Representa uma item input") @RequestBody @Valid ItemInput itemInput,
			@Parameter(description = "Id da lista", example = "1") @PathVariable Long listasId) {

		ListasEntity listaEncontrada = listasService.buscaPeloId(listasId);
		ItemEntity itemEntity = itemConvert.newInputToEntity(itemInput);
		itemEntity.setLista(listaEncontrada);
		ItemEntity itemCadastrada = itemService.cadastra(itemEntity);
		return itemConvert.entityToOutput(itemCadastrada);

	}

	@Operation(summary = "Altera um item de uma lista", description = " detalhada.")
	@PutMapping("/{id}/lista/{listasId}")
	public ItemOutput atualiza(@Parameter(description = "Id do item", example = "1") @PathVariable Long id,
			@Parameter(description = "Representa uma item input") @RequestBody @Valid ItemInput itemInput,
			@Parameter(description = "Id da lista", example = "1") @PathVariable Long listasId) {
		ItemEntity itemEncontrado = itemService.buscaPeloId(id);
		ListasEntity listaEncontrada = listasService.buscaPeloId(listasId);

		itemConvert.copyInputToEntity(itemInput, itemEncontrado);

		itemEncontrado.setLista(listaEncontrada);

		ItemEntity itemCadastrada = itemService.altera(itemEncontrado);
		return itemConvert.entityToOutput(itemCadastrada);

	}

	@Operation(summary = "Remove um item de uma lista", description = "Remove um item de uma lista detalhada.")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remove(@Parameter(description = "Id do item", example = "1") @PathVariable Long id) {
		itemService.remove(id);
	}

	@Operation(summary = "Marca um item de uma lista como concluído", description = "Marca um item de uma lista como concluído detalhada.")
	@PutMapping("/{id}/concluir")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void marcarComoConcluido(@Parameter(description = "Id do item", example = "1") @PathVariable Long id) {
		itemService.marcaComoConcluido(id);
	}

	@Operation(summary = "Desmarca um item de uma lista como concluído", description = "Desmarca um item de uma lista como concluído detalhada.")
	@PutMapping("/{id}/desconcluir")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void desmarcarComoConcluido(@Parameter(description = "Id do item", example = "1") @PathVariable Long id) {
		itemService.desmarcaComoConcluido(id);
	}

	@Operation(summary = "Lista todos os item de uma lista pelo identificador da lista", description = "Lista todos os item de uma lista pelo identificador da lista detalhada.")
	@GetMapping("/lista/{listasId}")
	public List<ItemOutput> listaTodos(
			@Parameter(description = "Id da lista", example = "1") @PathVariable Long listasId) {
		List<ItemEntity> itemEntities = itemService.listaTodos(listasId);
		return itemConvert.entityToOutput(itemEntities);
	}

}
