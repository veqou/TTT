package de.nerax.ttt;

import de.nerax.ttt.gamestates.GameState;
import de.nerax.ttt.gamestates.GameStateManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TTT extends JavaPlugin {

    private static TTT instance;

    private GameStateManager gameStateManager;
    @Override
    public void onEnable() {
        instance = this;
        System.out.println("Das Plugin wurde gestartet");

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
}
