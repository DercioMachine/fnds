package mz.fipag.grm.repository;

import mz.fipag.grm.domain.Processo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProcessoRepository extends CrudRepository<Processo, Long> {

    @Query(value="select * from Processo where tipo=:tipo", nativeQuery=true)
    public List<Processo> BuscarTodosPorProcessos(@Param("tipo") String tipo);

}
