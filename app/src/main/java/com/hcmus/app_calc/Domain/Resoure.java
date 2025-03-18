package com.hcmus.app_calc.Domain;

import java.io.Serializable;

public class Resoure implements Serializable {
    private Double numberA;
    private Double numberB;
    private Double numberC;

    public Double getNumberA() {
        return numberA;
    }

    public void setNumberA(Double numberA) {
        this.numberA = numberA;
    }

    public Double getNumberB() {
        return numberB;
    }

    public void setNumberB(Double numberB) {
        this.numberB = numberB;
    }

    public Double getNumberC() {
        return numberC;
    }

    public void setNumberC(Double numberC) {
        this.numberC = numberC;
    }

    @Override
    public String toString() {
        return "Resoure{" +
                "numberA=" + numberA +
                ", numberB=" + numberB +
                ", numberC=" + numberC +
                '}';
    }
}
