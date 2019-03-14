package com.training.springcore.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Captor {
    /**
     * Captor id
     */
    @Id
    private String id = UUID.randomUUID().toString();

    /**
     * Captor name
     */
    @Column(nullable=false)
    private String name;

    /**
     * Captor PowerSource powerSource;
     * */

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private PowerSource powerSource;

    /**
     * Captor Site site
     * */
    @Autowired
    @ManyToOne(optional = false)
    private Site site;

    @Column
    Integer defaultPowerInWatt;

    @Deprecated
    public Captor() {
        // Use for serializer or deserializer
    }

    /**
     * Constructor to use with required property
     * @param name
     */
    public Captor(String name) {
        this.name = name;
        this.powerSource = PowerSource.FIXED;
    }

    public Captor(String name,Site site) {
        this.name = name;
        this.powerSource = PowerSource.FIXED;
        this.site = site;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Captor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public PowerSource getPowerSource() {
        return powerSource;
    }

    public void setPowerSource(PowerSource powerSource) {
        this.powerSource = powerSource;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public void setSite(String id){
        this.site = new Site();
        site.setId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Captor captor = (Captor) o;
        return Objects.equals(id, captor.id) &&
                Objects.equals(name, captor.name) &&
                powerSource == captor.powerSource &&
                Objects.equals(site, captor.site);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, powerSource, site);
    }


    public Integer getDefaultPowerInWatt() {
        return defaultPowerInWatt;
    }

    public void setDefaultPowerInWatt(Integer defaultPowerInWatt) {
        this.defaultPowerInWatt = defaultPowerInWatt;
    }
}
