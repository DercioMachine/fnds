package mz.fipag.grm.dao;

import org.springframework.stereotype.Repository;

import mz.fipag.grm.domain.Distrito;

import java.util.List;

@Repository
public class DistritoDaoImpl extends AbstractDao<Distrito, Long> implements DistritoDao{

    @Override
    public List<Distrito> findAllById(Long id) {
        return null;
    }
}
