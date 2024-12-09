package io.github.unisim.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.github.unisim.*;
import io.github.unisim.GameState;
import io.github.unisim.ScoreManager;
import io.github.unisim.Timer;

import io.github.unisim.world.UiInputProcessor;
import io.github.unisim.world.World;
import io.github.unisim.world.WorldInputProcessor;

/**
 * Game screen where the main game is rendered and controlled.
 * Supports pausing the game with a pause menu.
 */
public class GameScreen implements Screen {
  private World world;
  private Stage stage = new Stage(new ScreenViewport());

  private EventMenu eventMenu;
  private InfoBar infoBar;
  private BuildingMenu buildingMenu;
  private Timer timer;
  private InputProcessor uiInputProcessor = new UiInputProcessor(stage);
  private InputProcessor worldInputProcessor = new WorldInputProcessor(world);
  private InputMultiplexer inputMultiplexer = new InputMultiplexer();
  private GameOverMenu gameOverMenu = new GameOverMenu();
  private EventManager eventManager;
  private ScoreManager scoreManager;


  /**
   * Constructor for the GameScreen.
   */
  public GameScreen() {
    scoreManager = new ScoreManager();
    world = new World(scoreManager);
    timer = new Timer(300_000);
    infoBar = new InfoBar(stage, timer, world, scoreManager);
    buildingMenu = new BuildingMenu(stage, world);
    eventMenu = new EventMenu(stage, world.scoreManager);
    eventManager = new EventManager(timer, stage, world.scoreManager);

    uiInputProcessor = new UiInputProcessor(stage);
    worldInputProcessor = new WorldInputProcessor(world);
    inputMultiplexer = new InputMultiplexer();
    gameOverMenu = new GameOverMenu();
    inputMultiplexer.addProcessor(GameState.fullscreenInputProcessor);
    inputMultiplexer.addProcessor(stage);
    inputMultiplexer.addProcessor(uiInputProcessor);
    inputMultiplexer.addProcessor(worldInputProcessor);
  }

  @Override
  public void show() {
  }

  @Override
  public void render(float delta) {
    world.render();

    final int MILLISEC_IN_SEC = 1000;
    float dt = Gdx.graphics.getDeltaTime();
    float timeDelta = dt * MILLISEC_IN_SEC;

    if (!GameState.paused && !GameState.gameOver) {

      timer.tick(timeDelta);

      scoreManager.decrementScoreWithTime(timeDelta);
      eventManager.showEvent();

      if (!timer.isRunning()) {
        GameState.gameOver = true;
        Gdx.input.setInputProcessor(gameOverMenu.getInputProcessor());
      }
    }
    stage.act(dt);
    infoBar.update();
    buildingMenu.update();
    stage.draw();
    if (GameState.gameOver) {
      world.zoom((world.getMaxZoom() - world.getZoom()) * 2f);
      world.pan((150 - world.getCameraPos().x) / 10, -world.getCameraPos().y / 10);
      gameOverMenu.render(delta);
    }
  }

  @Override
  public void resize(int width, int height) {
    world.resize(width, height);
    stage.getViewport().update(width, height, true);
    infoBar.resize(width, height);
    buildingMenu.resize(width, height);
    gameOverMenu.resize(width, height);
    eventMenu.resize(width,height);
  }

  @Override
  public void pause() {
  }

  @Override
  public void resume() {
    Gdx.input.setInputProcessor(inputMultiplexer);

    if (GameState.gameOver) {
      GameState.gameOver = false;
      GameState.paused = true;
      timer.reset();
      world.reset();
      infoBar.reset();
      buildingMenu.reset();
    }
  }

  @Override
  public void hide() {
  }

  @Override
  public void dispose() {
    world.dispose();
    stage.dispose();
  }
}