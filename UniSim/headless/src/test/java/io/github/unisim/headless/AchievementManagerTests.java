package io.github.unisim.headless;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.math.Matrix4;
import io.github.unisim.AchievementManager;
import io.github.unisim.ScoreManager;
import io.github.unisim.building.BuildingManager;
import io.github.unisim.building.BuildingType;
import io.github.unisim.ui.AchievementMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class AchievementManagerTests extends AbstractHeadlessGdxTest {

    private ScoreManager scoreManager;
    private BuildingManager buildingManager;





    @BeforeEach
    public void setUp() {
        // Initialize mocks or real instances
        scoreManager = new ScoreManager();
        buildingManager = new BuildingManager();

    }


    @Test
    public void testEqualiser(){
        AchievementManager achievementManager = new AchievementManager(scoreManager, buildingManager, null);

        Map<BuildingType, Integer> buildingCounts = new HashMap<>();

        // Create a map of building counts for each BuildingType
        buildingCounts.put(BuildingType.RECREATION, 5);
        buildingCounts.put(BuildingType.LEARNING, 5);
        buildingCounts.put(BuildingType.SLEEPING, 5);
        buildingCounts.put(BuildingType.EATING, 5);
        buildingManager.setBuildingCounts(buildingCounts); // Set building counts directly

        // Call method to calculate achievements
        achievementManager.calculateAchievements();

        // Assert that achievements are added correctly
        assertTrue(achievementManager.getAchievements().stream()
                .anyMatch(a -> a.getTitle().equals("Equaliser")));
    }
    @Test
    public void testChampion(){
        AchievementManager achievementManager = new AchievementManager(scoreManager, buildingManager, null);

        scoreManager.setScore(600); // Directly set the score to 600
        achievementManager.calculateAchievements();
        assertTrue(achievementManager.getAchievements().stream()
                .anyMatch(a -> a.getTitle().equals("Champion")));


    }

    @Test
    public void testMinimiser(){
        AchievementManager achievementManager = new AchievementManager(scoreManager, buildingManager, null);
        scoreManager.setScore(0);
        achievementManager.calculateAchievements();
        assertTrue(achievementManager.getAchievements().stream()
                .anyMatch(a -> a.getTitle().equals("Minimalist")));

    }

    @Test
    public void testMaximiser(){
        AchievementManager achievementManager = new AchievementManager(scoreManager, buildingManager, null);

        Map<BuildingType, Integer> buildingCounts = new HashMap<>();
        buildingCounts.put(BuildingType.RECREATION, 15);
        buildingCounts.put(BuildingType.LEARNING, 15);
        buildingCounts.put(BuildingType.SLEEPING, 15);
        buildingCounts.put(BuildingType.EATING, 6);
        buildingManager.setBuildingCounts(buildingCounts); // Set building counts directly

        achievementManager.calculateAchievements();

        assertTrue(achievementManager.getAchievements().stream()
                .anyMatch(a -> a.getTitle().equals("Maximiser")));

    }





    // Additional tests could include testing edge cases and other achievement conditions
}
