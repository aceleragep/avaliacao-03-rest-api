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

import br.com.aceleragep.avalicao03.converts.ListasConvert;
import br.com.aceleragep.avalicao03.dtos.inputs.ListasInput;
import br.com.aceleragep.avalicao03.dtos.outputs.ListasOutput;
import br.com.aceleragep.avalicao03.entities.ListasEntity;
import br.com.aceleragep.avalicao03.services.ListasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Listas", description = "Gerencie suas listas")
@RestController
@RequestMapping("/api/listas")
public class ListasController {

	@Autowired
	private ListasService listasService;

	@Autowired
	private ListasConvert listasConvert;

	@Operation(summary = "Cadastra uma nova lista", description = "Cadastra uma nova lista detalhada.")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ListasOutput cadastra(@Parameter(description = "Representa uma lista input") @RequestBody @Valid ListasInput listasInput) {
		ListasEntity listasEntity = listasConvert.newInputToEntity(listasInput);
		ListasEntity listasCadastrada = listasService.cadastra(listasEntity);
		return listasConvert.entityToOutput(listasCadastrada);

	}

	@Operation(summary = "Altera uma lista existente", description = "Altera uma lista existente detalhada.")
	@PutMapping("/{id}")
	public ListasOutput atualiza(@Parameter(description = "Id da lista", example = "1") @PathVariable Long id, @Parameter(description = "Representa uma lista input") @RequestBody @Valid ListasInput listasInput) {
		ListasEntity listaEncontrada = listasService.buscaPeloId(id);
		listasConvert.copyInputToEntity(listasInput, listaEncontrada);

		ListasEntity listasCadastrada = listasService.altera(listaEncontrada);
		return listasConvert.entityToOutput(listasCadastrada);

	}

	@Operation(summary = "Remove uma lista existente.", description = "Remove uma lista existente. detalhada.")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remove(@Parameter(description = "Id da lista", example = "1") @PathVariable Long id) {
		listasService.remove(id);
	}

	@Operation(summary = "Busca uma lista pelo seu identificador (id).", description = "Busca uma lista pelo seu identificador (id). detalhada.")
	@GetMapping("/{id}")
	public ListasOutput buscaPeloId(@Parameter(description = "Id da lista", example = "1") @PathVariable Long id) {
		return listasConvert.entityToOutput(listasService.buscaPeloId(id));
	}

	@Operation(summary = "Lista todas as listas do sistema", description = "Lista todas as listas do sistema detalhada.")
	@GetMapping
	public List<ListasOutput> listaTodos() {
		List<ListasEntity> listasEntities = listasService.listaTodos();
		return listasConvert.entityToOutput(listasEntities);
	}

}
