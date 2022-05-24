package mz.fipag.grm.repository;

import mz.fipag.grm.domain.ProjectoUser;
import mz.fipag.grm.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectoUserRepository extends CrudRepository<ProjectoUser, Long> {

    @Query(value="select * from projectouser where user_id=:id", nativeQuery=true)
    List<ProjectoUser> buscarPorUser(Long id);

    @Query(value="select * from projectouser where id=:id", nativeQuery=true)
    ProjectoUser buscarPoriD(Long id);
    
    @Query(value="select * from projectouser pu "
    		+ "inner join projecto p "
    		+ "inner join user u "
    		+ "where pu.projecto_id=p.id and pu.user_id=u.id", nativeQuery=true)
    List<ProjectoUser> buscarPorProjecto(Long id);
}
