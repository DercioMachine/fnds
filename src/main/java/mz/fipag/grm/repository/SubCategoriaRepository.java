package mz.fipag.grm.repository;

import mz.fipag.grm.domain.Assunto;
import mz.fipag.grm.domain.Distrito;
import mz.fipag.grm.domain.SubCategoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoriaRepository extends CrudRepository<SubCategoria, Long> {

    @Query(value="select * from subcategoria where categoria_id=:id", nativeQuery=true)
    public List<SubCategoria> buscarPorId(@Param("id") Long id);
}
