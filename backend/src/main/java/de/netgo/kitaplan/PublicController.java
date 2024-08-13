package de.netgo.kitaplan;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import de.netgo.kitaplan.benutzer.BenutzerDto;
import de.netgo.kitaplan.benutzer.BenutzerService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/public")
public class PublicController {

  private final BenutzerService benutzerService;

  PublicController(final BenutzerService benutzerService) {
    this.benutzerService = benutzerService;
  }

  @GetMapping(value = "/wochenplan", produces = APPLICATION_JSON_VALUE)
  public String getBenutzer() {
    return "["
        + "{\"tag\":\"1\"},"
        + "{\"tag\":\"2\"}"
        + "]";
  }

  @GetMapping(value = "/{planId}", produces = APPLICATION_JSON_VALUE)
  public List<BenutzerDto> getBenutzerByPlan(@PathVariable final UUID planId) {
    return benutzerService.findAllBenutzerByPlan(planId);
  }
}
