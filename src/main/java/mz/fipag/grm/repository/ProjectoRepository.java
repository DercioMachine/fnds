package mz.fipag.grm.repository;

import mz.fipag.grm.domain.Projecto;
import mz.fipag.grm.domain.ProjectoUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectoRepository  extends CrudRepository<Projecto, Long> {

    @Query(value="select * from Projecto where id=:projectos", nativeQuery=true)
    Projecto buscarPorIdProjecto(long projectos);

    @Query(value="select * from projecto WHERE id NOT IN (SELECT projectouser.projecto_id FROM projectouser WHERE projectouser.user_id=:id) ", nativeQuery=true)
    List<Projecto> buscarTodosSemSelecao(Long id);

}
