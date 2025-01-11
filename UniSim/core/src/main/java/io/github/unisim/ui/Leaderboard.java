package io.github.unisim.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import io.github.unisim.Event;
import io.github.unisim.GameState;
import io.github.unisim.ScoreManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Leaderboard {
    private Stage stage;
    private Table table;
    private ShapeActor leaderboard = new ShapeActor(Colors.get("BLACK"));
    private Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
    private Label leaderboardLabel = new Label("Leaderboard \n", skin);
    private String[] playerNames = {"Jerry", "Daniel", "Colin", "Andrei", "Nigel"};
    private String[] playerScores = {"95", "76", "62", "51", "10"};;
    private Array<Label> playerLabels = new Array<>();
    private TextField textField;
    private int EndGameScore;
    private String name;
    private TextButton submitButton;
    private ScoreManager manager;


    /**
     * Constructor for leaderboard class, this initialises the stage and table in which the
     *  leaderboard is built upon.
     *  Then creates the board calling createBoard()
     *
     * @param stage The game over stage that is used at the end of the game
     * @param scoreManager  ScoreManager passed so leaderboard can access its functions
     */
    public Leaderboard(Stage stage, ScoreManager scoreManager) {
        manager = scoreManager;
        this.stage = stage;
        this.table = new Table();
        createBoard();

    }

    /**
     * Update the bounds of the background & table actors to fit the new size of the screen.
     *
     * @param width  - The new width of the screen in pixels.
     * @param height - The enw height of the screen in pixels.
     */
    public void resize(int width, int height) {
        leaderboard.setBounds(width * 0.35f, height * 0.4f, width * 0.3f, height * 0.3f);
        if (table != null) {
            table.setBounds(width * 0.35f, height * 0.4f, width * 0.3f, height * 0.3f);
            leaderboardLabel.setFontScale(height * 0.0050f);
            for (Label playerLabel : playerLabels) {
                playerLabel.setFontScale(height * 0.0030f);
            }

        }

    }

    /**
     * Initialises and defines EndGameScore using ScoreManager
     * Iterates through the playerScores to see if the user has a score high enough to get on leaderboard
     *  If so it calls getName()
     *
     */
    public void endGame(){
        reset();
        EndGameScore = manager.getScore();
        for (String score : playerScores) {
            int scoreInt = Integer.parseInt(score);
            if (scoreInt < EndGameScore) {
                getName();
                break;
            }
        }
    }

    /**
     * This creates the table in which the leaderboard is built upon.
     * Iterating through playerNames and playerScores and adding them one by to table
     */
    public void createBoard() {

        table.add(leaderboardLabel).align(Align.center);
        table.row().pad(10, 0, 10, 0);

        playerLabels.clear();

        for (int i = 0; i < playerNames.length; i++) {
            String playerData = playerNames[i] + " - " + playerScores[i];
            Label playerLabel = new Label(playerData, skin);

            // Add the player label to the list
            playerLabels.add(playerLabel);

            // Add the player label to the table
            table.add(playerLabel).align(Align.center);
            table.row().pad(10, 0, 10, 0);
        }


        stage.addActor(table);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    /**
     * This updates the board if the user name and score is added
     */
    public void updateBoard(){
        for (int i = 0; i < playerNames.length; i++) {
            String playerData = playerNames[i] + " - " + playerScores[i];
            playerLabels.get(i).setText(playerData);

        }
    }

    /**
     * Updates the PlayerScores and PlayerNames arrays to insert the new score and name
     */
    public void updateScores() {
        int insertIndex = -1;
        for (int i = 0; i < playerScores.length; i++) {
            if (EndGameScore > Integer.parseInt(playerScores[i])) {
                insertIndex = i;
                break;
            }
        }
        if (insertIndex != -1) {
            String[] newPlayerScores = new String[playerScores.length + 1];
            String[] newPlayerNames = new String[playerNames.length + 1];
            System.arraycopy(playerScores, 0, newPlayerScores, 0, insertIndex);
            System.arraycopy(playerNames, 0, newPlayerNames, 0, insertIndex);
            newPlayerScores[insertIndex] = String.valueOf(EndGameScore);
            newPlayerNames[insertIndex] = name;
            System.arraycopy(playerScores, insertIndex, newPlayerScores, insertIndex + 1, playerScores.length - insertIndex);
            System.arraycopy(playerNames, insertIndex, newPlayerNames, insertIndex + 1, playerNames.length - insertIndex);
            playerScores = newPlayerScores;
            playerNames = newPlayerNames;
        } else {
            String[] newPlayerScores = new String[playerScores.length + 1];
            String[] newPlayerNames = new String[playerNames.length + 1];
            System.arraycopy(playerScores, 0, newPlayerScores, 0, playerScores.length);
            System.arraycopy(playerNames, 0, newPlayerNames, 0, playerNames.length);
            newPlayerScores[playerScores.length] = String.valueOf(EndGameScore);
            newPlayerNames[playerNames.length] = name;
            playerScores = newPlayerScores;
            playerNames = newPlayerNames;
        }

        if (playerScores.length > 5) {
            String[] trimmedPlayerScores = new String[5];
            String[] trimmedPlayerNames = new String[5];

            System.arraycopy(playerScores, 0, trimmedPlayerScores, 0, 5);
            System.arraycopy(playerNames, 0, trimmedPlayerNames, 0, 5);

            playerScores = trimmedPlayerScores;
            playerNames = trimmedPlayerNames;
        }

        updateBoard();
    }


    /**
     * Adds additional UI elements to get the users name in order to add to the board.
     */
    public void getName(){

        Label info = new Label("You scored " + EndGameScore, skin);
        Label inputName = new Label("Enter name to be added to leaderboard: ", skin);
        table.add(info).align(Align.center);
        table.row();
        table.add(inputName).align(Align.center);

        textField = new TextField("",skin);
        textField.setMessageText("Enter here...");

        table.row().pad(10);
        table.add(textField).align(Align.center);

        submitButton = new TextButton("Submit", skin);

        submitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                name = textField.getText();
                textField.setText("");
                updateScores();
            }
        });

        table.add(submitButton).align(Align.center);
            }

        public void reset(){
            table.clearChildren();
            createBoard();
        }
}


