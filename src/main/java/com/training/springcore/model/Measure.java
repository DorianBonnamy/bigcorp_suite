package com.training.springcore.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Entity
public class Measure {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private java.time.Instant instant; //instant au format UTC  où la date a été lue
    @Column(nullable=false)
    private Integer valueInWatt; //value en watt

    @ManyToOne(optional = false)
    private Captor captor;

    public Measure(){

    }

    public Measure(Long id, Instant instant, Integer valueInWatt, Captor captor) {
        this.id = id;
        this.instant = instant;
        this.valueInWatt = valueInWatt;
        this.captor = captor;
    }

    public Measure(Instant instant, Integer valueInWatt, Captor captor) {
        this.instant = instant;
        this.valueInWatt = valueInWatt;
        this.captor = captor;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public Integer getValueInWatt() {
        return valueInWatt;
    }

    public void setValueInWatt(Integer valueInWatt) {
        this.valueInWatt = valueInWatt;
    }

    public Captor getCaptor() {
        return captor;
    }

    public void setCaptor(Captor captor) {
        this.captor = captor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measure measure = (Measure) o;
        return Objects.equals(instant, measure.instant) && Objects.equals(valueInWatt, measure.valueInWatt) && Objects.equals(captor, measure.captor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instant, valueInWatt, captor);
    }
}
