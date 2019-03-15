package com.training.springcore.repository;

import com.training.springcore.model.Captor;
import com.training.springcore.model.Measure;
import com.training.springcore.model.RealCaptor;
import com.training.springcore.model.Site;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
@Transactional
public class MeasureDaoImplTest {
    @Autowired
    private MeasureDao measureDao;
    @Autowired
    private EntityManager entityManager;
    @Test
    public void findById() {
        Optional<Measure> measure = measureDao.findById(-1L);
        Assertions.assertThat(measure)
                .get()
                .extracting("valueInWatt")
                .contains(1000000);
    }
    @Test
    public void findByIdShouldReturnNullWhenIdUnknown() {
        Optional<Measure> measure = measureDao.findById(-25L);
        Assertions.assertThat(measure).isEmpty();
    }
    @Test
    public void findAll() {
        List<Measure> measures = measureDao.findAll();
        Assertions.assertThat(measures)
                .hasSize(10)
                .extracting("id")
                .contains(-10L, -9L, -8L, -7L, -6L, -5L, -4L, -3L, -2L, -1L);
    }
    @Test
    public void create() {
        Site site = new Site("site");
        entityManager.persist(site);
        Captor captor = new RealCaptor("Eolienne", site);
        entityManager.persist(captor);
        Assertions.assertThat(measureDao.findAll()).hasSize(10);
        measureDao.save(new Measure(Instant.now(),2_333_666, captor ));
        Assertions.assertThat(measureDao.findAll())
                .hasSize(11)
                .extracting(Measure::getValueInWatt)
                .contains(1000000, 1000124, 1001234, 1001236, 1009678, -9000000, -900124, -901234,  -901236, -909678, 2333666);
    }
    @Test
    public void update() {
        Optional<Measure> measure = measureDao.findById(-2L);
        Assertions.assertThat(measure).get().extracting("valueInWatt").containsExactly(1000124);
        measure.ifPresent(s -> {
            s.setValueInWatt(253);
            measureDao.save(s);
        });

        measure = measureDao.findById(-2L);
        Assertions.assertThat(measure).get().extracting("valueInWatt").containsExactly(253);
    }
    @Test
    public void deleteById() {
        Site site = new Site("yukl");
        Captor captor = new RealCaptor("new captor",site);
        entityManager.persist(captor);
        Measure newmeasure = new Measure(Instant.now(),2_333_666, captor );
        newmeasure = measureDao.save(newmeasure);
        Assertions.assertThat(measureDao.findById(newmeasure.getId())).isNotEmpty();
        measureDao.delete(newmeasure);
        Assertions.assertThat(measureDao.findById(newmeasure.getId())).isEmpty();
    }

    @Test
    public void preventConcurrentWrite() {
        Measure measure = measureDao.getOne(-1L);
// A la base le numéro de version est à sa valeur initiale
        Assertions.assertThat(measure.getVersion()).isEqualTo(0);
// On detache cet objet du contexte de persistence
        entityManager.detach(measure);
        measure.setValueInWatt(5);
// On force la mise à jour en base (via le flush) et on vérifie que l'objet retourné
// et attaché à la session a été mis à jour
        Measure attacheMeasure = measureDao.save(measure);
        entityManager.flush();
        Assertions.assertThat(attacheMeasure.getValueInWatt()).isEqualTo(5);
        Assertions.assertThat(attacheMeasure.getVersion()).isEqualTo(1);
// Si maintenant je réessaie d'enregistrer captor, comme le numéro de version est
// à 0 je dois avoir une exception
        Assertions.assertThatThrownBy(() -> measureDao.save(measure))
                .isExactlyInstanceOf(ObjectOptimisticLockingFailureException.class);
    }

    @Test
    public void deleteByCaptorId() {
        Assertions.assertThat(measureDao.findAll().stream().filter(m -> m.getCaptor().getId().equals("c1"))).hasSize(5);
        measureDao.deleteByCaptorId("c1");
        Assertions.assertThat(measureDao.findAll().stream().filter(m -> m.getCaptor().getId().equals("c1"))).isEmpty();
    }
}
