package de.moshpit.ttt.gamestates;

import de.moshpit.ttt.countdowns.LobbyCountdown;
import org.bukkit.Bukkit;

public class LobbyState extends GameState{

    public static final int MIN_PLAYERS = 1,
                            MAX_PLAYERS = 8;

    private LobbyCountdown countdown;

    public LobbyState(GameStateManager gameStateManager) {
        countdown = new LobbyCountdown(gameStateManager);
    }

    @Override
    public void start() {
        countdown.startIdle();
    }

    @Override
    public void stop() {
        Bukkit.broadcastMessage("ingamestate würde starten");
    }

    public LobbyCountdown getCountdown() {
        return countdown;
    }
}
