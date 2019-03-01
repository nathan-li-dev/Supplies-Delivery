package com.monologica.suppliesDelivery.config;

import com.monologica.suppliesDelivery.utils.ColourFormatter;
import com.monologica.suppliesDelivery.utils.Items;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class GuiConfig {
    private ConfigurationSection yml;

    public GuiConfig(FileConfiguration yml) {
        this.yml = yml.getConfigurationSection("gui");
    }

    public ItemStack backButton() {
        String name = yml.getString("back.name");
        Material material = Material.valueOf(yml.getString("back.material"));
        List<String> lore = yml.getStringList("back.lore");
        return Items.build(name, lore, material);
    }

    public ItemStack nextButton() {
        String name = yml.getString("next.name");
        Material material = Material.valueOf(yml.getString("next.material"));
        List<String> lore = yml.getStringList("next.lore");
        return Items.build(name, lore, material);
    }

    public ItemStack placeholder(boolean corner) {
        String name = yml.getString("placeholder.name");
        Material material = corner ? Material.GRAY_STAINED_GLASS_PANE : Material.BLACK_STAINED_GLASS_PANE;
        List<String> lore = yml.getStringList("placeholder.lore");
        return Items.build(name, lore, material);
    }

    public String name() {
        return new ColourFormatter().format(yml.getString("name"));
    }

}
