package de.netgo.wochenplan;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Benutzer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
