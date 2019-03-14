package com.training.springcore.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
@ConfigurationProperties(prefix = "bigcorp")
public class BigCorpApplicationProperties {

    @NestedConfigurationProperty
    BigCorpApplicationMeasureProperties measure;

    private String name;
    private Set<String> emails;
    private String webSiteUrl;
    private Integer version;

    public BigCorpApplicationProperties(){

    }

    public String getName() {
        return name;
    }

    public Integer getVersion() {
        return version;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public String getWebSiteUrl() {
        return webSiteUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmails(Set<String> emails) {
        this.emails = emails;
    }

    public void setWebSiteUrl(String webSiteUrl) {
        this.webSiteUrl = webSiteUrl;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public BigCorpApplicationMeasureProperties getMeasure() {
        return measure;
    }

    public void setMeasure(BigCorpApplicationMeasureProperties measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        return "ApplicationInfo{" +
            "name='" + name + '\'' +
            ", version=" + version +
            ", emails=" + emails +
            ", webSiteUrl='" + webSiteUrl + '\'' +
            '}';
    }
}
