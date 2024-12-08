package io.github.unisim.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
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

    private ShapeActor popUpWindow = new ShapeActor(Colors.get("BLACK"));
    public Table table;
    private Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
    private Cell<Label> eventLabelCell = new Cell<>();
    private Label eventLabel;
    private TextButton continueButton = new TextButton("Continue", GameState.defaultSkin);
    private Event currentEvent;


    public EventMenu(Stage stage, ScoreManager scoreManager) {
        this.table = new Table();
        Texture backgroundTexture = new Texture(Gdx.files.internal("ui/background.png"));

        eventLabel = new Label("", skin);
        eventLabelCell = table.add(eventLabel).align(Align.center);
        table.setBackground(new TextureRegionDrawable(backgroundTexture));
        table.row();
        table.add(continueButton).align(Align.center);

        table.setVisible(false);
        stage.addActor(table);

        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                GameState.currentScreen = GameState.gameScreen;
                currentEvent.getImpact();
                table.setVisible(false);
                GameState.paused = true;
            }
        });
    }

    /**
     * Update the bounds of the background & table actors to fit the new size of the screen.

     * @param width - The new width of the screen in pixels.
     * @param height - The enw height of the screen in pixels.
     */
    public void resize(int width, int height) {
        popUpWindow.setBounds(width*0.35f, height * 0.4f,width*0.3f, height*0.3f);
        table.setBounds(width*0.35f, height * 0.4f,width*0.3f, height*0.3f);
        eventLabelCell.width(width * 0.2f).height(height * 0.2f);
        eventLabel.setFontScale(height * 0.0015f);
    }

    public void setCurrentEvent(Event event) {
        this.currentEvent = event;
    }

    public void update() {
        eventLabel.setText(currentEvent.getMessage());
        table.setVisible(true);
    }

}
