package eq.larry.dev;

import eq.larry.dev.Listerners.InventoryClickListener;
import eq.larry.dev.Listerners.PlayerDeathListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class UltraCore extends JavaPlugin {
    public void onEnable() {
        this.saveConfig();
        this.getServer().getPluginManager().registerEvents(new InventoryClickListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
    }

    public void onDisable() {
    }
}