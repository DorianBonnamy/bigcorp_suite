package com.training.springcore.service;

import com.training.springcore.model.Captor;
import com.training.springcore.model.Measure;
import com.training.springcore.model.PowerSource;

import java.util.Set;

public interface CaptorService {
    Set<Captor> findBySite(String siteId);
}
