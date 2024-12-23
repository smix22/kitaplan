/*
 * This file is generated by jOOQ.
 */
package de.netgo.kitaplan.jooq.tables.records;


import de.netgo.kitaplan.jooq.tables.Plan;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class PlanRecord extends UpdatableRecordImpl<PlanRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.plan.id</code>.
     */
    public PlanRecord setId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.plan.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.plan.woche</code>.
     */
    public PlanRecord setWoche(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.plan.woche</code>.
     */
    @Size(max = 255)
    public String getWoche() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.plan.datum</code>.
     */
    public PlanRecord setDatum(LocalDate value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.plan.datum</code>.
     */
    public LocalDate getDatum() {
        return (LocalDate) get(2);
    }

    /**
     * Setter for <code>public.plan.wald</code>.
     */
    public PlanRecord setWald(Boolean value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.plan.wald</code>.
     */
    public Boolean getWald() {
        return (Boolean) get(3);
    }

    /**
     * Setter for <code>public.plan.offen</code>.
     */
    public PlanRecord setOffen(Boolean value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.plan.offen</code>.
     */
    public Boolean getOffen() {
        return (Boolean) get(4);
    }

    /**
     * Setter for <code>public.plan.start</code>.
     */
    public PlanRecord setStart(LocalTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.plan.start</code>.
     */
    public LocalTime getStart() {
        return (LocalTime) get(5);
    }

    /**
     * Setter for <code>public.plan.ende</code>.
     */
    public PlanRecord setEnde(LocalTime value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.plan.ende</code>.
     */
    public LocalTime getEnde() {
        return (LocalTime) get(6);
    }

    /**
     * Setter for <code>public.plan.abfahrt</code>.
     */
    public PlanRecord setAbfahrt(LocalTime value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public.plan.abfahrt</code>.
     */
    public LocalTime getAbfahrt() {
        return (LocalTime) get(7);
    }

    /**
     * Setter for <code>public.plan.kommentar</code>.
     */
    public PlanRecord setKommentar(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public.plan.kommentar</code>.
     */
    @Size(max = 255)
    public String getKommentar() {
        return (String) get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PlanRecord
     */
    public PlanRecord() {
        super(Plan.PLAN);
    }

    /**
     * Create a detached, initialised PlanRecord
     */
    public PlanRecord(UUID id, String woche, LocalDate datum, Boolean wald, Boolean offen, LocalTime start, LocalTime ende, LocalTime abfahrt, String kommentar) {
        super(Plan.PLAN);

        setId(id);
        setWoche(woche);
        setDatum(datum);
        setWald(wald);
        setOffen(offen);
        setStart(start);
        setEnde(ende);
        setAbfahrt(abfahrt);
        setKommentar(kommentar);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PlanRecord
     */
    public PlanRecord(de.netgo.kitaplan.jooq.tables.pojos.Plan value) {
        super(Plan.PLAN);

        if (value != null) {
            setId(value.getId());
            setWoche(value.getWoche());
            setDatum(value.getDatum());
            setWald(value.getWald());
            setOffen(value.getOffen());
            setStart(value.getStart());
            setEnde(value.getEnde());
            setAbfahrt(value.getAbfahrt());
            setKommentar(value.getKommentar());
            resetChangedOnNotNull();
        }
    }
}
