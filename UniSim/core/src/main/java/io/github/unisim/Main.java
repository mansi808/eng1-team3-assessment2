package io.github.unisim;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Main extends Game {
  private Screen currentScreen;
  private static Music backgroundMusic;
  public static Music buildingSoundEffect;

  @Override
  public void create() {
      backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
      buildingSoundEffect = Gdx.audio.newMusic(Gdx.files.internal("buildingSoundEffect.mp3"));
      updateMusicSettings();
      backgroundMusic.setLooping(true);
      GameState.currentScreen = GameState.startScreen;
  }

  @Override
  public void render() {
    if (currentScreen != GameState.currentScreen) {
      currentScreen = GameState.currentScreen;
      setScreen(currentScreen);
      currentScreen.resume();
    }
    super.render(); // Ensures the active screen is rendered
  }

    public static void updateMusicSettings() {
        backgroundMusic.setVolume(GameState.settings.getVolume()/10);
        buildingSoundEffect.setVolume(GameState.settings.getVolume());
        backgroundMusic.play();
    }

  @Override
  public void dispose() {
      backgroundMusic.dispose();
  }

  @Override
  public void resize(int width, int height) {
    if (width + height == 0) {
      return;
    }
    ((FullscreenInputProcessor) GameState.fullscreenInputProcessor).resize(width, height);
    GameState.gameScreen.resize(width, height);
    GameState.settingScreen.resize(width, height);
    GameState.startScreen.resize(width, height);
  }
}
