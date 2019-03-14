package com.training.springcore.repository;

import com.training.springcore.model.Captor;
import com.training.springcore.model.Measure;
import com.training.springcore.model.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class SiteDaoImpl implements  SiteDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CaptorDao captorDao;

    @Override
    public void persist(Site element) {
        em.persist(element);
    }

    @Override
    public Site findById(String s){
        return em.find(Site.class, s);
    }

    @Override
    public List<Site> findAll() {
        return em.createQuery("select s from Site s", Site.class).getResultList();
    }

    @Override
    public void delete(Site site) {
        em.remove(site);
    }

    @Override
    public void ForeignKeyCaptors(Site site){
        em.createQuery("Select c from Captor c inner join c.site s where s=:site",Captor.class)
                .setParameter("site", site)
                .getResultList().forEach(c ->{
            captorDao.deleteForeignKey(c);
            em.remove(c);
        });
    }
}
