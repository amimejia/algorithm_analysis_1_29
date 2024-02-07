package edu.guilford;

import java.util.Random;

//We want to sort Card objects, so they need to implement
//the Comparable interface
//"implements" the Comparable interface means that
//there is a compareTo method that can compare two Card objects
//and return a distinct integer value

public class Card implements Comparable<Card> {
    public enum Suit{
        //enum is enumeration
        //it allows us to use words instead of numbers
        //for example, instead of using 0 for hearts, we can use Suit.HEARTS
    HEARTS, DIAMONDS, CLUBS, SPADES
    }
    public enum Rank{
        //enum is enumeration
        //it allows us to use words instead of numbers
        //for example, instead of using 0 for hearts, we can use Suit.HEARTS
        //ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
        ACE (11),
        TWO (2),
        THREE (3),
        FOUR (4),
        FIVE (5),
        SIX (6),
        SEVEN (7),
        EIGHT (8),
        NINE (9),
        TEN (10),
        JACK (10),
        QUEEN (10),
        KING (10);
        
        //attribute
        private int rank;

        //constructor for one of the enum items
        private Rank(int rank){
            this.rank = rank;
        }

        //getter
        public int getRank(){
            return rank;
        }
    }

    //attributes for a Card Object
    private Suit suit;
    private Rank rank;
    private Random rand = new Random();

    //constructor
    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }
    //constructor
    public Card(){
        int suitIndex = rand.nextInt(Suit.values().length);
        int rankIndex = rand.nextInt(Rank.values().length);
        this.suit = Suit.values()[suitIndex];
        this.rank = Rank.values()[rankIndex];
    }
    
    //getters
    public Suit getSuit(){
        return suit;
    }
    public Rank getRank(){
        return rank;
    }

    //toString method
    @Override
    public String toString(){
        return rank + " of " + suit;
    }
    
    @Override
    public int compareTo(Card otherCard){
        //compare the ranks of the two cards
        //if the ranks are the same, compare the suits
        if (this.rank.getRank() < otherCard.getRank().getRank()){
            return -1;
        } else if (this.rank.getRank() > otherCard.getRank().getRank()){
            return 1;
        } else {
            if (this.suit.ordinal() < otherCard.getSuit().ordinal()){
                return -1;
            } else if (this.suit.ordinal() > otherCard.getSuit().ordinal()){
                return 1;
            } else {
                return 0;
            } 
    }
    }
}
