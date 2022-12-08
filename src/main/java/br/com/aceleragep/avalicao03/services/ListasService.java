package br.com.aceleragep.avalicao03.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aceleragep.avalicao03.entities.ListasEntity;
import br.com.aceleragep.avalicao03.exceptions.NotFoundBussinessException;
import br.com.aceleragep.avalicao03.repositories.ListasRepository;

@Service
public class ListasService {

	@Autowired
	private ListasRepository listasRepository;

	@Autowired
	private ItemService itemService;

	@Transactional
	public ListasEntity cadastra(ListasEntity listasEntity) {
		return listasRepository.save(listasEntity);
	}

	@Transactional
	public ListasEntity altera(ListasEntity listasEntity) {
		return listasRepository.save(listasEntity);
	}

	@Transactional
	public void remove(Long id) {
		ListasEntity lista = this.buscaPeloId(id);
		itemService.removeAllItemsByListaId(lista.getId());
		listasRepository.delete(lista);
	}

	public ListasEntity buscaPeloId(Long id) {
		return listasRepository.findById(id)
				.orElseThrow(() -> new NotFoundBussinessException("Lista não encotrada com o id: " + id));
	}

	public List<ListasEntity> listaTodos() {
		return listasRepository.findAll();
	}
}
