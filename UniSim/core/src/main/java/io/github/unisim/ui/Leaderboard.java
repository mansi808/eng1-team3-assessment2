package io.github.unisim.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Leaderboard {
    private Stage stage;
    private Table table;
    private ShapeActor leaderboard = new ShapeActor(Colors.get("BLACK"));
    private Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
    private Label leaderboardLabel = new Label("Leaderboard \n", skin);
    private Array<String> playerNames = new Array<>();
    private Array<String> playerScores = new Array<>();
    private Array<Label> playerLabels = new Array<>();
    private TextField textField;
    private int EndGameScore;
    private String name;
    private TextButton submitButton;
    private ScoreManager manager;


    public Leaderboard(Stage stage, ScoreManager scoreManager) {
        manager = scoreManager;
        this.stage = stage;
        this.table = null;
        readFile();
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

    public void endGame(){
        EndGameScore = manager.getScore();
        for (String score : playerScores) {
            int scoreInt = Integer.parseInt(score);
            if (scoreInt < EndGameScore) {
                getName();
                break;
            }
        }
    }

    public void createBoard() {
        this.table = new Table();


        table.add(leaderboardLabel).align(Align.center);
        table.row().pad(10, 0, 10, 0);

        playerLabels.clear();

        for (int i = 0; i < playerNames.size; i++) {
            String playerData = playerNames.get(i) + " - " + playerScores.get(i);
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


    public void readFile() {
        try (Scanner reader = new Scanner(new File("leaderboard.txt"))) {
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(",");
                playerNames.add(parts[0]);
                playerScores.add(parts[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void updateFile() {
        try {
            FileWriter writer = new FileWriter("leaderboard.txt");
            for (int i = 0; i < playerNames.size; i++) {
                String playerData = playerNames.get(i) +","+playerScores.get(i)+"\n";
                writer.write(playerData);
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred while writing to file");
        }
    }

    public void updateBoard(){
        for (int i = 0; i < playerNames.size; i++) {
            String playerData = playerNames.get(i) + " - " + playerScores.get(i);
            playerLabels.get(i).setText(playerData);

        }
    }

    public void updateScores() {
        int insertIndex = -1;
        for (int i = 0; i < playerScores.size; i++) {
            if (EndGameScore > Integer.parseInt(playerScores.get(i))) {
                insertIndex = i;
                break;
            }
        }
        if (insertIndex != -1){
            playerScores.insert(insertIndex, String.valueOf(EndGameScore));
            playerNames.insert(insertIndex, name);
        } else {
            playerScores.add(String.valueOf(EndGameScore));
            playerNames.add(name);
        }

        if (playerScores.size > 5){
            playerScores.removeIndex(5);
            playerNames.removeIndex(5);
        }
        updateFile();
        updateBoard();
    }



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

}


