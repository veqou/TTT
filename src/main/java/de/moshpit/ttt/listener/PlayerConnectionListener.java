package de.moshpit.ttt.listener;

import de.moshpit.ttt.TTT;
import de.moshpit.ttt.countdowns.LobbyCountdown;
import de.moshpit.ttt.gamestates.LobbyState;
import de.moshpit.ttt.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    private TTT instance;

    public PlayerConnectionListener(TTT instance) {
        this.instance = instance;
    }

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        if(!(instance.getGameStateManager().getCurrentGameState() instanceof LobbyState)) return;
        Player player = event.getPlayer();
        instance.getPlayers().add(player);
        event.setJoinMessage(Chat.CHAT_PREFIX + "§e" + player.getDisplayName() + "§7 hat das Spiel betreten.");

        LobbyState lobbyState = (LobbyState) instance.getGameStateManager().getCurrentGameState();
        LobbyCountdown countdown = lobbyState.getCountdown();
        if(instance.getPlayers().size() >= LobbyState.MIN_PLAYERS) {
            if(!countdown.isRunning()) {
                countdown.stopIdle();
                countdown.start();
            }
        }


    }

    @EventHandler
    public void handlePlayerQuit(PlayerQuitEvent event) {
        if(!(instance.getGameStateManager().getCurrentGameState() instanceof LobbyState)) return;
        Player player = event.getPlayer();
        instance.getPlayers().remove(player);
        event.setQuitMessage(Chat.CHAT_PREFIX + "§e" + player.getDisplayName() + "§7 hat das Spiel verlassen.");

        LobbyState lobbyState = (LobbyState) instance.getGameStateManager().getCurrentGameState();
        LobbyCountdown countdown = lobbyState.getCountdown();
        if(instance.getPlayers().size() < LobbyState.MIN_PLAYERS) {
            if(countdown.isRunning()) {
                countdown.stop();
                countdown.startIdle();
            }
        }
    }



}
