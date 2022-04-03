package de.nerax.ttt.listener;

import de.nerax.ttt.TTT;
import de.nerax.ttt.gamestates.LobbyState;
import de.nerax.ttt.utils.Chat;
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
        if(instance.getPlayers().size() >= LobbyState.MIN_PLAYERS)
            Bukkit.broadcastMessage("das spiel würde starten");

    }

    @EventHandler
    public void handlePlayerQuit(PlayerQuitEvent event) {
        if(!(instance.getGameStateManager().getCurrentGameState() instanceof LobbyState)) return;
        Player player = event.getPlayer();
        instance.getPlayers().remove(player);
        event.setQuitMessage(Chat.CHAT_PREFIX + "§e" + player.getDisplayName() + "§7 hat das Spiel verlassen.");
        if(instance.getPlayers().size() >= LobbyState.MIN_PLAYERS)
            Bukkit.broadcastMessage("das spiel würde starten");
    }



}
