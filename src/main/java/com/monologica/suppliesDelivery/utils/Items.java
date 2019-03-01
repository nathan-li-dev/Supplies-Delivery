package com.monologica.suppliesDelivery.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Items {
    // Returns how many items have been taken
    public static int takeFrom(Player player, ItemStack toTake, int amount) {
        int leftToTake = amount;

        for(ItemStack item: player.getInventory().getContents()) {
            if(item != null && toTake.isSimilar(item)) {
                int amountTaken = Math.min(leftToTake, item.getAmount());
                item.setAmount(item.getAmount() - amountTaken);
                leftToTake -= amountTaken;
            }
            if(leftToTake == 0)
                break;
        }
        return amount - leftToTake;
    }

    public static ItemStack build(String name, List<String> lore, Material m, TextFormatter tf) {
        ItemStack is = new ItemStack(m);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(tf.format(name));
        List<String> formattedLore = new ArrayList<>(lore.size());
        for(String s: lore) {
            formattedLore.add(tf.format(s));
        }
        im.setLore(formattedLore);
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack build(String name, List<String> lore, Material m) {
        return build(name, lore, m, new ColourFormatter());
    }

    public static void giveOrDrop(HumanEntity p, ItemStack i) {
        Map<Integer, ItemStack> toDrop = p.getInventory().addItem(i);
        if (!toDrop.isEmpty()) {
            for(ItemStack item: toDrop.values()) {
                p.getWorld().dropItemNaturally(p.getLocation(), item);
            }
        }
    }
}
