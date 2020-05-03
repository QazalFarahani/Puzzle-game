import utils.config.ConfigLoader;

public class Main {

    public static void main(String[] args) {
        ConfigLoader urls = ConfigLoader.getInstance("default");
        Game game = new Game();
        game.startGame();
    }
}
