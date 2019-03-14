package com.training.springcore.service;

import com.training.springcore.config.Monitored;
import com.training.springcore.model.Captor;
import com.training.springcore.model.Site;
import com.training.springcore.repository.CaptorDao;
import com.training.springcore.service.measure.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("captorService")
@Transactional
public class CaptorServiceImpl implements CaptorService{

    private MeasureService fixedMeasureService; //getter et setter sous_entendu
    private MeasureService realMeasureService;
    private MeasureService simulatedMeasureService;
    private CaptorDao captorDao;

    public CaptorServiceImpl(MeasureService fixedMeasureService, MeasureService realMeasureService, MeasureService simulatedMeasureService, CaptorDao captorDao ){
        this.fixedMeasureService = fixedMeasureService;
        this.realMeasureService = realMeasureService;
        this.simulatedMeasureService = simulatedMeasureService;
        this.captorDao = captorDao;
    }

    @Override
    public Set<Captor> findBySite(String siteId) {
        return captorDao.findBySiteId(siteId).stream().collect(Collectors.toSet());
    }
}
