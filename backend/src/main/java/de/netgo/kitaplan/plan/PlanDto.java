package de.netgo.kitaplan.plan;

import java.util.UUID;

public class PlanDto {
  private UUID id;
  private String woche;
  private String wochentag;
  private String datum;
  private boolean wald;
  private String start;
  private String ende;
  private String abfahrt;

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

  public String getWochentag() {
    return wochentag;
  }

  public PlanDto setWochentag(final String wochentag) {
    this.wochentag = wochentag;
    return this;
  }

  public String getDatum() {
    return datum;
  }

  public PlanDto setDatum(final String datum) {
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

  public String getStart() {
    return start;
  }

  public PlanDto setStart(final String start) {
    this.start = start;
    return this;
  }

  public String getEnde() {
    return ende;
  }

  public PlanDto setEnde(final String ende) {
    this.ende = ende;
    return this;
  }

  public String getAbfahrt() {
    return abfahrt;
  }

  public PlanDto setAbfahrt(final String abfahrt) {
    this.abfahrt = abfahrt;
    return this;
  }
}
