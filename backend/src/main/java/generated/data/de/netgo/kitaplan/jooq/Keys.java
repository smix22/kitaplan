/*
 * This file is generated by jOOQ.
 */
package de.netgo.kitaplan.jooq;


import de.netgo.kitaplan.jooq.tables.Plan;
import de.netgo.kitaplan.jooq.tables.records.PlanRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<PlanRecord> PLAN_PKEY = Internal.createUniqueKey(Plan.PLAN, DSL.name("plan_pkey"), new TableField[] { Plan.PLAN.ID }, true);
}
