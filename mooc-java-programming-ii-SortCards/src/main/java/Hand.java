package main.java;

import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Maria Serdyukova
 */
public class Hand implements Comparable<Hand> {

    private ArrayList<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public void print() {
        this.cards.stream().forEach(card -> System.out.println(card));
    }

    public void sort() {
        Collections.sort(cards);
    }

    public int compareTo(Hand otherHand) {
        int currSum = cards.stream().mapToInt(valueToSum -> valueToSum.getValue()).sum();
        int otherSum = otherHand.getCards().stream().mapToInt(valueToSum -> valueToSum.getValue()).sum();

        return currSum - otherSum;
    }

    public void sortBySuit() {
        Collections.sort(cards, new BySuitInValueOrder());
    }
}
