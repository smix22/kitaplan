package de.netgo.kitaplan.plan;

import static de.netgo.kitaplan.jooq.Tables.PLAN;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.jooq.DSLContext;
import org.jooq.impl.QOM;
import org.jooq.impl.QOM.CurrentDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlanService {

  private final DSLContext dsl;

  PlanService(final DSLContext dsl) {
    this.dsl = dsl;
  }

  @Transactional(readOnly = true)
  public List<PlanDto> findAllPlaene() {
    return dsl.select(PLAN.WOCHE, PLAN.DATUM)
        .distinctOn(PLAN.WOCHE)
        .from(PLAN)
        .where(PLAN.DATUM.greaterOrEqual(LocalDate.now()))
        .orderBy(PLAN.WOCHE, PLAN.DATUM)
        .fetchInto(PlanDto.class);
  }

  @Transactional(readOnly = true)
  public List<PlanDto> findPlanByDatum(final LocalDate datum) {
    var woche = dsl.select(PLAN.WOCHE)
        .from(PLAN)
        .where(PLAN.DATUM.eq(datum));
    return dsl.select()
        .from(PLAN)
        .where(PLAN.WOCHE
            .eq(woche))
        .orderBy(PLAN.DATUM)
        .fetchInto(PlanDto.class);
  }

  @Transactional(readOnly = true)
  public List<PlanDto> findCurrentPlan(final LocalDate datum) {
    return dsl.select()
        .from(PLAN)
        .where(PLAN.DATUM.between(datum)
            .and(datum.plusDays(6)))
        .orderBy(PLAN.DATUM)
        .fetchInto(PlanDto.class);
  }

  @Transactional(readOnly = true)
  public List<PlanDto> findPlanByWoche(final String woche) {
    return dsl.select()
        .from(PLAN)
        .where(PLAN.WOCHE.eq(woche))
        .orderBy(PLAN.DATUM)
        .fetchInto(PlanDto.class);
  }

  @Transactional(readOnly = true)
  public List<PlanDto> findTagById(final UUID id) {
    return dsl.select()
        .from(PLAN)
        .where(PLAN.ID.eq(id))
        .fetchInto(PlanDto.class);
  }

  @Transactional
  public void updatePlan(final PlanDto plan, final UUID id) {
    dsl.update(PLAN)
        .set(PLAN.WALD, plan.isWald())
        .set(PLAN.OFFEN, plan.isOffen())
        .set(PLAN.START, plan.getStart())
        .set(PLAN.ENDE, plan.getEnde())
        .set(PLAN.ABFAHRT, plan.getAbfahrt())
        .set(PLAN.KOMMENTAR, plan.getKommentar())
        .where(PLAN.ID.eq(id))
        .execute();
  }
}
