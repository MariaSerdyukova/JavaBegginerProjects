package main.java;

import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Maria Serdyukova
 */
public class BySuitInValueOrder implements Comparator<Card> {
    @Override
    public int compare(Card c1, Card c2) {
        int suitPosition = c1.getSuit().ordinal() - c2.getSuit().ordinal();
        int value = c1.getValue() - c2.getValue();

        if (suitPosition == 0 && value > 0) {
            return 1;
        } else if (suitPosition < 0) {
            return -1;
        } else if (suitPosition == 0 && value < 0) {
            return -1;
        } else if (suitPosition == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
