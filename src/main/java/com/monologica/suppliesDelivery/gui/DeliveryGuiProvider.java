package com.monologica.suppliesDelivery.gui;

import com.monologica.suppliesDelivery.SuppliesDeliveryPlugin;
import com.monologica.suppliesDelivery.config.GuiConfig;
import com.monologica.suppliesDelivery.supplyDelivery.SupplyItem;
import com.monologica.suppliesDelivery.supplyDelivery.Transaction;
import com.monologica.suppliesDelivery.utils.CompletedTransactionFormatter;
import com.monologica.suppliesDelivery.utils.TextFormatter;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class DeliveryGuiProvider implements InventoryProvider {

    private final SuppliesDeliveryPlugin plugin;
    private SupplyItemView view;

    public DeliveryGuiProvider(SuppliesDeliveryPlugin plugin) {
        this.plugin = plugin;
        view = new SupplyItemView(plugin.getSupplyManager());
    }

    @Override
    public void init(Player player, InventoryContents contents) {
        GuiConfig config = plugin.getGuiConfig();

        contents.fillBorders(ClickableItem.empty(config.placeholder(false)));
        contents.set(0,0, ClickableItem.empty(config.placeholder(true)));
        contents.set(0,8, ClickableItem.empty(config.placeholder(true)));
        contents.set(2,0, ClickableItem.empty(config.placeholder(true)));
        contents.set(2,8, ClickableItem.empty(config.placeholder(true)));

        contents.set(1, 0, ClickableItem.of(config.backButton(), e-> {
            if (view.back())
                updateSupplies(player,contents);
        }));

        contents.set(1, 8, ClickableItem.of(config.nextButton(), e-> {
            if (view.next())
                updateSupplies(player, contents);
        }));

        updateSupplies(player, contents);
    }

    @Override
    public void update(Player player, InventoryContents contents) { }

    public void updateSupplies(Player player, InventoryContents contents) {
        int row = 1;
        int col = 1;

        for(SupplyItem item: view.get()) {
            contents.set(row, col++, ClickableItem.of(item.guiify(), e-> {

                int amount = e.getClick().isRightClick() ? 1 : 10000;
                Transaction t = new Transaction(player, item, amount);
                TextFormatter f = new CompletedTransactionFormatter(t.execute());

                String message = f.format(plugin.getMessagesConfig().sold());
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 100, 1.2f);
                updateSupplies(player, contents);
            }));
        }
    }




}
