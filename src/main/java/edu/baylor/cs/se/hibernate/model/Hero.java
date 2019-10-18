package edu.baylor.cs.se.hibernate.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Hero implements Serializable {


    @Id
    @GeneratedValue
    private Long id;


    @Column(unique = true)
    private String name;

    @Column
    private String race;

    @Column
    private double strength;

    @Column
    private boolean isDarkSide;

    public Hero() {

    }

    public Hero(String name, String race, double strength, boolean isDarkSide) {
        this.name = name;
        this.race = race;
        this.strength = strength;
        this.isDarkSide = isDarkSide;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public boolean isDarkSide() {
        return isDarkSide;
    }

    public void setDarkSide(boolean darkSide) {
        isDarkSide = darkSide;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
