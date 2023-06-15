package de.moshpit.ttt.countdowns;

import de.moshpit.ttt.TTT;
import de.moshpit.ttt.gamestates.GameState;
import de.moshpit.ttt.gamestates.GameStateManager;
import de.moshpit.ttt.gamestates.LobbyState;
import de.moshpit.ttt.utils.Chat;
import org.bukkit.Bukkit;

public class LobbyCountdown extends Countdown{

    private static final int COUNTDOWN_TIME = 30, IDLE_TIME = 30;
    private GameStateManager gameStateManager;

    private int seconds;
    private int idleID;
    private boolean isIdling;
    private boolean isRunning;

    public LobbyCountdown(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        seconds = COUNTDOWN_TIME;
    }

    @Override
    public void start() {
        isRunning = true;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStateManager.getInstance(), new Runnable() {
            @Override
            public void run() {
                switch (seconds) {
                    case 30: case 15: case 10: case 5: case 3: case 2:
                        Bukkit.broadcastMessage(Chat.CHAT_PREFIX + "§7Das Spiel startet in §e" + seconds + " §7Sekunden");
                        break;
                    case 1:
                        Bukkit.broadcastMessage(Chat.CHAT_PREFIX + "§7Das Spiel startet in §e" + seconds + " §7Sekunde");
                        break;
                    case 0:
                        gameStateManager.setGameState(GameState.INGAME_STATE);
                        break;

                    default:
                        break;
                }
                seconds--;
            }
        }, 0, 20);
    }

    @Override
    public void stop() {
        if(isRunning) {
            Bukkit.getScheduler().cancelTask(taskID);
            isRunning = false;
            seconds = COUNTDOWN_TIME;
        }
    }

    public void startIdle() {
        isIdling = true;
        idleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStateManager.getInstance(), new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(Chat.CHAT_PREFIX + "§7Es fehlen noch §e" +
                        (LobbyState.MIN_PLAYERS - gameStateManager.getInstance().getPlayers().size()) + " §7Spieler");
            }
        }, 0, 20 * IDLE_TIME);

    }

    public void stopIdle() {
        if(isIdling) {
            Bukkit.getScheduler().cancelTask(idleID);
            isIdling = false;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

}
