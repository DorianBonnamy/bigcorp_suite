package com.training.springcore.repository;

import com.training.springcore.model.Captor;
import com.training.springcore.model.Measure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CaptorDaoImpl implements CaptorDao {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private MeasureDao measureDao;

    @Override
    public List<Captor> findBySiteId(String siteId) {
        return em.createQuery("select c from Captor c inner join c.site s where s.id =:siteId",
        Captor.class).setParameter("siteId", siteId).getResultList();
    }

    @Override
    public void persist(Captor captor) {
        em.persist(captor);
    }

    @Override
    public Captor findById(String id) {
        return em.find(Captor.class, id);
    }

    @Override
    public List<Captor> findAll() {
        return em.createQuery("select c from Captor c", Captor.class).getResultList();
    }

    @Override
    public void delete(Captor captor) {
        em.remove(captor);
    }

    @Override
    public void deleteForeignKey(Captor  captor){
        em.createQuery("select m from Measure m where m.captor =: captor",Measure.class)
                .setParameter("captor", captor)
                .getResultList().forEach(m -> measureDao.delete(m) );
    }
}
