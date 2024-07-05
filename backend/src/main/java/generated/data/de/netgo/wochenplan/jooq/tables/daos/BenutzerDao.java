/*
 * This file is generated by jOOQ.
 */
package de.netgo.wochenplan.jooq.tables.daos;


import de.netgo.wochenplan.jooq.tables.Benutzer;
import de.netgo.wochenplan.jooq.tables.records.BenutzerRecord;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
@Repository
public class BenutzerDao extends DAOImpl<BenutzerRecord, de.netgo.wochenplan.jooq.tables.pojos.Benutzer, UUID> {

    /**
     * Create a new BenutzerDao without any configuration
     */
    public BenutzerDao() {
        super(Benutzer.BENUTZER, de.netgo.wochenplan.jooq.tables.pojos.Benutzer.class);
    }

    /**
     * Create a new BenutzerDao with an attached configuration
     */
    @Autowired
    public BenutzerDao(Configuration configuration) {
        super(Benutzer.BENUTZER, de.netgo.wochenplan.jooq.tables.pojos.Benutzer.class, configuration);
    }

    @Override
    public UUID getId(de.netgo.wochenplan.jooq.tables.pojos.Benutzer object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.netgo.wochenplan.jooq.tables.pojos.Benutzer> fetchRangeOfId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(Benutzer.BENUTZER.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<de.netgo.wochenplan.jooq.tables.pojos.Benutzer> fetchById(UUID... values) {
        return fetch(Benutzer.BENUTZER.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public de.netgo.wochenplan.jooq.tables.pojos.Benutzer fetchOneById(UUID value) {
        return fetchOne(Benutzer.BENUTZER.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<de.netgo.wochenplan.jooq.tables.pojos.Benutzer> fetchOptionalById(UUID value) {
        return fetchOptional(Benutzer.BENUTZER.ID, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.netgo.wochenplan.jooq.tables.pojos.Benutzer> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Benutzer.BENUTZER.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<de.netgo.wochenplan.jooq.tables.pojos.Benutzer> fetchByName(String... values) {
        return fetch(Benutzer.BENUTZER.NAME, values);
    }
}