/*
 * This file is generated by jOOQ.
 */
package de.netgo.kitaplan.jooq.tables.pojos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Plan implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String tag;
    private LocalDate datum;

    public Plan() {}

    public Plan(Plan value) {
        this.id = value.id;
        this.tag = value.tag;
        this.datum = value.datum;
    }

    public Plan(
        UUID id,
        String tag,
        LocalDate datum
    ) {
        this.id = id;
        this.tag = tag;
        this.datum = datum;
    }

    /**
     * Getter for <code>public.plan.id</code>.
     */
    @NotNull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.plan.id</code>.
     */
    public Plan setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.plan.tag</code>.
     */
    @Size(max = 255)
    public String getTag() {
        return this.tag;
    }

    /**
     * Setter for <code>public.plan.tag</code>.
     */
    public Plan setTag(String tag) {
        this.tag = tag;
        return this;
    }

    /**
     * Getter for <code>public.plan.datum</code>.
     */
    public LocalDate getDatum() {
        return this.datum;
    }

    /**
     * Setter for <code>public.plan.datum</code>.
     */
    public Plan setDatum(LocalDate datum) {
        this.datum = datum;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Plan other = (Plan) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.tag == null) {
            if (other.tag != null)
                return false;
        }
        else if (!this.tag.equals(other.tag))
            return false;
        if (this.datum == null) {
            if (other.datum != null)
                return false;
        }
        else if (!this.datum.equals(other.datum))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.tag == null) ? 0 : this.tag.hashCode());
        result = prime * result + ((this.datum == null) ? 0 : this.datum.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Plan (");

        sb.append(id);
        sb.append(", ").append(tag);
        sb.append(", ").append(datum);

        sb.append(")");
        return sb.toString();
    }
}
