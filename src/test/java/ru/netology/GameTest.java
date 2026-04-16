package ru.netology;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.netology.NotRegisteredException;

public class GameTest {

    Player player1 = new Player(1, "Player1", 20);
    Player player2 = new Player(2, "Player2", 15);
    Player player3 = new Player(3, "Player3", 10);
    Player player4 = new Player(4, "Player4", 50);
    Player player5 = new Player(5, "Player5", 20);

    Game game = new Game();

    @Test
    public void secondWin() {
        game.register(player4);
        game.register(player2);

        int expected = 2;
        int actual = game.round("Player2", "Player4");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void firstWin() {
        game.register(player1);
        game.register(player2);

        int expected = 1;
        int actual = game.round("Player1", "Player2");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void equalMore() {
        game.register(player1);
        game.register(player5);

        int expected = 0;
        int actual = game.round("Player1", "Player5");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void firstPlayerNotRegistered() {
        game.register(player1);
        game.register(player4);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Player1", "Player5");
        });
    }

    @Test
    public void secondPlayerNotRegistered() {
        game.register(player1);
        game.register(player2);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Player2", "Player5");
        });
    }

    @Test
    public void bothPlayersNotRegistered() {
        game.register(player4);
        game.register(player5);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Player3", "Player2");
        });
    }
}