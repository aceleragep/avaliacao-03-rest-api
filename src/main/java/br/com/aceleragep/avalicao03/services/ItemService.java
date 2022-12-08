package br.com.aceleragep.avalicao03.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aceleragep.avalicao03.entities.ItemEntity;
import br.com.aceleragep.avalicao03.exceptions.NotFoundBussinessException;
import br.com.aceleragep.avalicao03.repositories.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Transactional
	public ItemEntity cadastra(ItemEntity itemEntity) {
		return itemRepository.save(itemEntity);
	}

	@Transactional
	public ItemEntity altera(ItemEntity itemEntity) {
		return itemRepository.save(itemEntity);
	}

	@Transactional
	public void remove(Long id) {
		itemRepository.delete(this.buscaPeloId(id));
	}

	@Transactional
	public void marcaComoConcluido(Long id) {
		ItemEntity itemEncontrado = this.buscaPeloId(id);
		itemEncontrado.setConcluido(true);
		itemRepository.save(itemEncontrado);
	}

	@Transactional
	public void desmarcaComoConcluido(Long id) {
		ItemEntity itemEncontrado = this.buscaPeloId(id);
		itemEncontrado.setConcluido(false);
		itemRepository.save(itemEncontrado);
	}

	@Transactional
	public void removeAllItemsByListaId(Long listasId) {
		List<ItemEntity> listaTodos = this.listaTodos(listasId);
		itemRepository.deleteAll(listaTodos);
	}

	public ItemEntity buscaPeloId(Long id) {
		return itemRepository.findById(id)
				.orElseThrow(() -> new NotFoundBussinessException("Item não encotrado com o id: " + id));
	}

	public List<ItemEntity> listaTodos(Long listasId) {
		return itemRepository.findAllByListaId(listasId);
	}
}
