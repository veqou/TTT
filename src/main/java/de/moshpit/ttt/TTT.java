package de.moshpit.ttt;

import de.moshpit.ttt.gamestates.GameState;
import de.moshpit.ttt.gamestates.GameStateManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class TTT extends JavaPlugin {

    private static TTT instance;

    private GameStateManager gameStateManager;
    private ArrayList<Player> players;

    @Override
    public void onEnable() {
        instance = this;
        System.out.println("Das Plugin wurde gestartet");

        players = new ArrayList<>();

        registerCommands();
        registerEvents();

        gameStateManager = new GameStateManager(this);
        gameStateManager.setGameState(GameState.LOBBY_STATE);

    }

    @Override
    public void onDisable() {
        System.out.println("Das Plugin wurde gestoppt");
    }

    private void registerCommands() {

    }

    private void registerEvents() {

    }

    public static TTT getInstance() {
        return instance;
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
