/*
 * This file is generated by jOOQ.
 */
package de.netgo.kitaplan.jooq.tables.pojos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Plan implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String woche;
    private String wochentag;
    private LocalDate datum;
    private Boolean wald;
    private LocalTime start;
    private LocalTime ende;
    private LocalTime abfahrt;

    public Plan() {}

    public Plan(Plan value) {
        this.id = value.id;
        this.woche = value.woche;
        this.wochentag = value.wochentag;
        this.datum = value.datum;
        this.wald = value.wald;
        this.start = value.start;
        this.ende = value.ende;
        this.abfahrt = value.abfahrt;
    }

    public Plan(
        UUID id,
        String woche,
        String wochentag,
        LocalDate datum,
        Boolean wald,
        LocalTime start,
        LocalTime ende,
        LocalTime abfahrt
    ) {
        this.id = id;
        this.woche = woche;
        this.wochentag = wochentag;
        this.datum = datum;
        this.wald = wald;
        this.start = start;
        this.ende = ende;
        this.abfahrt = abfahrt;
    }

    /**
     * Getter for <code>public.plan.id</code>.
     */
    @NotNull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.plan.id</code>.
     */
    public Plan setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.plan.woche</code>.
     */
    @Size(max = 255)
    public String getWoche() {
        return this.woche;
    }

    /**
     * Setter for <code>public.plan.woche</code>.
     */
    public Plan setWoche(String woche) {
        this.woche = woche;
        return this;
    }

    /**
     * Getter for <code>public.plan.wochentag</code>.
     */
    @Size(max = 255)
    public String getWochentag() {
        return this.wochentag;
    }

    /**
     * Setter for <code>public.plan.wochentag</code>.
     */
    public Plan setWochentag(String wochentag) {
        this.wochentag = wochentag;
        return this;
    }

    /**
     * Getter for <code>public.plan.datum</code>.
     */
    public LocalDate getDatum() {
        return this.datum;
    }

    /**
     * Setter for <code>public.plan.datum</code>.
     */
    public Plan setDatum(LocalDate datum) {
        this.datum = datum;
        return this;
    }

    /**
     * Getter for <code>public.plan.wald</code>.
     */
    public Boolean getWald() {
        return this.wald;
    }

    /**
     * Setter for <code>public.plan.wald</code>.
     */
    public Plan setWald(Boolean wald) {
        this.wald = wald;
        return this;
    }

    /**
     * Getter for <code>public.plan.start</code>.
     */
    public LocalTime getStart() {
        return this.start;
    }

    /**
     * Setter for <code>public.plan.start</code>.
     */
    public Plan setStart(LocalTime start) {
        this.start = start;
        return this;
    }

    /**
     * Getter for <code>public.plan.ende</code>.
     */
    public LocalTime getEnde() {
        return this.ende;
    }

    /**
     * Setter for <code>public.plan.ende</code>.
     */
    public Plan setEnde(LocalTime ende) {
        this.ende = ende;
        return this;
    }

    /**
     * Getter for <code>public.plan.abfahrt</code>.
     */
    public LocalTime getAbfahrt() {
        return this.abfahrt;
    }

    /**
     * Setter for <code>public.plan.abfahrt</code>.
     */
    public Plan setAbfahrt(LocalTime abfahrt) {
        this.abfahrt = abfahrt;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Plan other = (Plan) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.woche == null) {
            if (other.woche != null)
                return false;
        }
        else if (!this.woche.equals(other.woche))
            return false;
        if (this.wochentag == null) {
            if (other.wochentag != null)
                return false;
        }
        else if (!this.wochentag.equals(other.wochentag))
            return false;
        if (this.datum == null) {
            if (other.datum != null)
                return false;
        }
        else if (!this.datum.equals(other.datum))
            return false;
        if (this.wald == null) {
            if (other.wald != null)
                return false;
        }
        else if (!this.wald.equals(other.wald))
            return false;
        if (this.start == null) {
            if (other.start != null)
                return false;
        }
        else if (!this.start.equals(other.start))
            return false;
        if (this.ende == null) {
            if (other.ende != null)
                return false;
        }
        else if (!this.ende.equals(other.ende))
            return false;
        if (this.abfahrt == null) {
            if (other.abfahrt != null)
                return false;
        }
        else if (!this.abfahrt.equals(other.abfahrt))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.woche == null) ? 0 : this.woche.hashCode());
        result = prime * result + ((this.wochentag == null) ? 0 : this.wochentag.hashCode());
        result = prime * result + ((this.datum == null) ? 0 : this.datum.hashCode());
        result = prime * result + ((this.wald == null) ? 0 : this.wald.hashCode());
        result = prime * result + ((this.start == null) ? 0 : this.start.hashCode());
        result = prime * result + ((this.ende == null) ? 0 : this.ende.hashCode());
        result = prime * result + ((this.abfahrt == null) ? 0 : this.abfahrt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Plan (");

        sb.append(id);
        sb.append(", ").append(woche);
        sb.append(", ").append(wochentag);
        sb.append(", ").append(datum);
        sb.append(", ").append(wald);
        sb.append(", ").append(start);
        sb.append(", ").append(ende);
        sb.append(", ").append(abfahrt);

        sb.append(")");
        return sb.toString();
    }
}
