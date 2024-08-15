/*
 * This file is generated by jOOQ.
 */
package de.netgo.kitaplan.jooq.tables.daos;


import de.netgo.kitaplan.jooq.tables.BenutzerPlan;
import de.netgo.kitaplan.jooq.tables.records.BenutzerPlanRecord;

import java.util.List;
import java.util.UUID;

import org.jooq.Configuration;
import org.jooq.Record2;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
@Repository
public class BenutzerPlanDao extends DAOImpl<BenutzerPlanRecord, de.netgo.kitaplan.jooq.tables.pojos.BenutzerPlan, Record2<UUID, UUID>> {

    /**
     * Create a new BenutzerPlanDao without any configuration
     */
    public BenutzerPlanDao() {
        super(BenutzerPlan.BENUTZER_PLAN, de.netgo.kitaplan.jooq.tables.pojos.BenutzerPlan.class);
    }

    /**
     * Create a new BenutzerPlanDao with an attached configuration
     */
    @Autowired
    public BenutzerPlanDao(Configuration configuration) {
        super(BenutzerPlan.BENUTZER_PLAN, de.netgo.kitaplan.jooq.tables.pojos.BenutzerPlan.class, configuration);
    }

    @Override
    public Record2<UUID, UUID> getId(de.netgo.kitaplan.jooq.tables.pojos.BenutzerPlan object) {
        return compositeKeyRecord(object.getBenutzerId(), object.getPlanId());
    }

    /**
     * Fetch records that have <code>benutzer_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.netgo.kitaplan.jooq.tables.pojos.BenutzerPlan> fetchRangeOfBenutzerId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(BenutzerPlan.BENUTZER_PLAN.BENUTZER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>benutzer_id IN (values)</code>
     */
    public List<de.netgo.kitaplan.jooq.tables.pojos.BenutzerPlan> fetchByBenutzerId(UUID... values) {
        return fetch(BenutzerPlan.BENUTZER_PLAN.BENUTZER_ID, values);
    }

    /**
     * Fetch records that have <code>plan_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.netgo.kitaplan.jooq.tables.pojos.BenutzerPlan> fetchRangeOfPlanId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(BenutzerPlan.BENUTZER_PLAN.PLAN_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>plan_id IN (values)</code>
     */
    public List<de.netgo.kitaplan.jooq.tables.pojos.BenutzerPlan> fetchByPlanId(UUID... values) {
        return fetch(BenutzerPlan.BENUTZER_PLAN.PLAN_ID, values);
    }
}