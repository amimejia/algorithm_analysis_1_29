package edu.guilford;

import java.util.Arrays;
import java.util.Random;

public class AlgorithmAnalysisDriver {
    public static void main(String[] args) {
        //final int N = 1000;
        //int[] randomInts = new int[N];
        Random rand = new Random();
        // for (int i = 0; i < N; i++) {
        //     randomInts[i] = rand.nextInt();
        // }
        //long startTime = System.nanoTime();
        // // find the maximum value in randomInts
        // int max = randomInts[0];
        // for (int i = 1; i < N; i++) {
        //     if (randomInts[i] > max) {
        //         max = randomInts[i];
        //     }
        // }
        // long endTime = System.nanoTime();
        // System.out.print("The maximum value in randomInts is " + max + ". ");
        // System.out.println("The algorithm took " + (endTime - startTime) / 1.e+9 + " seconds to run.");

        // Make a random Card Object
        Card card = new Card();
        System.out.println(card);

        Card card2 = new Card();
        System.out.println(card2);

        System.out.println("The result of comparing the two cards is " + card.compareTo(card2) + ".");

        //Let's build a deck of Card objects
        Card[] deck = new Card[52];
        //Let's assign each card a suit and then a rank
        //for each suit in our list of values in our enumeration suit, 
        for (Card.Suit suit: Card.Suit.values()){
            //for each rank, then we're going to create a card in the right place
            for (Card.Rank rank: Card.Rank.values()){
                //ordinal gives us back an index, not a value, but an index
                deck[suit.ordinal() * 13 + rank.ordinal()] = new Card (suit,rank);
            }
        }

        System.out.println(Arrays.toString(deck));


        //let's shuffle the deck: 7 times the length of the deck so 364 times (there's some mathematical equation that 7 times is random enough)
        for (int i = 0; i  < 7 * deck.length; i++){
            int index1 = rand.nextInt(deck.length);
            int index2 = rand.nextInt(deck.length);
            swap(deck, index1, index2);
        }
        System.out.println(Arrays.toString(deck));

        //let's sort the deck
        long startTime = System.nanoTime();
        selectionSort(deck);
        long endTime = System.nanoTime();
        System.out.println("Sorted deck: " + Arrays.toString(deck));
        System.out.println("The algorithm took " + (endTime - startTime) / 1.e+9 + " seconds to run.");


        //Let's build a big array of random Card objects
        final int M = 1000;
        Card[] randomCards = new Card[M];
        for (int i = 0; i < M; i++){
            randomCards[i] = new Card();
        }

        System.out.println("Was it sorted?/This is my verfication method : " + isSorted(randomCards));

        //then sort them
        // startTime = System.nanoTime();
        // selectionSort(randomCards);
        // endTime = System.nanoTime();
        // System.out.println("The algorithm took " + (endTime - startTime) / 1.e+9 + " seconds to run.");
        // System.out.println("Was it sorted?/This is my verfication method : " + isSorted(randomCards));

        //Search for a random Card in the array
        Card target = new Card();
        startTime = System.nanoTime();
        int index = sequentialSearch(randomCards, target);
        endTime = System.nanoTime();
        System.out.println("The algorithm took " + (endTime - startTime) / 1.e+9 + " seconds to run.");
        
        //now the sequential search
        startTime = System.nanoTime();
        index = binarySearch(randomCards, target);
        endTime = System.nanoTime();
        System.out.println("The algorithm took " + (endTime - startTime) / 1.e+9 + " seconds to run.");
    }

    // walk through the unsorted array and find the smallest Card
    // then swap it to the front of the unsorted part of the array
    public static void selectionSort(Card[] array) {
        // the array starts out unsorted and i keeps track of the sorted part
        for (int i = 0; i < array.length - 1; i++) {
            // minIndex will be the smallest Card in the unsorted part of the array
            int minIndex = i;
            // start at the rest of the unsorted apart and find the smallest Card
            for (int j = i + 1; j < array.length; j++) {
                //if array[j] < array[minIndex], we have a new minIndex
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
                //repeat until we have all the Card objects compared
            }
            //swap the smallest Card with the first unsorted Card
            swap(array, i, minIndex);
        }
    }

    public static void swap (Object[] array, int i, int j){
        //store i in a temp variable
        Object temp = array[i];
        //take the value at i and put it into i
        array[i] = array[j];
        array[j] = temp;
    }

    //quicksort is a recursive algorithm
    public static void quickSort(Card[] array){
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(Card[] array, int low, int high){
        if (low < high){
            int pivotLocation = partition(array, low, high);
            quickSort(array, low, pivotLocation - 1);
            quickSort(array, pivotLocation + 1, high);
        }
    }

    //the foundation of quiksort is partitioning an array into two parts
    //those that come before the pivot and those that come after
    //the pivot is the middle element in the array and we first move it to the end
    
    public static int partition(Card[] array, int low, int high){
        //low is the start of the unsorted array
        //high is the end
        //find the pivot
        int pivot = (low + high) / 2;
        swap(array, pivot, high);
        //get the value of the pivot
        Card pivotValue = array[high];
        //and set up our pionters, one left and one right
        int left = low;
        int right = high - 1;
        //as long as the pointers haven't crossed, identify what objects should be swapped
        while (left <= right){
            //march from the left until we find a Card that is greater than the pivor
            while (array[left].compareTo(pivotValue)<0){
                left++;
            }
            //march from the right until we find a Card that is less than the pivot
            while (right >= 0 && array[right].compareTo(pivotValue) > 0){
                right--;
            }
            //if the pointers haven't crossed, swap the two objects/cards and move the pointers one more
            if (left < right){
                swap(array, left, right);
                left++;
                right--;
            }
        }
        //when the pointers cross, swap the pivot with the left pointer
        swap(array, high, left);
        //after this point, the pivot is in the right places, 
        //and we just need to report where that pivot now is
        return left;
    }

    //Is the Array of Card objects sorted?
    public static boolean isSorted(Card[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    //build a sequential search algorithm
    public static int sequentialSearch(Card[] array, Card target){
        for (int i = 0; i < array.length; i++){
            if (array[i].compareTo(target) == 0){
                return i;
            }
        }
        return -1;
    }

    //build a recursive binary search algorithm
    //returns the index of the target Card in the array
    //or -1 if the target is not in the array
    public static int binarySearch(Card[] array, Card target){
        return binarySearch(array, target, 0, array.length - 1);
    }

    public static int binarySearch(Card[] array, Card target, int low, int high){
        if (low > high){ //THIS RIGHT HERE IS THE BASE CARE
            return -1;
        }
        int mid = (low + high) / 2;
        if (array[mid].compareTo(target) == 0){ //SUCESS
            return mid;
        } 
        //choose which smaller problem to solve
        else if (array[mid].compareTo(target) < 0){
            return binarySearch(array, target, mid + 1, high);
        } else {
            return binarySearch(array, target, low, mid - 1);
        }
    }
}