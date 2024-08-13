package de.netgo.kitaplan.benutzer;

import java.util.UUID;

public class BenutzerDto {
  private UUID id;
  private String name;
  private String rolle;

  public UUID getId() {
    return id;
  }

  public BenutzerDto setId(UUID id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public BenutzerDto setName(String name) {
    this.name = name;
    return this;
  }

  public String getRolle() {
    return rolle;
  }

  public BenutzerDto setRolle(String rolle) {
    this.rolle = rolle;
    return this;
  }
}
