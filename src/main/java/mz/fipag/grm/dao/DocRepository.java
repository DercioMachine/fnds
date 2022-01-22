package mz.fipag.grm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mz.fipag.grm.domain.Doc;

public interface DocRepository extends JpaRepository<Doc, Long>{

}
