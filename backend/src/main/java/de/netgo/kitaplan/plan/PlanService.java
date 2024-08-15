package de.netgo.kitaplan.plan;

import static de.netgo.kitaplan.jooq.Tables.PLAN;

import java.util.List;
import org.jooq.DSLContext;
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
    return dsl.select()
        .from(PLAN)
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
}
