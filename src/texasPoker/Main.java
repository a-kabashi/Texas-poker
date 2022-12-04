package texasPoker;

/**
 *
 * @author Team (Farzana,Esraa,Joy,Abubakar)
 */
public class Main {
    
    public static void main(String[] args) {
        
        StartPage startPage = new StartPage();
        GamePage gamePage = new GamePage();
        Dealer dealer = new Dealer();
        Player player = new Player();
        Player2 computer = new Player2();
        StarterGamePage starterGamePage = new StarterGamePage();
        Settings_Interface2 st = new Settings_Interface2();
        
        Controller controller = new Controller(startPage, gamePage, dealer, player, computer, starterGamePage, st);
        
        controller.launchGame();
    }
}