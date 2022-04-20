package mz.fipag.grm.repository;

import org.springframework.data.repository.CrudRepository;

import mz.fipag.grm.domain.Assunto;

public interface TipoAlertaRepository extends CrudRepository<Assunto, Long> {


}
