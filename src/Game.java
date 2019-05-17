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

    //public void closeHomeScreen(HomeScreen screen) {
    //	screen.finishedWindow();
    //}
    
    private void runHomeScreen() {
    	new HomeScreen(this);
    }
}
