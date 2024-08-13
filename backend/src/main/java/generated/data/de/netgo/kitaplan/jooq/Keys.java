/*
 * This file is generated by jOOQ.
 */
package de.netgo.kitaplan.jooq;


import de.netgo.kitaplan.jooq.tables.Benutzer;
import de.netgo.kitaplan.jooq.tables.BenutzerPlan;
import de.netgo.kitaplan.jooq.tables.Plan;
import de.netgo.kitaplan.jooq.tables.records.BenutzerPlanRecord;
import de.netgo.kitaplan.jooq.tables.records.BenutzerRecord;
import de.netgo.kitaplan.jooq.tables.records.PlanRecord;

import org.jooq.ForeignKey;
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

    public static final UniqueKey<BenutzerRecord> BENUTZER_PKEY = Internal.createUniqueKey(Benutzer.BENUTZER, DSL.name("benutzer_pkey"), new TableField[] { Benutzer.BENUTZER.ID }, true);
    public static final UniqueKey<PlanRecord> PLAN_PKEY = Internal.createUniqueKey(Plan.PLAN, DSL.name("plan_pkey"), new TableField[] { Plan.PLAN.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<BenutzerPlanRecord, BenutzerRecord> BENUTZER_PLAN__BENUTZER_PLAN_BENUTZER_ID_FKEY = Internal.createForeignKey(BenutzerPlan.BENUTZER_PLAN, DSL.name("benutzer_plan_benutzer_id_fkey"), new TableField[] { BenutzerPlan.BENUTZER_PLAN.BENUTZER_ID }, Keys.BENUTZER_PKEY, new TableField[] { Benutzer.BENUTZER.ID }, true);
    public static final ForeignKey<BenutzerPlanRecord, PlanRecord> BENUTZER_PLAN__BENUTZER_PLAN_PLAN_ID_FKEY = Internal.createForeignKey(BenutzerPlan.BENUTZER_PLAN, DSL.name("benutzer_plan_plan_id_fkey"), new TableField[] { BenutzerPlan.BENUTZER_PLAN.PLAN_ID }, Keys.PLAN_PKEY, new TableField[] { Plan.PLAN.ID }, true);
}