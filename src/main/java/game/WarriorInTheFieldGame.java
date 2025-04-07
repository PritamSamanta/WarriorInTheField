package main.java.game;

import main.java.game.model.GameModel;
import main.java.game.view.GameView;
import main.java.game.controller.GameController;

public class WarriorInTheFieldGame {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView();
        GameController controller = new GameController(model, view);

        controller.startGame();
    }
}