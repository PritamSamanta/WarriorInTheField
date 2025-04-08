package main.java.game;

import main.java.game.model.GameModel;
import main.java.game.view.GameView;
import main.java.game.controller.GameController;

public class WarriorInTheFieldGame {
    public static void main(String[] args) {
        initializeAndStartGame();
    }

    private static void initializeAndStartGame(){
        GameModel model = new GameModel();
        GameView view = new GameView();
        GameController controller = new GameController(model, view);

        controller.startGame();
    }
}