package mz.fipag.grm.repository;

import mz.fipag.grm.domain.Origem;
import mz.fipag.grm.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @Query(value="select * from user where telefone=:telefone or email=:email", nativeQuery=true)
    User pesquisarPorTelefoneOuEmail(String telefone,String email);

}
