/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package texasPoker;

import java.util.Arrays;

/**
 *
 * @author Team.
 */
class GameLogic {
    public String identifyHand(int[] hand){
        
        int[] fourOrFullHouse = new int[5];
        int fullHouse = 0;
        int count = 10;
        boolean straight = true;
        boolean straightFlush = true;
        boolean threeOfKind = false;
        
        for (int i = 0, j = 0; i < hand.length - 1; i++) {
            if (!(hand[i] == (hand[i + 1] - 1)))
                straightFlush = false;

            if (!(hand[i] % 13 == ((hand[i + 1] % 13) - 1)))
                straight = false;

            if (hand[i] % 13 == hand[i + 1] % 13) {
                fullHouse++;
                fourOrFullHouse[j++] = hand[i];
            } else
            count = i;
        } 

        if (straightFlush || (isSameKind(hand) && isItStraigthAceTop(hand))) {
            if (Royal_Flush(hand)) {
                return "Royal Flush";
            }
            return "Striaght Flush";
        }
        else if (fullHouse == 3) {
            if (count == 0 || count == 3)
                return "Four of a kind";
            else
                return "Full House";
        }
        else if (isSameKind(hand))
            return "Flush";
        else if (straight || isItStraigthAceTop(hand))
            return "Straight";
        else {
            boolean co = false;
            int forTwo = 0;
            int ofSuit[] = new int[2];
            for (int i = 0, j = 0; i < hand.length - 1; i++) {
                if (hand[i] % 13 == hand[i + 1] % 13) {
                if (co)
                    threeOfKind = true;

                ofSuit[j++] = hand[i];
                co = true;
                forTwo++;
                } 
                else
                    co = false;
            } 
            if (threeOfKind)
                return "Three of kind";
            else if (forTwo == 2)
                return "Two Pairs";
            else if (forTwo == 1)
                return "One Pair";
            else
                return "High Card";

        }

    }
    //All four possable ways for Royal flush.
    public boolean Royal_Flush(int[] hand) {
        sortArr(hand);
        int[] h = { 0, 9, 10, 11, 12 };
        int[] d = { 13, 22, 23, 24, 25 };
        int[] c = { 26, 35, 36, 37, 38 };
        int[] s = { 39, 48, 49, 50, 51 };
        //h:heard/ d:diamonds/c:clubs/s:speads.
        if (Arrays.equals(hand, h) || Arrays.equals(hand, d) || Arrays.equals(hand, c) || Arrays.equals(hand, s))
            return true;
        return false;

    }
        
        public boolean isSameKind(int[] hand) {
        int cont;
        for (int i = 0; i < 4; i++) {
            cont = 0;
            for (int j = 0; j < hand.length; j++) {
                if (hand[j] / 13 == i)
                    cont++;
            } 
            if (cont == 5)
                return true;
        } 
        return false;
    }
        
        public boolean isItStraigthAceTop(int[] hand) {

        int han[] = new int[5];

      

        for (int i = 0; i < hand.length; i++) {
            if (hand[i] % 13 == 0)
                han[i] = hand[i] + 12;
            else
                han[i] = hand[i] - 1;
        } 

        sortArr(han);

        for (int i = 0; i < han.length - 1; i++)
            if (han[i] % 13 != han[i + 1] % 13 - 1)
                return false;

        return true;
    }



  // this method for sorting the array hand base on suite.it's check the hand in all deck 52 cards.
  public int[] sortArr(int[] hand) {
        int min = hand[0] % 13;
        int index = 0;
        int temp;
        for (int i = 0; i < hand.length; i++) {
            index = i;
            min = hand[i] % 13;
            for (int x = i; x < hand.length; x++) {
                if (hand[x] % 13 < min) {
                    min = hand[x] % 13;
                    index = x;
                }
            }
            temp = hand[i];
            hand[i] = hand[index];
            hand[index] = temp;

        } 
        return hand;
    }

// this method for checking the 10 ways trying the best one first going to the lower rank.
public String[] helper(int[] towCards, int[] fiveCards){

        int[] bestHand = new int[5];
        String[] compare = new String[10];
        // one two three
        bestHand[0] = towCards[0];
        bestHand[1] = towCards[1];
        bestHand[2] = fiveCards[0];
        bestHand[3] = fiveCards[1];
        bestHand[4] = fiveCards[2];
        sortArr(bestHand);
        String compare1 = identifyHand(bestHand);
        compare[0] = compare1;

        // one two four
        bestHand[0] = towCards[0];
        bestHand[1] = towCards[1];
        bestHand[2] = fiveCards[0];
        bestHand[3] = fiveCards[1];
        bestHand[4] = fiveCards[3];
        sortArr(bestHand);
        String compare2 = identifyHand(bestHand);
        compare[1] = compare2;

        // one two five
        bestHand[0] = towCards[0];
        bestHand[1] = towCards[1];
        bestHand[2] = fiveCards[0];
        bestHand[3] = fiveCards[1];
        bestHand[4] = fiveCards[4];
        sortArr(bestHand);
        String compare3 = identifyHand(bestHand);
        compare[2] = compare3;

        // one three four
        bestHand[0] = towCards[0];
        bestHand[1] = towCards[1];
        bestHand[2] = fiveCards[0];
        bestHand[3] = fiveCards[2];
        bestHand[4] = fiveCards[3];
        sortArr(bestHand);
        String compare4 = identifyHand(bestHand);
        compare[3] = compare4;

        // one three five
        bestHand[0] = towCards[0];
        bestHand[1] = towCards[1];
        bestHand[2] = fiveCards[0];
        bestHand[3] = fiveCards[2];
        bestHand[4] = fiveCards[4];
        sortArr(bestHand);
        String compare5 = identifyHand(bestHand);
        compare[4] = compare5;

        // 145
        bestHand[0] = towCards[0];
        bestHand[1] = towCards[1];
        bestHand[2] = fiveCards[0];
        bestHand[3] = fiveCards[3];
        bestHand[4] = fiveCards[4];
        sortArr(bestHand);
        String compare6 = identifyHand(bestHand);
        compare[5] = compare6;

        // tow three four
        bestHand[0] = towCards[0];
        bestHand[1] = towCards[1];
        bestHand[2] = fiveCards[1];
        bestHand[3] = fiveCards[2];
        bestHand[4] = fiveCards[3];
        sortArr(bestHand);
        String compare7 = identifyHand(bestHand);
        compare[6] = compare7;

        // two three five
        bestHand[0] = towCards[0];
        bestHand[1] = towCards[1];
        bestHand[2] = fiveCards[1];
        bestHand[3] = fiveCards[2];
        bestHand[4] = fiveCards[4];
        sortArr(bestHand);
        String compare8 = identifyHand(bestHand);
        compare[7] = compare8;

        // tow four five
        bestHand[0] = towCards[0];
        bestHand[1] = towCards[1];
        bestHand[2] = fiveCards[1];
        bestHand[3] = fiveCards[3];
        bestHand[4] = fiveCards[4];
        sortArr(bestHand);
        String compare9 = identifyHand(bestHand);
        compare[8] = compare9;

        // three four five
        bestHand[0] = towCards[0];
        bestHand[1] = towCards[1];
        bestHand[2] = fiveCards[2];
        bestHand[3] = fiveCards[3];
        bestHand[4] = fiveCards[4];
        sortArr(bestHand);
        String compare10 = identifyHand(bestHand);
        compare[9] = compare10;
	return compare;

}
/*
* this method for the best choise matching player two cards from five comunity cards.
*/
public int determineBestHand(int[] towCards, int[] fiveCards){    
        
        String[] compare = new String[10];
        compare = helper(towCards, fiveCards);

        int counter = 10;
        for (int i = 0; i < 10; i++) {

            if (compare[i].equals("Royal Flush")) {
                
                counter = 1;
                return 1;
            } else if (compare[i].equals("Striaght Flush")) {
                   if(counter>2){
                         counter = 2;
                   }

            } else if (compare[i].equals("Four of a kind")) {
                if(counter>3){
                     counter = 3;
                }
                        

            } else if (compare[i].equals("Full House")) {
                if(counter>4){
                     counter = 4;
                }
                   

            }

            else if (compare[i].equals("Flush")) {
                if(counter>5){
                     counter = 5;
                }
                      

            } else if (compare[i].equals("Straight")) {
                   if(counter>6){
                        counter = 6;
                   }

            } else if (compare[i].equals("Three of kind")) {
                if(counter>7){
                     counter = 7;
                }
                        

            } else if (compare[i].equals("Two Pairs")) {
                   if(counter>8){
                        counter = 8;
                   }

            } else if (compare[i].equals("One Pair")) {
                if(counter>9){
                    counter = 9;
                }
                       

            } else {
                if(counter == 10)
                    counter =  10;
            }
        }
        return 10;
    }
     
}
