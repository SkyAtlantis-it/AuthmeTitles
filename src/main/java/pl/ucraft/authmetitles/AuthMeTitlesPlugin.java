package pl.ucraft.authmetitles;

import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class AuthMeTitlesPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        String[] dependencies = new String[]{"TitleAPI"};
        boolean toDisable = false;
        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage(ChatColor.WHITE + "[AuthMeTitles] Abilitando AuthmeTitles versione 1.0-SNAPSHOT");
        for (String dependency : dependencies) {
            if (isPluginEnabled(dependency)) continue;

            logPluginNotFound(dependency);
            toDisable = true;
            break;
        }

        if (toDisable) {
            Bukkit.shutdown();
            return;
        }

        console.sendMessage(ChatColor.GREEN + "[AuthMeTitles] Abilitato AuthmeTitles versione 1.0-SNAPSHOT");
        AuthMeApi authMeApi = AuthMeApi.getInstance();
        saveDefaultConfig();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new TitleTask(this, authMeApi), 10L, 10L);
    }

    private boolean isPluginEnabled(String name) {
        return Bukkit.getPluginManager().isPluginEnabled(name);
    }

    private void logPluginNotFound(String name) {
        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage(ChatColor.RED + "[AuthMeTitles] " + "Plugin " + name + " non trovato, si prega di inserirlo tra la lista dei plugins.");
    }


    @Override
    public void onDisable() {
        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage(ChatColor.WHITE + "[AuthMeTitles] Disabilitando AuthmeTitles versione 1.0-SNAPSHOT");

    }
}
