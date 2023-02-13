/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maria Serdyukova
 */
public class AverageSensor implements Sensor {

    private ArrayList<Sensor> sensors;
    private ArrayList<Integer> averages;

    public AverageSensor() {
        sensors = new ArrayList<>();
        averages = new ArrayList<>();
    }

    public void addSensor(Sensor toAdd) {
        sensors.add(toAdd);
    }

    @Override
    public boolean isOn() {
        if (sensors.isEmpty()) {
            return false;
        }
        return sensors.get(0).isOn();
    }

    @Override
    public void setOn() {
        sensors.stream().forEach(sensor -> sensor.setOn());
    }

    @Override
    public void setOff() {
        sensors.stream().forEach(sensor -> sensor.setOff());
    }

    @Override
    public int read() {

        if (!isOn()) {
            throw new IllegalStateException("Sensor is off");
        }

        int count = 0;
        int sum = 0;

        for (Sensor sensor : sensors) {
            sum += sensor.read();
            count++;
        }
        averages.add(sum / count);
        return sum / count;
    }

    public List<Integer> readings() {
        return averages;
    }
}
