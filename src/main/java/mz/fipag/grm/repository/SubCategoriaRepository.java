package mz.fipag.grm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mz.fipag.grm.domain.SubCategoria;

@Repository
public interface SubCategoriaRepository extends CrudRepository<SubCategoria, Long> {

    @Query(value="select * from subcategoria where categoria_id=:id", nativeQuery=true)
    public List<SubCategoria> buscarPorId(@Param("id") Long id);
    
    @Query(value="select DISTINCT * from subcategoria subcat inner join categoria cat where subcat.categoria_id=cat.id order by cat.designacao", nativeQuery=true)
    public List<SubCategoria> buscarTodoAgrupoPorCategoria();
}
