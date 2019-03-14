package com.training.springcore.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class BigCorpApplicationMeasureProperties {

    private Integer defaultFixed;
    private Integer defaultSimulated;
    private Integer defaultReal;

    public Integer getDefaultFixed() {
        return defaultFixed;
    }

    public void setDefaultFixed(Integer defaultFixed) {
        this.defaultFixed = defaultFixed;
    }

    public Integer getDefaultSimulated() {
        return defaultSimulated;
    }

    public void setDefaultSimulated(Integer defaultSimulated) {
        this.defaultSimulated = defaultSimulated;
    }

    public Integer getDefaultReal() {
        return defaultReal;
    }

    public void setDefaultReal(Integer defaultReal) {
        this.defaultReal = defaultReal;
    }
}
