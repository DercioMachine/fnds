package mz.fipag.grm.repository;

import mz.fipag.grm.domain.Distrito;
import mz.fipag.grm.domain.PostoAdministrativo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostoAdminitrativoRepository extends CrudRepository<PostoAdministrativo, Long> {

    @Query(value="select d from PostoAdministrativo d where d.id=:id")
    public List<PostoAdministrativo> findAllById(@Param("id") Long id);
}
