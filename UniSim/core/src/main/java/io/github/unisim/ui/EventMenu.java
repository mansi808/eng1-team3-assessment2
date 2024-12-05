package io.github.unisim.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import io.github.unisim.*;
import java.util.Random;

import java.util.ArrayList;


public class EventMenu {

    private ShapeActor popUpWindow = new ShapeActor(Colors.get("BLACK"));
    private ArrayList<Event> events = new ArrayList<>();
    private Table table;
    private Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
    private Cell<Label> eventLabelCell = new Cell<>();
    private Label eventLabel;
    private TextButton continueButton = new TextButton("Continue", GameState.defaultSkin);


    EventMenu(Stage stage, ScoreManager scoreManager) {
        this.table = new Table();

        events.add(new PositiveEvent(
            "This is a positive event.", scoreManager
        ));

        events.add(new NegativeEvent(
            "This is a negative event.", scoreManager
        ));

        events.add(new NeutralEvent(
            "This is a neutral event.", scoreManager
        ));

        Event currentEvent = getRandomEvent();


        eventLabel = new Label(currentEvent.getMessage(), skin);
        eventLabelCell = table.add(eventLabel).align(Align.center);
        table.row();
        table.add(continueButton).align(Align.center);

        eventLabel.setText(currentEvent.getMessage());
        stage.addActor(popUpWindow);
        stage.addActor(table);
        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                GameState.currentScreen = GameState.gameScreen;
                currentEvent.getImpact();
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

    public Event getRandomEvent() {
        Random rand = new Random();
        int i = rand.nextInt(0,events.size());
        return events.get(i);
    }

}
