package com.training.springcore.controller;

import com.training.springcore.model.Captor;
import com.training.springcore.model.FixedCaptor;
import com.training.springcore.model.Site;
import com.training.springcore.repository.CaptorDao;
import com.training.springcore.repository.MeasureDao;
import com.training.springcore.repository.SiteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sites/{siteId}/captors")
public class FixedCaptorController {

    @Autowired
    CaptorDao captorDao;

    @Autowired
    SiteDao siteDao;

    @Autowired
    MeasureDao measureDao;

    private Site findSiteById(String siteId){
        return siteDao.findById(siteId).orElseThrow(IllegalArgumentException::new);
    }

    @GetMapping(path = "/FIXED/create")
    public ModelAndView editFixedCaptor(Model model, @PathVariable String siteId) {
        Site site = findSiteById(siteId);
        return new  ModelAndView("captor").addObject("captor", new FixedCaptor("", site, null));
    }

    @GetMapping(path = "/{id}")
    public ModelAndView modification(Model model, @PathVariable String siteId){
        return new ModelAndView("sites-create").addObject("site", siteDao.findById(siteId).orElseThrow(IllegalArgumentException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView save(@PathVariable String siteId, FixedCaptor captor) {
        Site site = siteDao.findById(siteId).orElseThrow(IllegalArgumentException::new);
        FixedCaptor captorToPersist;
        if (captor.getId() == null) {
            System.out.println(captor.getName());
            System.out.println(site.toString());
            System.out.println(captor.getDefaultPowerInWatt());
            captorToPersist = new FixedCaptor(captor.getName(), site, captor.getDefaultPowerInWatt());
        } else {
            captorToPersist = (FixedCaptor) captorDao.findById(captor.getId()).orElseThrow(IllegalArgumentException::new);
            captorToPersist.setName(captor.getName());
            captorToPersist.setDefaultPowerInWatt(captor.getDefaultPowerInWatt());
        }
        captorDao.save(captorToPersist);
        return new ModelAndView("sites").addObject("sites", siteDao.findAll());
    }

    @PostMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable String id) {
        // Comme les capteurs sont liés à un site et les mesures sont liées à un capteur, nous devons faire
        // le ménage avant pour ne pas avoir d'erreur à la suppression d'un site utilisé ailleurs dans la base
        measureDao.deleteByCaptorId(id);
        captorDao.deleteById(id);
        return new ModelAndView("sites").addObject("sites", siteDao.findAll());
    }
}
