package com.monologica.suppliesDelivery.commands;

import com.monologica.suppliesDelivery.SuppliesDeliveryPlugin;
import com.monologica.suppliesDelivery.gui.DeliveryGui;
import com.monologica.suppliesDelivery.utils.Items;
import com.monologica.suppliesDelivery.utils.Messenger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SuppliesDeliveryCommand extends Messenger implements CommandExecutor {

    private final SuppliesDeliveryPlugin plugin;

    public SuppliesDeliveryCommand(SuppliesDeliveryPlugin plugin) {
        super();
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length == 0) {
            sendSplash(commandSender);
            return true;
        }

        switch(args[0].toLowerCase()) {
            case "open":
                openCommand(commandSender);
                break;

            case "reload":
                reloadCommand(commandSender);
                break;

            case "get":
                getSupplies(commandSender);
                break;

            case "reset":
                resetCommand(commandSender);
                break;

            default:
                sendSplash(commandSender);
        }

        return true;
    }

    private boolean checkPermission(CommandSender cs, String permission) {
        if(cs.hasPermission(permission)) {
            return true;
        } else {
            String message = plugin.getMessagesConfig().noPermission();
            sendActionBar(cs, message);
            return false;
        }
    }

    private void resetCommand(CommandSender cs) {
        if(checkPermission(cs, "suppliesdelivery.reset")) {
            plugin.getSupplyManager().reset();
            String message = plugin.getMessagesConfig().reset();
            sendActionBar(cs, message);
        }
    }

    private void getSupplies(CommandSender cs) {
        if(checkPermission(cs, "suppliesdelivery.getsupplies")) {
            if (cs instanceof Player) {
                for (ItemStack item : plugin.getSupplyManager().getSupplies()) {
                    Items.giveOrDrop((Player) cs, item);
                }
            }
        }
    }

    private void reloadCommand(CommandSender cs) {
        if(checkPermission(cs, "suppliesdelivery.reload")) {
            plugin.reloadConfig();
            String message = plugin.getMessagesConfig().reloaded();
            sendActionBar(cs, message);
        }
    }

    private void openCommand(CommandSender cs) {
        if(checkPermission(cs, "suppliesdelivery.opencommand")) {
            if (cs instanceof Player)
                new DeliveryGui(plugin).open((Player) cs);
        }
    }

    private void sendSplash(CommandSender cs) {
        sendMessages(cs, plugin.getMessagesConfig().splash());
    }
}
