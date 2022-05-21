package mz.fipag.grm.repository;

import mz.fipag.grm.domain.Projecto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mz.fipag.grm.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @Query(value="select * from user where telefone=:telefone", nativeQuery=true)
    User pesquisarPorTelefone(String telefone);
    
    @Query(value="select * from user where email=:email", nativeQuery=true)
    User pesquisarPorEmail(String email);

    @Query(value="select * from user, projectouser where user.id=projectouser.user_id and projectouser.projecto_id=:projecto", nativeQuery=true)
    public List<User> BuscarUserPorProjecto(Projecto projecto);

    @Query(value="select * from user, projectouser where user.id=projectouser.user_id and user.id=:id", nativeQuery=true)
    User buscarTodosPorId(Long id);

    @Query(value="select * from user where id=:iduser", nativeQuery=true)
    User buscarPorIdUser(long iduser);
}
