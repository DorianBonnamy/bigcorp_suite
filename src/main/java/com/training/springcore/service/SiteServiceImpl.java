package com.training.springcore.service;

import com.training.springcore.config.Monitored;
import com.training.springcore.model.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Service("siteService")
@Component
public class SiteServiceImpl  implements SiteService{

    private final static Logger logger = LoggerFactory.getLogger(SiteService.class);

    private CaptorService captorService;

    @Autowired
    private ResourceLoader resourceLoader;

    public SiteServiceImpl(){

    }

    @Autowired
    public SiteServiceImpl(CaptorService captorService){
        logger.info("Init SiteServiceImpl :" + this);
        this.captorService = captorService;
    }

    @Override
    @Monitored
    public Site findById(String siteId) {
        logger.info("Appel de findById :" + this);
        if (siteId == null) {
            return null;
        }

        Site site = new Site("Florange");
        site.setId(siteId);
        site.setCaptors(captorService.findBySite(siteId));
        return site;
    }
}
