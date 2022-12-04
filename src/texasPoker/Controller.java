
package texasPoker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 *
 * @author Team 
 */
public class Controller {
    
    StartPage startPage;
    StarterGamePage starterGamePage;
    Settings_Interface2 st;
    GamePage gamePage;
    Dealer dealer;
    Player player;
    Player2 computer;
    
    int stage = 0;
    
    public Controller(StartPage startPage, GamePage gamePage, Dealer dealer, Player player, Player2 computer, StarterGamePage starterGamePage, Settings_Interface2 st){
        this.startPage = startPage;
        this.dealer = dealer;
        this.gamePage = gamePage;
        this.player = player;
        this.computer = computer;
        this.starterGamePage = starterGamePage;
        this.st = st;
        
       
        this.startPage.addjButtonStartGame(new jButtonStartGameListener());
        this.gamePage.addjButtonNewRound(new jButtonNewRoundListener());
        this.gamePage.addjButtonRaise(new jButtonRaiseListener());
        this.gamePage.addjButtonCall(new jButtonCallListener());
        this.gamePage.addjButtonFold(new jButtonFoldListener());
    }
    
    //prints out the the winner or draw according to the return from identifyWinner method.
    private void displayWinner() {
        int pRank = this.dealer.DetermineBestHand(this.dealer.getPlayerCards(), this.dealer.getCommunityCards());
        int compRank = this.dealer.DetermineBestHand(this.dealer.getPlayer2Cards(), this.dealer.getCommunityCards());
        
        String winner = this.dealer.identifyWinner(pRank, compRank);
        
        switch(winner){
            case "Player":
               this.gamePage.setDisplayWinner("The Winner is Player!");
               this.hndBet("C->P"); 
               break;
            case "Computer":
               this.gamePage.setDisplayWinner("The Winner is Computer!");
               this.hndBet("P->C"); 
               break;
            case "Draw":
               this.gamePage.setDisplayWinner("Tide!!");
               break;
            default:
                break;
        }
        
        this.gamePage.setPlayer2BestHand(this.dealer.identifyHouselast(compRank));
        this.gamePage.setPlayerBestHand(this.dealer.identifyHouselast(pRank));
    }
    
    private void displayCompWin() {
        this.gamePage.setDisplayWinner("The Player2 Won!");
        
        this.gamePage.setPlayer2BestHand("Winner!");
        this.gamePage.setPlayerBestHand("FOLD!");

        this.hndBet("P->C"); 
    }

    private void displayPWin() {
        this.gamePage.setDisplayWinner("The Player1 Won!");
        
        this.gamePage.setPlayer2BestHand("FOLD!");
        this.gamePage.setPlayerBestHand("Winner!");

        this.hndBet("C->P"); 
    }
    // draws the back card picture for all cards on the table.
    private void hideCards() {
        this.gamePage.setPlayerCard1(52);
        this.gamePage.setPlayerCard2(52);
        
        this.gamePage.setComputerCard1(52);
        this.gamePage.setComputerCard2(52);
        
        this.gamePage.setCommunityCard1(52);
        this.gamePage.setCommunityCard2(52);
        this.gamePage.setCommunityCard3(52);
        this.gamePage.setCommunityCard4(52);
        this.gamePage.setCommunityCard5(52);
           
    }
    
    // computer game logic
    private void compPlays() {
        Random rand = new Random();
        
        this.computer.setDifficultyLevel(this.gamePage.getDifficultyLevel());
        
        int decision = this.computer.decide();
        
        switch(this.computer.getDifficultyLevel()){
            case "Easy":
                if(0 <= decision && decision <= 50){ 
                    this.gamePage.setcomputerDecision("Raise!");

                    this.computer.raiseBet(rand.nextInt(100));
                    this.gamePage.setComputerBet(Integer.toString(this.computer.getBet()));
                } else if(50 < decision && decision <= 45){ 
                        this.gamePage.setcomputerDecision("Call!");
                } else { 
                        this.gamePage.setcomputerDecision("Fold!");
                        this.displayPWin();
                }   
                break;
            case "Medium":
                if(0 <= decision && decision <= 50){ 
                    this.gamePage.setcomputerDecision("Raise!");

                    this.computer.raiseBet(rand.nextInt(100));
                    this.gamePage.setComputerBet(Integer.toString(this.computer.getBet()));
                } else if(50 < decision && decision <= 85){ 
                        this.gamePage.setcomputerDecision("Call!");
                } else { //
                        this.gamePage.setcomputerDecision("Fold!");
                        this.displayPWin();
                }   
                break;
            case "Hard":
                if(0 <= decision && decision <= 75){ 
                    this.gamePage.setcomputerDecision("Raise!");

                    this.computer.raiseBet(rand.nextInt(100));
                    this.gamePage.setComputerBet(Integer.toString(this.computer.getBet()));
                } else if(75 < decision && decision <= 95){ 
                        this.gamePage.setcomputerDecision("Call!");
                } else { //Folds
                        this.gamePage.setcomputerDecision("Fold!");
                        this.displayPWin();
                }   
                break;
            default:
                break;
        }
            
    }
    
    private void hndBet(String dec) {
        if (dec.equals("P->C")){
            this.computer.raiseBet(this.player.getBet());
            this.player.setBet(0);
        } else {
            this.player.raiseBet(this.computer.getBet());
            this.computer.setBet(0);
        }
        
        this.gamePage.setComputerBet(Integer.toString(this.computer.getBet()));
        this.gamePage.setPlayerBet(Integer.toString(this.player.getBet()));
    }
    
    
    private class jButtonCallListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            pCall();
        }
    }
    
    private class jButtonFoldListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            pFold();
        }
    }
    
    private class jButtonRaiseListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            pRaise(gamePage.getPlayerBet());
        }
    }
    private class jButtonStarterGameListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            startGame();
        }
    }
    
    private class jButtonStartGameListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            startGame();
        }
    }
    
    private class jButtonNewRoundListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            newRound();
        }
    }
    
    public void displayPCards(){
        this.gamePage.setPlayerCard1(this.dealer.getPlayerCards()[0]);
        this.gamePage.setPlayerCard2(this.dealer.getPlayerCards()[1]);
    }
    
    public void dispThreeCards(){
            this.gamePage.setCommunityCard1(this.dealer.getCommunityCards()[0]);
            this.gamePage.setCommunityCard2(this.dealer.getCommunityCards()[1]);
            this.gamePage.setCommunityCard3(this.dealer.getCommunityCards()[2]);
    }
    
    public void dispP2Cards(){
        this.gamePage.setComputerCard1(this.dealer.getPlayer2Cards()[0]);
        this.gamePage.setComputerCard2(this.dealer.getPlayer2Cards()[1]);
    }
    
    public void dispFourthCards(){
        this.gamePage.setCommunityCard1(this.dealer.getCommunityCards()[0]);
            this.gamePage.setCommunityCard2(this.dealer.getCommunityCards()[1]);
            this.gamePage.setCommunityCard3(this.dealer.getCommunityCards()[2]);
        this.gamePage.setCommunityCard4(this.dealer.getCommunityCards()[3]);
    }
    
    public void dispFifthCard(){
        this.gamePage.setCommunityCard1(this.dealer.getCommunityCards()[0]);
            this.gamePage.setCommunityCard2(this.dealer.getCommunityCards()[1]);
            this.gamePage.setCommunityCard3(this.dealer.getCommunityCards()[2]);
        this.gamePage.setCommunityCard4(this.dealer.getCommunityCards()[3]);
               this.gamePage.setCommunityCard5(this.dealer.getCommunityCards()[4]);

    }
    // reset the game logic and game page.
    public void newRound(){
        this.stage = 0;
        
        this.dealer.shuffleDeck();
        this.dealer.dealCards();
        
        hideCards();
        
        displayPCards();        
    }
    
    // player raise bet logic.
    public void pRaise(int bet){

        switch (this.stage){
            case 0:
                this.player.raiseBet(bet);
                this.gamePage.setPlayerBet(Integer.toString(this.player.getBet()));
                
                this.compPlays();
      
                
                this.dispThreeCards();
                this.stage++;
                break;
            case 1:
                this.player.raiseBet(bet);
                this.gamePage.setPlayerBet(Integer.toString(this.player.getBet()));
                
                this.compPlays();
                
                this.dispFourthCards();
                this.stage++;
                break;
            case 2:
                this.player.raiseBet(bet);
                this.gamePage.setPlayerBet(Integer.toString(this.player.getBet()));
                
                this.compPlays();

                this.dispFifthCard();
                this.stage++;
                break;
            case 3:
                this.dispP2Cards();
                this.displayWinner();
                break;
            default:
                this.displayWinner();
                break;
        }
    }
    public void pFold(){
        this.displayCompWin();
    }
    
    // player call logic
    public void pCall(){
        switch (this.stage){
            case 0:
                this.dispThreeCards();
                this.compPlays();

                this.stage++;
                break;
            case 1:
                this.dispFourthCards();
                this.compPlays();
                this.stage++;
                break;
            case 2:
                this.dispFifthCard();
                this.compPlays();
                this.stage++;
                break;
            case 3:
                this.displayWinner();
                this.dispP2Cards();
                break;
            default:
                this.displayWinner();
                break;
        }
    }
    
    public void startGame(){
        
        this.player.setName(this.startPage.getPlayerName());
        this.gamePage.setVisible(true);
        this.startPage.dispose();
        this.gamePage.setPlayerName(this.player.getName());
    }
    
    public void launchGame(){
        this.startPage.setVisible(true);
    }
   
}
