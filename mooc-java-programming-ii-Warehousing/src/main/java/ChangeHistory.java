package main.java;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Maria Serdyukova
 */
public class ChangeHistory {
    private ArrayList<Double> history;

    public ChangeHistory() {
        this.history = new ArrayList<>();
    }

    public void add(double status) {
        this.history.add(status);
    }

    public void clear() {
        this.history.clear();
    }

    public double maxValue() {
        double max = history.get(0);
        for (double value : history) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public double minValue() {
        double min = history.get(0);
        for (double value : history) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    public double average() {
        double sum = 0;
        double count = 0;
        for (double value : history) {
            sum += value;
            count += 1;
        }

        if (count == 0) {
            return 0;
        }
        return sum / count;
    }

    @Override
    public String toString() {
        return history.toString();
    }
}
