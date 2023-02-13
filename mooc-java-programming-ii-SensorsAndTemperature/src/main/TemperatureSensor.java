/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Random;

/**
 * @author Maria Serdyukova
 */
public class TemperatureSensor implements Sensor {
    private boolean sensorOn;

    public TemperatureSensor() {
        sensorOn = false;
    }

    @Override
    public boolean isOn() {
        return sensorOn;
    }

    @Override
    public void setOn() {
        sensorOn = true;
    }

    @Override
    public void setOff() {
        sensorOn = false;
    }

    @Override
    public int read() {
        if (!sensorOn) {
            throw new IllegalStateException("Sensor is off");
        }
        Random random = new Random();
        return random.ints(-30, 31)
            .findFirst()
            .getAsInt();
    }
}
