package io.github.unisim.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import io.github.unisim.*;
import io.github.unisim.Event;

import java.awt.*;
import java.util.Random;

import java.util.ArrayList;


public class EventMenu {

    public Table table;
    private Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
    private Cell<Label> eventLabelCell = new Cell<>();
    private Label eventLabel;
    private TextButton continueButton = new TextButton("Continue", GameState.defaultSkin);
    private Event currentEvent;
    private Stage stage;
    private TextButton firstOption;
    private TextButton secondOption;

    public EventMenu(Stage stage) {
        this.stage = stage;
        this.table = null;
    }

    /**
     * Update the bounds of the background & table actors to fit the new size of the screen.

     * @param width - The new width of the screen in pixels.
     * @param height - The enw height of the screen in pixels.
     */
    public void resize(int width, int height) {
        if (table!=null) {
            table.setBounds(width * 0.3f, height * 0.4f, width * 0.4f, height * 0.4f);
            eventLabelCell.width(width * 0.2f);
            eventLabelCell.height(height * 0.2f);
            eventLabel.setFontScale(height * 0.0015f);

            if (currentEvent != null) {
                if (currentEvent.getClass().equals(SingleEvent.class)) {
                    continueButton.pad(height*0.025f,width*0.010f,height*.025f,width*0.010f);
                } else if (currentEvent.getClass().equals(ChoiceEvent.class)) {
                    table.getCell(firstOption).height(height*0.1f).width(width*0.17f);
                    table.getCell(secondOption).width(width*0.17f).height(height*0.1f);
                }
            }
        }

    }

    public void setCurrentEvent(Event event) {

        this.currentEvent = event;
    }

    public void update() {
        if (currentEvent.getClass().equals(SingleEvent.class)) {
            createSinglePopUp(currentEvent);

        } else if (currentEvent.getClass().equals(ChoiceEvent.class)) {
            createChoicePopUp((ChoiceEvent) currentEvent);

        }

    }

    public void createSinglePopUp(Event currentEvent){
        this.table = new Table();
        Texture backgroundTexture = new Texture(Gdx.files.internal("ui/popUp.png"));

        eventLabel = new Label("", skin);
        eventLabelCell = table.add(eventLabel).align(Align.center);
        table.setBackground(new TextureRegionDrawable(backgroundTexture));
        table.row();
        table.add(continueButton).align(Align.center);

        stage.addActor(table);

        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                GameState.currentScreen = GameState.gameScreen;
                currentEvent.getImpact((eventLabel.toString()));
                table.setVisible(false);
                GameState.paused = false;
                table.remove();
            }
        });
        eventLabel.setText(currentEvent.getMessage());
        table.setVisible(true);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void createChoicePopUp(ChoiceEvent currentEvent){
        this.table = new Table();
        Texture backgroundTexture = new Texture(Gdx.files.internal("ui/popUp.png"));

        eventLabel = new Label("", skin);
        eventLabel.setWrap(true);
        eventLabel.setAlignment(Align.center);
        eventLabelCell = table.add(eventLabel);

        table.setBackground(new TextureRegionDrawable(backgroundTexture));
        table.row();
        Random random = new Random();

        // Randomly decide which message goes to the first option
        boolean isPositiveFirst = random.nextBoolean();

        firstOption = isPositiveFirst
                ? new TextButton(currentEvent.getPositiveMessage(), skin)
                : new TextButton(currentEvent.getNegativeMessage(), skin);

        secondOption = isPositiveFirst
                ? new TextButton(currentEvent.getNegativeMessage(), skin)
                : new TextButton(currentEvent.getPositiveMessage(), skin);
        firstOption.getLabel().setWrap(true);
        secondOption.getLabel().setWrap(true);
        table.add(firstOption);
        table.add(secondOption);

        stage.addActor(table);

        firstOption.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                GameState.currentScreen = GameState.gameScreen;
                currentEvent.getImpact((firstOption.getLabel()).getText().toString());
                GameState.paused = false;
                table.remove();
            }
        });
        secondOption.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                GameState.currentScreen = GameState.gameScreen;
                currentEvent.getImpact((secondOption.getLabel()).getText().toString());
                GameState.paused = false;
                table.remove();
            }
        });
        eventLabel.setText(currentEvent.getMessage());
        table.setVisible(true);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
