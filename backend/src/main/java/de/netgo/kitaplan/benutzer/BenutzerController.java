package de.netgo.kitaplan.benutzer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/benutzer")
public class BenutzerController {
  private final BenutzerService benutzerService;

  BenutzerController(final BenutzerService benutzerService) {
    this.benutzerService = benutzerService;
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public List<BenutzerDto> getBenutzer() {
    return benutzerService.findAllBenutzer();
  }

  @GetMapping(value = "/{planId}", produces = APPLICATION_JSON_VALUE)
  public List<BenutzerDto> getBenutzerByPlan(@PathVariable final UUID planId) {
    return benutzerService.findAllBenutzerByPlan(planId);
  }
}
