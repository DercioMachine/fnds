package mz.fipag.grm.repository;

import mz.fipag.grm.domain.Origem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrigemRepository extends CrudRepository<Origem, Long> {

    @Query(value="select * from origem where id = 1", nativeQuery=true)
    public List<Origem> findAllExterno();

    @Query(value="select * from origem where id = 2", nativeQuery=true)
    public List<Origem> findAllInterno();

}
