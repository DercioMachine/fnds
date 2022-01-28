package mz.fipag.grm.repository;

import mz.fipag.grm.domain.Responsabilidade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsabilidadeRepository extends CrudRepository<Responsabilidade, Long> {
}
