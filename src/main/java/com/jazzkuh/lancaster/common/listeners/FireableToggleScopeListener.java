package com.jazzkuh.lancaster.common.listeners;

import com.jazzkuh.lancaster.LancasterPlugin;
import com.jazzkuh.lancaster.api.enums.PlayerTempModification;
import com.jazzkuh.lancaster.api.events.FireableToggleScopeEvent;
import com.jazzkuh.lancaster.api.objects.LancasterFireable;
import com.jazzkuh.lancaster.compatibility.CompatibilityLayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FireableToggleScopeListener implements Listener {
    @EventHandler
    public void onFireableToggleScope(FireableToggleScopeEvent event) {
        Player player = event.getPlayer();
        LancasterFireable fireable = event.getFireable();
        CompatibilityLayer compatibilityLayer = LancasterPlugin.getInstance().getCompatibilityLayer();

        if (LancasterPlugin.getInstance().getModifiedPlayerMap().containsKey(player.getUniqueId())
                && LancasterPlugin.getInstance().getModifiedPlayerMap().get(player.getUniqueId()) == PlayerTempModification.SCOPED) {
            if (player.hasPotionEffect(PotionEffectType.SLOW)) {
                player.removePotionEffect(PotionEffectType.SLOW);
            }
            if (fireable.isScopePumpkinBlurEnabled()) {
                compatibilityLayer.sendPumpkinEffect(player, true);
            }
            LancasterPlugin.getInstance().getModifiedPlayerMap().remove(player.getUniqueId());
        } else {
            int scopeAmplifier = fireable.getScopeAmplifier();
            LancasterPlugin.getInstance().getModifiedPlayerMap().put(player.getUniqueId(), PlayerTempModification.SCOPED);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, scopeAmplifier, true));
            if (fireable.isScopePumpkinBlurEnabled()) {
                compatibilityLayer.sendPumpkinEffect(player, false);
            }
        }
    }
}
