public class Game {
	private GameEnvironment environment;
	
    Game() {
        runSetUpScreen();
    }
    
    public static void main(String[] args) {
    	new Game();
    }

    public void setGameEnvironment(GameEnvironment environment) {
    	this.environment = environment;
    }
    
    public GameEnvironment getGameEnvironment() {
    	return environment;
    }
    
    public void closeSetUpScreen(SetUpScreen screen) {
    	screen.finishedWindow();
        runHomeScreen();
    }
 
    private void runSetUpScreen() {
    	new SetUpScreen(this);
    }

    public void closeHomeScreen(HomeScreen screen, boolean isVictory, String response) {
    	screen.finishedWindow();
        if (isVictory) {
            runVictoryScreen();
        } else {
            runDefeatScreen(response);
        }
    }
    
    private void runHomeScreen() {
    	new HomeScreen(this);
    }

    private void runVictoryScreen() {
        new VictoryScreen(this);
    }

    private void runDefeatScreen(String response) {
        new DefeatScreen(this, response);
    }
}
