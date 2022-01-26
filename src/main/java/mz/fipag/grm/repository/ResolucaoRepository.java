package mz.fipag.grm.repository;

import mz.fipag.grm.domain.Resolucao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResolucaoRepository  extends CrudRepository<Resolucao, Long> {

}
