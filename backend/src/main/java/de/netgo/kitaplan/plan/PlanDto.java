package de.netgo.kitaplan.plan;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class PlanDto {
  private UUID id;
  private String woche;
  private LocalDate datum;
  private boolean wald;
  private boolean offen;
  private LocalTime start;
  private LocalTime ende;
  private LocalTime abfahrt;
  private String kommentar;

  public UUID getId() {
    return id;
  }

  public PlanDto setId(final UUID id) {
    this.id = id;
    return this;
  }

  public String getWoche() {
    return woche;
  }

  public PlanDto setWoche(final String woche) {
    this.woche = woche;
    return this;
  }

  public LocalDate getDatum() {
    return datum;
  }

  public PlanDto setDatum(final LocalDate datum) {
    this.datum = datum;
    return this;
  }

  public boolean isWald() {
    return wald;
  }

  public PlanDto setWald(final boolean wald) {
    this.wald = wald;
    return this;
  }

  public boolean isOffen() {
    return offen;
  }

  public PlanDto setOffen(final boolean offen) {
    this.offen = offen;
    return this;
  }

  public LocalTime getStart() {
    return start;
  }

  public PlanDto setStart(final LocalTime start) {
    this.start = start;
    return this;
  }

  public LocalTime getEnde() {
    return ende;
  }

  public PlanDto setEnde(final LocalTime ende) {
    this.ende = ende;
    return this;
  }

  public LocalTime getAbfahrt() {
    return abfahrt;
  }

  public PlanDto setAbfahrt(final LocalTime abfahrt) {
    this.abfahrt = abfahrt;
    return this;
  }

  public String getKommentar() {
    return kommentar;
  }

  public PlanDto setKommentar(final String kommentar) {
    this.kommentar = kommentar;
    return this;
  }
}
