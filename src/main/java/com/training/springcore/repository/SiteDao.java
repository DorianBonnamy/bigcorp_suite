package com.training.springcore.repository;

import com.training.springcore.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface SiteDao extends JpaRepository<Site, String> {
}
