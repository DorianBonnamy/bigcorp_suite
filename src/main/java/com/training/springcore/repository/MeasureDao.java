package com.training.springcore.repository;

import com.training.springcore.model.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MeasureDao extends JpaRepository<Measure, Long> {

    void deleteByCaptorId(String captorId);
}
