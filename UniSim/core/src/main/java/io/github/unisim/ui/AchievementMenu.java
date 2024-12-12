package io.github.unisim.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import io.github.unisim.*;

import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;

public class AchievementMenu {

    public Table table;
    private Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
    private TextButton continueButton = new TextButton("Continue", GameState.defaultSkin);
    private Cell<TextButton> buttonCell;
    private Label title = new Label("Achievements", GameState.defaultSkin);
    private Cell<Label> titleCell;
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
        if (table!=null) {
            table.setBounds(width * 0.25f, height * 0.3f, width * 0.5f, height * 0.5f);
            buttonCell.width(width * 0.05f).height(height * 0.05f).center();
            titleCell.width(width * 0.05f).height(height * 0.05f).center();
            title.setFontScale(height * 0.0025f);

//            for (int i = 1; i <= table.getChildren().size - 2; i += 2) {
//                Label l = (Label) table.getChildren().get(1);
//                l.setWidth(width * 500);
//                l.setHeight(height * 1f);
//                l.setFontScale(height*0.0015f);
//                table.getChild(2).setWidth(width*0.7f);
//                table.getChild(i+1).setHeight(height*0.1f);
//            }
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
        Texture backgroundTexture = new Texture(Gdx.files.internal("ui/popUp.png"));

        table.setBackground(new TextureRegionDrawable(backgroundTexture));
        table.setSkin(skin);

        titleCell = table.add(title).center();
        table.row();
        for (Achievement achievement : achievements) {
            Label l = new Label(achievement.getTitle(),skin);
//            l.setWrap(true);
            table.add(l).align(Align.center);

            Label l2 = new Label(achievement.getDescription(),skin);
//            l2.setWrap(true);
            table.add(l2).align(Align.center);

            table.row();
        }

        table.row();
        buttonCell = table.add(continueButton).center();

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

    public void reset() {
        table.setVisible(false);
        achievements.clear();
    }
//
//    public void resizeTitle(Label label,int height, int width) {
//        label.setBounds(width,height,width * 0.2f,height(height * 0.2f);
//        label.setFontScale(height * 0.0015f);
//    }
}
