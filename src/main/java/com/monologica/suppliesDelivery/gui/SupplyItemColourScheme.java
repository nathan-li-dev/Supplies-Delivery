package com.monologica.suppliesDelivery.gui;

import org.bukkit.ChatColor;

public class SupplyItemColourScheme {
    ChatColor mainColour;
    ChatColor highlightColour;

    public SupplyItemColourScheme(ChatColor main, ChatColor highlight) {
        this.mainColour = main;
        this.highlightColour = highlight;
    }

    public ChatColor getHighlightColour() {
        return highlightColour;
    }

    public ChatColor getMainColour() {
        return mainColour;
    }
}
