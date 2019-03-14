package com.training.springcore.repository;

import com.training.springcore.model.Captor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("captorDao")
public interface CaptorDao extends CrudDao<Captor, String> {
    List<Captor> findBySiteId(String siteId);
    void deleteForeignKey(Captor  captor);
}
