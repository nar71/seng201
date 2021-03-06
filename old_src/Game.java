public class Game {
	private GameEnvironment environment;
	
    Game() {
    	//runStartScreen();
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
    
    public void closeStartScreen(StartScreen screen) {
    	screen.finishedWindow();
    	runSetUpScreen();
    }
    
    private void runStartScreen() {
        new StartScreen(this);
    }
    
    public void closeSetUpScreen(SetUpScreen screen) {
    	screen.finishedWindow();
    	//runChooseCharacterScreen();
        runHomeScreen();
    }
 
    private void runSetUpScreen() {
    	new SetUpScreen(this);
    }
    
    public void closeChooseCharacterScreen(ChooseCharacterScreen screen) {
    	screen.finishedWindow();
    	runHomeScreen();
    }
    
    private void runChooseCharacterScreen() {
    	new ChooseCharacterScreen(this);
    }
    
    public void closeHomeScreen(HomeScreen screen) {
    	screen.finishedWindow();
    	runCrewDetailsScreen();
    }
    
    private void runHomeScreen() {
    	new HomeScreenPanel(this);
    }
    
    public void runCrewDetailsScreen() {
    	new CrewDetailsScreen(this);
    }
    
    public void closeCrewDetailsScreen(CrewDetailsScreen screen) {
    	screen.finishedWindow();
    }
}
