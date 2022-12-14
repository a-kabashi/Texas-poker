
package texasPoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Team.
 */
public class Dealer extends GameLogic{
    private ArrayList<Integer> deck;
    private int[] communityCards;
    private int[] playerCards;
    private int[] player2Cards;
    
    
    Dealer(){
        deck = new ArrayList<>();
        communityCards = new int[5];
        playerCards = new int[2];
        player2Cards = new int[2];
        
        for(int i=0; i <= 51; i++) deck.add(i);
    }
            
    
   
    public ArrayList<Integer> getDeck(){
        return this.deck;
    }
   
    public int getCard(int cardNumber){
        return this.deck.get(cardNumber);
    }
    
    /**
     *
     * @return
     */
    public int[] getCommunityCards(){
        return communityCards;
    }
    
    /**
     *
     * @return
     */
    public int[] getPlayerCards(){
        return playerCards;
    }
    
    /**
     *
     * @return
     */
    public int[] getPlayer2Cards(){
        return player2Cards;
    }
    
    /**
     *
     */
    public void shuffleDeck(){
        Collections.shuffle(this.deck);
    }
    
    /**
     *
     */
    public void dealCards(){
        for(int i = 0; i <= 4; i++) communityCards[i] = deck.get(i);
        for(int i = 0; i <= 1; i++) playerCards[i] = deck.get(i+5);
        for(int i = 0; i <= 1; i++) player2Cards[i] = deck.get(i+7);
    }
    
    /* it takes the two cards of the player and the five community cards,
    * and returns integer count according to the best five cards.
    */
    public int DetermineBestHand(int[] towCards, int[] fiveCards){    
        String[] compare = new String[10];
        compare = helper(towCards, fiveCards);

        int count = 10;
        for (int i = 0; i < 10; i++) {

            if (compare[i].equals("Royal Flush")) {
                
                count = 1;
                return 1;
            } else if (compare[i].equals("Striaght Flush")) {
                   if(count>2){
                         count = 2;
                   }

            } else if (compare[i].equals("Four of a kind")) {
                if(count>3){
                     count = 3;
                }
                        

            } else if (compare[i].equals("Full House")) {
                if(count>4){
                     count = 4;
                }
                   

            }

            else if (compare[i].equals("Flush")) {
                if(count>5){
                     count = 5;
                }
                      

            } else if (compare[i].equals("Straight")) {
                   if(count>6){
                        count = 6;
                   }

            } else if (compare[i].equals("Three of kind")) {
                if(count>7){
                     count = 7;
                }
                        

            } else if (compare[i].equals("Two Pairs")) {
                   if(count>8){
                        count = 8;
                   }

            } else if (compare[i].equals("One Pair")) {
                if(count>9){
                    count = 9;
                }
                       

            } else {
                if(count == 10)
                    count =  10;
            }
        }
        return count;
    }
    
    
    /*
    *isSameKind method it takes an array of integer and return boolean, its deteraom
    *if its the same kind or not.
    */
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

    /*
    * idenHnd takes an array of intege representing the Hand, then determine the
    * kind of hand and return it, as String.
    */
    public String idenHnd(int[] hand) {
        
        boolean isThreeKnd = false;
        boolean isStrt = true;
        boolean isStrtFlush = true;
        int isFH = 0;
        int[] fourOrFH = new int[5];
        int count = 10;
        
        for (int i = 0, j = 0; i < hand.length - 1; i++) {
            if (!(hand[i] == (hand[i + 1] - 1)))
                isStrtFlush = false;

            if (!(hand[i] % 13 == ((hand[i + 1] % 13) - 1)))
                isStrt = false;

            if (hand[i] % 13 == hand[i + 1] % 13) {
                isFH++;
                fourOrFH[j++] = hand[i];

            } else
            count = i;
        } 

        if (isStrtFlush || (isSameKind(hand) && strtAceTop(hand))) {
            if (Royal_Flush(hand)) {
                return "Royal Flush";
            }
            return "Striaght Flush";
        } // end of if
        else if (isFH == 3) {
            if (count == 0 || count == 3)
                return "Four of a kind";
            else
                return "Full House";
        } // end of if
        else if (isSameKind(hand))
            return "Flush";
        else if (isStrt || strtAceTop(hand))
            return "Straight";
        else {
            boolean co = false;
            int forTwo = 0;
            int ofSuit[] = new int[2];
            for (int i = 0, j = 0; i < hand.length - 1; i++) {
                if (hand[i] % 13 == hand[i + 1] % 13) {
                if (co)
                    isThreeKnd = true;

                ofSuit[j++] = hand[i];
                co = true;
                forTwo++;
                } // end of if
                else
                    co = false;
            } // end of for loop
            if (isThreeKnd)
                return "Three of kind";
            else if (forTwo == 2)
                return "Two Pairs";
            else if (forTwo == 1)
                return "One Pair";
            else
                return "High Card";

        } // end of else

    }

    //Royal_Flush method it takes an array of hand (5 integers) and it return boolean.

    public boolean Royal_Flush(int[] hand) {
        sortArr(hand);// sorting the array hand.
        // all posable cases for Royal hands.
        int[] h = { 0, 9, 10, 11, 12 };
        int[] d = { 13, 22, 23, 24, 25 };
        int[] c = { 26, 35, 36, 37, 38 };
        int[] s = { 39, 48, 49, 50, 51 };

        if (Arrays.equals(hand, h) || Arrays.equals(hand, d) || Arrays.equals(hand, c) || Arrays.equals(hand, s))
            return true;
        return false;

    }

    /*
    * strtAceTop method it takes an array of integer "hand", and checks if its Straight, 
    * when the Ace on Top, and return boolean type regard to that.
    */
    public boolean strtAceTop(int[] hand) {

        int han[] = new int[5];

        // for loop copy hand into new array, whenever find Ace card, change its position
        // to the king position, else will push all the card one number down

        for (int i = 0; i < hand.length; i++) {
            if (hand[i] % 13 == 0)
                han[i] = hand[i] + 12;
            else
                han[i] = hand[i] - 1;
        } // end of for loop

        sortArr(han);

        // then try to determine is the new array is straight or not
        for (int i = 0; i < han.length - 1; i++)
            if (han[i] % 13 != han[i + 1] % 13 - 1)
                return false;

        return true;
    }
    
    /*
    *identifyHouselast method it takes an integer count and returns string of the type of the hand.
    */
    public String identifyHouselast(int count){
        
            if (count == 1)
                return "Royal Flush";
            else if (count == 2)
                return "Striaght Flush";
            else if (count == 3)
                return "Four of a kind";
            else if (count == 4)
                return "Full House";
            else if (count == 5)
                return "Flush";
            else if (count == 6)
                return "Straight";
            else if (count == 7)
                return "Three of kind";
            else if (count == 8)
                return "Two Pairs";
            else if (count == 9)
                return "One Pair";
            else  
                return "High Card";
       
   
   
    }
    
 
    public String identifyWinner(int p1Hnd, int p2Hnd){
        if(p1Hnd < p2Hnd){
            return "Player1";
        }
        if(p1Hnd > p2Hnd){
            return  "player2";
        }
        return "Draw";
    
    }
    
}

