
package texasPoker;

import java.util.Arrays;

/**
 *
 * @author Team.
 */
class GameLogic {
    public String identHnd(int[] hnd){
        
        int[] fourOrFH = new int[5];
        int fH = 0;
        int count = 10;
        boolean strt = true;
        boolean strtFlush = true;
        boolean threeKnd = false;
        
        for (int i = 0, j = 0; i < hnd.length - 1; i++) {
            if (!(hnd[i] == (hnd[i + 1] - 1)))
                strtFlush = false;

            if (!(hnd[i] % 13 == ((hnd[i + 1] % 13) - 1)))
                strt = false;

            if (hnd[i] % 13 == hnd[i + 1] % 13) {
                fH++;
                fourOrFH[j++] = hnd[i];
            } else
            count = i;
        } 

        if (strtFlush || (sameKnd(hnd) && strtAceTop(hnd))) {
            if (Royal_Flush(hnd)) {
                return "Royal Flush";
            }
            return "Striaght Flush";
        }
        else if (fH == 3) {
            if (count == 0 || count == 3)
                return "Four of a kind";
            else
                return "Full House";
        }
        else if (sameKnd(hnd))
            return "Flush";
        else if (strt || strtAceTop(hnd))
            return "strt";
        else {
            boolean co = false;
            int forTwo = 0;
            int ofSuit[] = new int[2];
            for (int i = 0, j = 0; i < hnd.length - 1; i++) {
                if (hnd[i] % 13 == hnd[i + 1] % 13) {
                if (co)
                    threeKnd = true;

                ofSuit[j++] = hnd[i];
                co = true;
                forTwo++;
                } 
                else
                    co = false;
            } 
            if (threeKnd)
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
    public boolean Royal_Flush(int[] hnd) {
        sortArr(hnd);
        int[] h = { 0, 9, 10, 11, 12 };
        int[] d = { 13, 22, 23, 24, 25 };
        int[] c = { 26, 35, 36, 37, 38 };
        int[] s = { 39, 48, 49, 50, 51 };
        //h:heard/ d:diamonds/c:clubs/s:speads.
        if (Arrays.equals(hnd, h) || Arrays.equals(hnd, d) || Arrays.equals(hnd, c) || Arrays.equals(hnd, s))
            return true;
        return false;

    }
        
        public boolean sameKnd(int[] hnd) {
        int cont;
        for (int i = 0; i < 4; i++) {
            cont = 0;
            for (int j = 0; j < hnd.length; j++) {
                if (hnd[j] / 13 == i)
                    cont++;
            } 
            if (cont == 5)
                return true;
        } 
        return false;
    }
        
        public boolean strtAceTop(int[] hnd) {

        int han[] = new int[5];

      

        for (int i = 0; i < hnd.length; i++) {
            if (hnd[i] % 13 == 0)
                han[i] = hnd[i] + 12;
            else
                han[i] = hnd[i] - 1;
        } 

        sortArr(han);

        for (int i = 0; i < han.length - 1; i++)
            if (han[i] % 13 != han[i + 1] % 13 - 1)
                return false;

        return true;
    }



  // this method for sorting the array hand base on suite.it's check the hand in all deck 52 cards.
  public int[] sortArr(int[] hnd) {
        int min = hnd[0] % 13;
        int index = 0;
        int temp;
        for (int i = 0; i < hnd.length; i++) {
            index = i;
            min = hnd[i] % 13;
            for (int x = i; x < hnd.length; x++) {
                if (hnd[x] % 13 < min) {
                    min = hnd[x] % 13;
                    index = x;
                }
            }
            temp = hnd[i];
            hnd[i] = hnd[index];
            hnd[index] = temp;

        } 
        return hnd;
    }

// this method for checking the 10 ways trying the best one first going to the lower rank.
public String[] helper(int[] twoCards, int[] fiveCards){

        int[] bestHnd = new int[5];
        String[] compare = new String[10];
        // one two three
        bestHnd[0] = twoCards[0];
        bestHnd[1] = twoCards[1];
        bestHnd[2] = fiveCards[0];
        bestHnd[3] = fiveCards[1];
        bestHnd[4] = fiveCards[2];
        sortArr(bestHnd);
        String compare1 = identHnd(bestHnd);
        compare[0] = compare1;

        // one two four
        bestHnd[0] = twoCards[0];
        bestHnd[1] = twoCards[1];
        bestHnd[2] = fiveCards[0];
        bestHnd[3] = fiveCards[1];
        bestHnd[4] = fiveCards[3];
        sortArr(bestHnd);
        String compare2 = identHnd(bestHnd);
        compare[1] = compare2;

        // one two five
        bestHnd[0] = twoCards[0];
        bestHnd[1] = twoCards[1];
        bestHnd[2] = fiveCards[0];
        bestHnd[3] = fiveCards[1];
        bestHnd[4] = fiveCards[4];
        sortArr(bestHnd);
        String compare3 = identHnd(bestHnd);
        compare[2] = compare3;

        // one three four
        bestHnd[0] = twoCards[0];
        bestHnd[1] = twoCards[1];
        bestHnd[2] = fiveCards[0];
        bestHnd[3] = fiveCards[2];
        bestHnd[4] = fiveCards[3];
        sortArr(bestHnd);
        String compare4 = identHnd(bestHnd);
        compare[3] = compare4;

        // one three five
        bestHnd[0] = twoCards[0];
        bestHnd[1] = twoCards[1];
        bestHnd[2] = fiveCards[0];
        bestHnd[3] = fiveCards[2];
        bestHnd[4] = fiveCards[4];
        sortArr(bestHnd);
        String compare5 = identHnd(bestHnd);
        compare[4] = compare5;

        // one, four, five
        bestHnd[0] = twoCards[0];
        bestHnd[1] = twoCards[1];
        bestHnd[2] = fiveCards[0];
        bestHnd[3] = fiveCards[3];
        bestHnd[4] = fiveCards[4];
        sortArr(bestHnd);
        String compare6 = identHnd(bestHnd);
        compare[5] = compare6;

        // two three four
        bestHnd[0] = twoCards[0];
        bestHnd[1] = twoCards[1];
        bestHnd[2] = fiveCards[1];
        bestHnd[3] = fiveCards[2];
        bestHnd[4] = fiveCards[3];
        sortArr(bestHnd);
        String compare7 = identHnd(bestHnd);
        compare[6] = compare7;

        // two three five
        bestHnd[0] = twoCards[0];
        bestHnd[1] = twoCards[1];
        bestHnd[2] = fiveCards[1];
        bestHnd[3] = fiveCards[2];
        bestHnd[4] = fiveCards[4];
        sortArr(bestHnd);
        String compare8 = identHnd(bestHnd);
        compare[7] = compare8;

        // two four five
        bestHnd[0] = twoCards[0];
        bestHnd[1] = twoCards[1];
        bestHnd[2] = fiveCards[1];
        bestHnd[3] = fiveCards[3];
        bestHnd[4] = fiveCards[4];
        sortArr(bestHnd);
        String compare9 = identHnd(bestHnd);
        compare[8] = compare9;

        // three four five
        bestHnd[0] = twoCards[0];
        bestHnd[1] = twoCards[1];
        bestHnd[2] = fiveCards[2];
        bestHnd[3] = fiveCards[3];
        bestHnd[4] = fiveCards[4];
        sortArr(bestHnd);
        String compare10 = identHnd(bestHnd);
        compare[9] = compare10;
	return compare;

}
/*
* this method for the best choise matching player two cards from five comunity cards.
*/
public int determineBestHand(int[] twoCards, int[] fiveCards){    
        
        String[] compare = new String[10];
        compare = helper(twoCards, fiveCards);

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
                      

            } else if (compare[i].equals("Stright")) {
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
        return 10;
    }
     
}
