package de.netgo.kitaplan.plan;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/public")

public class PlanController {
  private final PlanService planService;

  PlanController(final PlanService planService) {
    this.planService = planService;
  }

  @GetMapping(value = "/plan", produces = APPLICATION_JSON_VALUE)
  public List<PlanDto> getPlan() {
    return planService.findAllPlaene();
  }

  @GetMapping(value = "/plan/{woche}", produces = APPLICATION_JSON_VALUE)
  public List<PlanDto> getPlanByWoche(@PathVariable("woche") String woche) {
    return planService.findPlanByWoche(woche);
  }
}
