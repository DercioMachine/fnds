package mz.fipag.grm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mz.fipag.grm.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @Query(value="select * from user where telefone=:telefone", nativeQuery=true)
    User pesquisarPorTelefone(String telefone);
    
    @Query(value="select * from user where email=:email", nativeQuery=true)
    User pesquisarPorEmail(String email);

}
