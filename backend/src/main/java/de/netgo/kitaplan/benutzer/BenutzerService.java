package de.netgo.kitaplan.benutzer;

import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static de.netgo.kitaplan.jooq.Tables.*;
import static org.jooq.impl.DSL.multiset;

@Service
public class BenutzerService {

  private final DSLContext dsl;

  BenutzerService(final DSLContext dsl) {
    this.dsl = dsl;
  }

  @Transactional(readOnly = true)
  public List<BenutzerDto> findAllBenutzer() {
    return dsl.selectFrom(BENUTZER).fetchInto(BenutzerDto.class);
  }

  @Transactional(readOnly = true)
  public List<BenutzerDto> findAllBenutzerByPlan(final UUID planId) {
    return dsl.select()
      .from(BENUTZER)
      .join(BENUTZER_PLAN).on(BENUTZER_PLAN.BENUTZER_ID.eq(BENUTZER.ID))
      .where(BENUTZER_PLAN.PLAN_ID.eq(planId))
      .fetchInto(BenutzerDto.class);
  }
}
