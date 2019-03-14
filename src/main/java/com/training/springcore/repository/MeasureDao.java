package com.training.springcore.repository;

import com.training.springcore.model.Measure;
import org.springframework.stereotype.Service;

@Service("measureDao")
public interface MeasureDao extends CrudDao<Measure, Long> {
}
