package io.github.unisim.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import io.github.unisim.*;
import java.util.ArrayList;

/**
* Achievement Menu which shows the achievements earned by the player
* after the timer runs out.
 **/
public class AchievementMenu {

    public Table table;
    private Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
    private TextButton continueButton = new TextButton("Go to LeaderBoard", GameState.defaultSkin);
    private Cell<TextButton> buttonCell;
    private Label title = new Label("Achievements", GameState.defaultSkin);
    private Cell<Label> titleCell;
    private Stage stage;
    private ArrayList<Achievement> achievements = new ArrayList<>();
    private Leaderboard leaderboard;

    public AchievementMenu(Stage stage, Leaderboard leaderboard) {
        this.stage = stage;
        this.table = null;
        this.leaderboard = leaderboard;
    }

    /**
     * Update the bounds of the background & table actors to fit the new size of the screen.

     * @param width - The new width of the screen in pixels.
     * @param height - The enw height of the screen in pixels.
     */
    public void resize(int width, int height) {
        if (table!=null) {
            table.setBounds(width * 0.25f, height * 0.3f, width * 0.5f, height * 0.5f);
            title.setFontScale(height * 0.0020f);
            titleCell.padBottom(height*.025f);
            continueButton.pad(height*0.025f,width*0.010f,height*.025f,width*0.010f);


            for (int i = 1; i <= table.getChildren().size - 2; i += 2) {
                table.getCell(table.getChild(i)).width(width * 0.5f);
                table.getCell(table.getChild(i)).height(height * 0.025f);
                ((Label) table.getChild(i)).setFontScale(height * 0.0015f);
            }
            if (!achievements.isEmpty()) {
                for (int i = 2; i <= table.getChildren().size - 1; i += 2) {
                    table.getCell(table.getChild(i)).width(width * 0.5f);
                    table.getCell(table.getChild(i)).height(height * 0.1f);
                    ((Label) table.getChild(i)).setFontScale(height * 0.0012f);
                }
            }
        }

    }

    /**
     * sets achievements for the menu after calculating
     */
    public void setAchievements(ArrayList<Achievement> achievements) {
        this.achievements = (ArrayList<Achievement>) achievements.clone();
    }

    public void update() {
        createPopUp();
        table.setVisible(true);
    }

    /**
     * displays the achievement menu at the end of the game
     */
    public void createPopUp(){
        this.table = new Table();
        Texture backgroundTexture = new Texture(Gdx.files.internal("ui/popUp.png"));

        table.setBackground(new TextureRegionDrawable(backgroundTexture));
        table.setSkin(skin);

        titleCell = table.add(title).center();
        table.row();
        if (!achievements.isEmpty()) {
            for (Achievement achievement : achievements) {
                String score = achievement.isPositiveImpact() ? "+5" : "-5";
                Label title = new Label(achievement.getTitle() + " " + score, skin);
                title.setAlignment(Align.center);
                title.setAlignment(Align.center);
                table.add(title).align(Align.center);

                table.row();
                Label description = new Label(achievement.getDescription(), skin);
                description.setWrap(true);
                description.setAlignment(Align.center);
                table.add(description).align(Align.center);

                table.add(String.valueOf(achievements.size()));
                table.row();
            }
        } else {
            Label message = new Label("You have got a pristine record of almost doing something noteworthy. Better luck next time! ", skin);
            message.setWrap(true);
            message.setAlignment(Align.center);
            table.add(message).align(Align.center);
        }

        table.row();
        buttonCell = table.add(continueButton).center();

        table.setVisible(false);
        stage.addActor(table);

        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                table.remove();
                achievements.clear();
                GameState.paused = false;
                GameState.gameOver=true;
                leaderboard.endGame();
            }
        });
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    /**
     * resets the table when game starts over
     */
    public void reset() {
        table.setVisible(false);
        achievements.clear();
    }

}
