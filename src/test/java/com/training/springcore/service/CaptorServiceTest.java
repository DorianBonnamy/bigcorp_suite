package com.training.springcore.service;

import com.training.springcore.model.Captor;
import com.training.springcore.model.Site;
import com.training.springcore.repository.CaptorDao;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Set;

public class CaptorServiceTest {

    @Mock
    private CaptorDao captorDao;
    @InjectMocks
    private CaptorServiceImpl captorService;
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void findBySiteShouldReturnNullWhenIdIsNull() {
// Initialisation
        String siteId = null;
// Appel du SUT
        Set<Captor> captors = captorService.findBySite(siteId);
// Vérification
        Assertions.assertThat(captors).isEmpty();
    }
    @Test
    public void findBySite() {
// Initialisation
        String siteId = "siteId";
        Captor expectedCaptor = new Captor("Capteur A", new Site("Florange"));
        Mockito.when(captorDao.findBySiteId(siteId)).thenReturn(Arrays.asList(expectedCaptor))
        ;
// Appel du SUT
        Set<Captor> captors = captorService.findBySite(siteId);
// Vérification
        Assertions.assertThat(captors).hasSize(1);
        Assertions.assertThat(captors).extracting(Captor::getName).contains("Capteur A");
    }
}
