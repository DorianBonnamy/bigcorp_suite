package com.training.springcore.service;

import com.training.springcore.model.Site;

import java.io.IOException;

public interface SiteService {
    Site findById(String siteId);
}