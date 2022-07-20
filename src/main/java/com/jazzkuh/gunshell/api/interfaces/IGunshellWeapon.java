package com.jazzkuh.gunshell.api.interfaces;

import com.jazzkuh.gunshell.utils.ItemBuilder;
import org.bukkit.inventory.ItemStack;

public interface IGunshellWeapon {
    ItemBuilder getItem();
    ItemStack getItemStack();
}
