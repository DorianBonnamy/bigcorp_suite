package com.training.springcore.repository;

import com.training.springcore.model.Captor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
@Transactional
public interface CaptorDao extends JpaRepository<Captor, String> {
    Set<Captor> findBySiteId(String siteId);
    void deleteBySiteId(String siteId);
}
