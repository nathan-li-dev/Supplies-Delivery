package com.monologica.suppliesDelivery;

import com.monologica.suppliesDelivery.commands.SuppliesDeliveryCommand;
import com.monologica.suppliesDelivery.config.GuiConfig;
import com.monologica.suppliesDelivery.config.MessagesConfig;
import com.monologica.suppliesDelivery.hooks.HookManager;
import com.monologica.suppliesDelivery.supplyDelivery.ResetDemandTask;
import com.monologica.suppliesDelivery.supplyDelivery.SupplyManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class SuppliesDeliveryPlugin extends JavaPlugin {

    public static Economy economy;

    private HookManager hookManager;
    private SupplyManager supplyManager;
    private GuiConfig guiConfig;
    private MessagesConfig messagesConfig;
    private ResetDemandTask resetDemandTask;

    public GuiConfig getGuiConfig() { return guiConfig; }
    public MessagesConfig getMessagesConfig() { return messagesConfig; }
    public SupplyManager getSupplyManager() { return supplyManager; }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();

        setupHooks();
        setupCommands();
        setupListeners();
        setupEconomy();
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        setupConfig();
        setupUtils();
        setupTasks();
    }

    private void setupCommands() {
        getCommand("supplies").setExecutor(new SuppliesDeliveryCommand(this));
    }

    private void setupHooks() {
        this.hookManager = new HookManager(this);
        hookManager.register();
    }

    private void setupListeners() {

    }

    private void setupConfig() {
        guiConfig = new GuiConfig(getConfig());
        messagesConfig = new MessagesConfig(getConfig());
    }

    private void setupUtils() {
        this.supplyManager = new SupplyManager(loadCustomConfig("items.yml"));
    }

    private void setupTasks() {
        resetDemandTask = new ResetDemandTask(this);
        resetDemandTask.runTaskTimer(this, 0, getConfig().getInt("reset_delay"));
    }

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }
        return (economy != null);
    }

    private YamlConfiguration loadCustomConfig(String path) {
        // Extract file if needed
        File configFile = extractFileFromJar(path);

        // Load YAML
        YamlConfiguration configYaml = new YamlConfiguration();
        try {
            configYaml.load(configFile);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return configYaml;
    }

    public File extractFileFromJar(String path) {
        File file = new File(getDataFolder(), path);

        // Copy file from inside jar
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            saveResource(path, false);
        }

        return file;
    }

}
