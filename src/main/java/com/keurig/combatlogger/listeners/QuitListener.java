package com.keurig.combatlogger.listeners;

import com.keurig.combatlogger.CombatLogger;
import com.keurig.combatlogger.api.CombatLoggerAPI;
import com.keurig.combatlogger.event.PlayerCombatQuitEvent;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class QuitListener implements Listener {

    private final CombatLogger plugin = CombatLogger.getInstance();

    @EventHandler
    public void onQuitCombat(PlayerCombatQuitEvent event) {
        final Player player = event.getPlayer();
        this.plugin.getPunishmentManager().onQuit(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (CombatLoggerAPI.isTagged(player)) {
            PlayerCombatQuitEvent bukkitEvent = new PlayerCombatQuitEvent(player);
            Bukkit.getPluginManager().callEvent(bukkitEvent);
        }

        this.plugin.getCombatPlayer().removePlayer(player);
    }
}


