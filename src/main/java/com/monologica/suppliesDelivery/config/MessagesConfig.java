package com.monologica.suppliesDelivery.config;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class MessagesConfig {
    private ConfigurationSection yml;

    public MessagesConfig(FileConfiguration yml) {
        this.yml = yml.getConfigurationSection("messages");
    }

    public String sold() {
        return yml.getString("sold");
    }
    public String reloaded() {
        return yml.getString("reloaded");
    }
    public String reset() {
        return yml.getString("reset");
    }
    public String noPermission() {
        return yml.getString("no_permission");
    }
    public List<String> splash() {
        return yml.getStringList("splash");
    }
}
