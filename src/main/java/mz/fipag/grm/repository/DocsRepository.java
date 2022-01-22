package mz.fipag.grm.repository;

import mz.fipag.grm.domain.Distrito;
import mz.fipag.grm.domain.Doc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocsRepository extends CrudRepository<Doc, Long> {

    @Query(value="select * from docs where ocorrencia_id=:id", nativeQuery=true)
    public List<Doc> findAllById(@Param("id") Long id);
}
