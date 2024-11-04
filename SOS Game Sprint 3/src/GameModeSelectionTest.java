import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import views.GameView;

public class GameModeSelectionTest {

    private GameView gameView;

    @Before
    public void setUp() {
        // Initialize the GameView
        gameView = new GameView();
    }

    @Test
    public void testSimpleGameModeSelected() {
        // Simulate selecting "Simple Game" radio button
        gameView.getGameMode1().setSelected(true);

        // Assert that "Simple Game" is selected
        assertTrue("Simple Game mode should be selected", gameView.getGameMode1().isSelected());

        // Assert that "General Game" is not selected
        assertFalse("General Game mode should not be selected", gameView.getGameMode2().isSelected());
    }

    @Test
    public void testGeneralGameModeSelected() {
        // Simulate selecting "General Game" radio button
        gameView.getGameMode2().setSelected(true);

        // Assert that "General Game" is selected
        assertTrue("General Game mode should be selected", gameView.getGameMode2().isSelected());

        // Assert that "Simple Game" is not selected
        assertFalse("Simple Game mode should not be selected", gameView.getGameMode1().isSelected());
    }

}
