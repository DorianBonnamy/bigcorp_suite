package com.training.springcore.repository;

import com.training.springcore.model.Measure;
import com.training.springcore.utils.H2DateConverter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class MeasureDaoImpl implements MeasureDao {

    @PersistenceContext
    private EntityManager em;

    private H2DateConverter h2DateConverter;

    @Override
    public void persist(Measure measure) {
        em.persist(measure);
    }

    @Override
    public Measure findById(Long s) {
        return em.find(Measure.class, s);
    }

    @Override
    public List<Measure> findAll() {
        return em.createQuery("select c from Measure c",Measure.class).getResultList();
    }

    @Override
    public void delete(Measure measure) {
        em.remove(measure);
    }
}
