package com.training.springcore.repository;

import com.training.springcore.model.Site;
import org.springframework.stereotype.Service;

@Service
public interface SiteDao extends CrudDao<Site, String> {

    void ForeignKeyCaptors(Site site);
}
