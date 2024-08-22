package de.netgo.kitaplan.plan;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/public")

public class PlanController {

  private final PlanService planService;

  PlanController(final PlanService planService) {
    this.planService = planService;
  }

  @GetMapping(value = "/plaene", produces = APPLICATION_JSON_VALUE)
  public List<PlanDto> getPlaene() {
    return planService.findAllPlaene();
  }

  @GetMapping(value = "/plan/{woche}", produces = APPLICATION_JSON_VALUE)
  public List<PlanDto> getPlanByWoche(@PathVariable("woche") String woche) {
    return planService.findPlanByWoche(woche);
  }

  @GetMapping(value = "/plan/tag/{id}", produces = APPLICATION_JSON_VALUE)
  public List<PlanDto> getTagById(@PathVariable("id") UUID id) {
    return planService.findTagById(id);
  }
  @GetMapping(value = "/plan/woche/{datum}", produces = APPLICATION_JSON_VALUE)
  public List<PlanDto> getPlanByDatum(@PathVariable("datum") LocalDate datum) {
    return planService.findPlanByDatum(datum);
  }

  @GetMapping(value = "/plan/woche/datum/{datum}", produces = APPLICATION_JSON_VALUE)
  public List<PlanDto> getCurrentPlan(@PathVariable("datum") LocalDate datum) {
    return planService.findCurrentPlan(datum);
  }

  @PutMapping(value="/plan/tag/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> updatePlan(@RequestBody final PlanDto plan, @PathVariable("id") UUID id) {
    planService.updatePlan(plan, id);
    return ResponseEntity.ok().build();
  }

}
