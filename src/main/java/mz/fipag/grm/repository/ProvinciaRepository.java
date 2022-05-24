package mz.fipag.grm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mz.fipag.grm.domain.Provincia;

@Repository
public interface ProvinciaRepository extends CrudRepository<Provincia, Long> {

    @Query(value="select * from provincia order by id asc", nativeQuery=true)
   public List<Provincia> findAllOrderById();
}
