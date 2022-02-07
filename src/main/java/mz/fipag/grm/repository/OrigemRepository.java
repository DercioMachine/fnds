package mz.fipag.grm.repository;

import mz.fipag.grm.domain.Doc;
import mz.fipag.grm.domain.Origem;
import org.springframework.data.repository.CrudRepository;

public interface OrigemRepository extends CrudRepository<Origem, Long> {
}
