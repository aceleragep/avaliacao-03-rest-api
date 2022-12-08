package br.com.aceleragep.avalicao03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aceleragep.avalicao03.entities.ListasEntity;

@Repository
public interface ListasRepository extends JpaRepository<ListasEntity, Long> {

}
