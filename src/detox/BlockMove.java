package detox;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.UUID;

public class BlockMove implements Listener
{
    public static HashMap<UUID, Boolean> blockStepped = new HashMap<>();

    @EventHandler
    public void playerMove(PlayerMoveEvent e)
    {
        if (BlockCommand.bool == null) {return;}
        if (BlockCommand.bool)
        {
            Player p = e.getPlayer();
            if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == BlockCommand.dedicatedBlock.get(p.getUniqueId()))
            {
                if (!blockStepped.get(p.getUniqueId()))
                {
                    blockStepped.put(p.getUniqueId(), true);
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[!] &e" + p.getDisplayName() + "&a has found their block!"));
                    int i = 0;
                    int v = 0;
                    for (Player player : Bukkit.getOnlinePlayers())
                    {
                        i++;
                        if (blockStepped.get(player.getUniqueId()))
                        {
                            v++;
                        }
                        if (v == i)
                        {
                            BlockCommand.cancelTask(BlockCommand.bukkitTask);
                            BlockCommand.cancelTask(BlockCommand.b1);
                            BlockCommand.cancelTask(BlockCommand.b2);
                            BlockCommand.cancelTask(BlockCommand.b3);
                            BlockCommand.cancelTask(BlockCommand.b4);
                            BlockCommand.cancelTask(BlockCommand.b5);
                            BlockCommand.cancelTask(BlockCommand.b6);
                            BlockCommand.cancelTask(BlockCommand.b7);
                            BlockCommand.cancelTask(BlockCommand.b8);
                        }
                    }
                }
            }
        }
    }
}
