import static org.junit.Assert.*;

import java.awt.GridLayout;

import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import controllers.GameController;
import views.GameView;

public class GameControllerTest {

    private GameView gameView;
    private GameController gameController;

    @Before
    public void setUp() {
        // Initialize the real GameView and GameController
        gameView = new GameView();
        gameController = new GameController(gameView);
    }

    @Test
    public void testInvalidBoardSizeInputNonNumeric() {
        // Simulate non-numeric input
        gameView.getNumSize().setText("abc");

        // Simulate the Enter button click
        gameView.getBtnEnter().doClick();

        // Assert that no buttons were created because of invalid input
        assertNull(gameView.getButtons());
    }

    @Test
    public void testInvalidBoardSizeInputOutOfRange() {
        // Simulate input that is outside the valid range (e.g., 2)
        gameView.getNumSize().setText("2");

        // Simulate the Enter button click
        gameView.getBtnEnter().doClick();

        // Assert that no buttons were created because of out-of-range input
        assertNull(gameView.getButtons());
    }

    @Test
    public void testValidBoardSizeInput() {
        // Simulate valid input (e.g., board size 5,general game mode)
        gameView.getNumSize().setText("5");
        gameView.getGameMode2().setSelected(true);
        
        // Simulate the Enter button click
        gameView.getBtnEnter().doClick();

        // Check that the buttons were created and match the expected board size
        JButton[] buttons = gameView.getButtons();
        assertNotNull(buttons);
        assertEquals(25, buttons.length);  // 5x5 board should have 25 buttons
    }
}
