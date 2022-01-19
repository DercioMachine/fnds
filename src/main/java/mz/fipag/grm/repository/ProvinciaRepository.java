package mz.fipag.grm.repository;

import mz.fipag.grm.domain.Provincia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinciaRepository extends CrudRepository<Provincia, Long> {

    @Query(value="select distinct p from Provincia p join p.distritos d where d.id=:id")
    public List<Provincia> findAllById(@Param("id") Long id);
}
