package io.github.unisim.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import io.github.unisim.*;

import java.util.ArrayList;

public class AchievementMenu {

    private ShapeActor popUpWindow = new ShapeActor(Colors.get("BLACK"));
    public Table table;
    private Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
    private TextButton continueButton = new TextButton("Continue", GameState.defaultSkin);
    private Stage stage;
    private ArrayList<Achievement> achievements = new ArrayList<>();


    public AchievementMenu(Stage stage) {
        this.stage = stage;
        this.table = null;
    }

    /**
     * Update the bounds of the background & table actors to fit the new size of the screen.

     * @param width - The new width of the screen in pixels.
     * @param height - The enw height of the screen in pixels.
     */
    public void resize(int width, int height) {
        popUpWindow.setBounds(width*0.35f, height * 0.4f,width*0.5f, height*0.5f);
        if (table!=null) {
            table.setBounds(width * 0.35f, height * 0.4f, width * 0.5f, height * 0.5f);
        }
    }

    public void setAchievements(ArrayList<Achievement> achievements) {
        this.achievements = achievements;
    }

    public void update() {
        createPopUp();
        table.setVisible(true);
    }

    public void createPopUp(){
        this.table = new Table();
        Texture backgroundTexture = new Texture(Gdx.files.internal("ui/background.png"));

        table.setBackground(new TextureRegionDrawable(backgroundTexture));
        table.setSkin(skin);
        for (Achievement achievement : achievements) {
            table.add(achievement.getTitle());
            table.add(achievement.getDescription());
            table.row();
        }

        table.add(continueButton).align(Align.center);

        table.setVisible(false);
        stage.addActor(table);

        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                GameState.currentScreen = GameState.gameScreen;
//                currentAchievement.getImpact();
                table.setVisible(false);
                GameState.paused = false;
                table.remove();
            }
        });
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
