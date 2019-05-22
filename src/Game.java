public class Game {

    /**
     * The main game, the Game Environment holds all the data as the user goes through the game.
     * It holds the crew members, space out post, space ship, medical supplies etc.
     */ 
    private GameEnvironment environment;
	
    /**
     * Game construtor - runs first screen which is the set up screen.
     */
    Game() {
        runSetUpScreen();
    }
    
    /**
     * Launch the game.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
    	new Game();
    }

    /**
     * Closes the set up screen and runs home screen.
     * @param screen The set up screen
     */
    public void closeSetUpScreen(SetUpScreen screen) {
    	screen.finishedWindow();
        runHomeScreen();
    }
 
    /**
     * Runs set up screen
     */
    private void runSetUpScreen() {
    	new SetUpScreen(this);
    }

    /**
     * Closes home screen and opens either victory or defeat screen
     * @param screen The home screen.
     * @param isVictory boolean.
     * @param response A string for the error message.
     */
    public void closeHomeScreen(HomeScreen screen, boolean isVictory, String response) {
    	screen.finishedWindow();
        if (isVictory) {
            runVictoryScreen();
        } else {
            runDefeatScreen(response);
        }
    }
    
    /**
     * Runs the home screen
     */
    private void runHomeScreen() {
    	new HomeScreen(this);
    }

    /**
     * Runs the victory screen.
     */
    private void runVictoryScreen() {
        new VictoryScreen(this);
    }

    /**
     * Runs the defeat screen.
     */
    private void runDefeatScreen(String response) {
        new DefeatScreen(this, response);
    }

    /**
     * Setter for game environment.
     * @param environment A game environment instance.
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Getter for game environment.
     * @return environment The game environment instance.
     */
    public GameEnvironment getGameEnvironment() {
        return environment;
    }
}
