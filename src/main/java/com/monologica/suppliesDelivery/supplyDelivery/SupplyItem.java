package com.monologica.suppliesDelivery.supplyDelivery;

import com.monologica.suppliesDelivery.gui.SupplyItemColourScheme;
import com.monologica.suppliesDelivery.utils.ColourFormatter;
import com.monologica.suppliesDelivery.utils.TextFormatter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SupplyItem extends ItemStack {

    private double baseValue;
    private int demand;
    private float minMultiplier;
    private float maxBonus;
    private int sold;

    public SupplyItem(ItemStack item, double baseValue, int demand, float minMultiplier, float maxBonus) {
        super(item);
        this.baseValue = baseValue;
        this.demand = demand;
        this.minMultiplier = minMultiplier;
        this.maxBonus = maxBonus;
        this.sold = 0;
    }

    private int adjustedDemand() {
        return demand - sold;
    }

    private float bonus() {
        return maxBonus * adjustedDemand() / demand;
    }

    public void reset() {
        sold = 0;
    }

    public float multiplier() {
        return Math.max(minMultiplier, 1 + bonus());
    }

    public double marketValue() {
        return baseValue * multiplier();
    }

    public void buy(int amount) {
        sold += amount;
    }

    public SupplyItemColourScheme multiplierColour() {

        float multiplier = multiplier();
        if(multiplier >= 1)
            return new SupplyItemColourScheme(ChatColor.DARK_GREEN, ChatColor.GREEN);
        if(multiplier < 1 && multiplier >= 0.85)
            return new SupplyItemColourScheme(ChatColor.YELLOW, ChatColor.YELLOW);
        if(multiplier < 0.85)
            return new SupplyItemColourScheme(ChatColor.DARK_RED, ChatColor.RED);

        return new SupplyItemColourScheme(ChatColor.WHITE, ChatColor.WHITE);
    }

    public ItemStack guiify() {
        SupplyItemColourScheme colourScheme = multiplierColour();
        ItemStack item = clone();
        ItemMeta itemMeta = getItemMeta();
        List<String> lore = itemMeta.getLore();

        String name = colourScheme.getMainColour() + ChatColor.stripColor(itemMeta.getDisplayName());
        itemMeta.setDisplayName(name);

        TextFormatter f = new ColourFormatter();
        lore.add("");
        lore.add(f.format(String.format("&fValue: &7$%,.2f &7(%s%.2f%%&7)", marketValue(),colourScheme.getHighlightColour(), multiplier() * 100)));
        lore.add("");
        lore.add(f.format("&8Left click to sell all"));
        lore.add(f.format("&8Right click to sell 1"));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ItemStack)
            return this.isSimilar((ItemStack)obj);
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = 1;

        hash = hash * 31 + getType().hashCode();
        hash = hash * 31 + (hasItemMeta() ? (getItemMeta() == null ? getItemMeta().hashCode() : getItemMeta().hashCode()) : 0);

        return hash;
    }

    public void print() {
        Bukkit.broadcastMessage(getItemMeta().getDisplayName());
        Bukkit.broadcastMessage("base: " + baseValue);
        Bukkit.broadcastMessage("demand: " +  demand);
        Bukkit.broadcastMessage("adjustedDemand: " + adjustedDemand());
        Bukkit.broadcastMessage("bonus: " + bonus());
    }
}
