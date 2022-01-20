package mz.fipag.grm.repository;


import mz.fipag.grm.domain.Distrito;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistritoRepository extends CrudRepository<Distrito, Long> {

    @Query(value="select id,designacao from distrito where provincia_id=:id", nativeQuery=true)
    public List<Distrito> findAllById(@Param("id") Long id);


}
