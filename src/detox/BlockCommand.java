package detox;

import detox.main.BlockShuffle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.*; 

public class BlockCommand implements CommandExecutor
{

    public static HashMap<UUID, Material> dedicatedBlock = new HashMap<>();
    public static List<Material> l = new ArrayList<>();
    public static Boolean bool;
    public static HashSet<Material> allBlocks = new HashSet<>();
    public static BukkitTask bukkitTask;
    public static BukkitTask b1;
    public static BukkitTask b2;
    public static BukkitTask b3;
    public static BukkitTask b4;
    public static BukkitTask b5;
    public static BukkitTask b6;
    public static BukkitTask b7;
    public static BukkitTask b8;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (strings.length == 1)
        {
            String string = strings[0];
            string.toLowerCase();
            if (string.equals("start"))
            {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[!] &aBlock Shuffle has started!"));
                aidanSmells();
                return false;
            }
            if (string.equals("stop"))
            {
                bool = false;
                if (bukkitTask == null)
                {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[!] &cYou haven't started Block Shuffle!"));
                    return false;
                }
                bukkitTask.cancel();
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[!] &cYou have stopped Block Shuffle! Thanks for playing!"));
                return false;
            }
        }
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[!] &cCorrect Usage: &a/block start/stop"));
        return false;
    }

    public static void aidanSmells()
    {
        bool = true;
        bukkitTask = Bukkit.getScheduler().runTaskTimer(BlockShuffle.instance, () ->
        {
            if (bool)
            {
                for (Player p : Bukkit.getOnlinePlayers())
                {

                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[!] &aTime is up!"));
                    if (BlockMove.blockStepped.get(p.getUniqueId()) == null)
                    {
                        BlockMove.blockStepped.put(p.getUniqueId(), false);
                    }
                    if (!BlockMove.blockStepped.get(p.getUniqueId()))
                    {
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[!] &c" + p.getDisplayName() + "&c didn't find their block!"));
                    }
                    BlockMove.blockStepped.put(p.getUniqueId(), false);
                }
                for (Player p : Bukkit.getOnlinePlayers())
                {
                    Material block = (Material) allBlocks.toArray()[new Random().nextInt(allBlocks.size())];
                    dedicatedBlock.put(p.getUniqueId(), block);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l[!] &eYou need to stand on &e" + block.toString().toLowerCase().replace('_', ' ')));
                }
                b1 = Bukkit.getScheduler().runTaskLater(BlockShuffle.instance, () -> Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[!] &cYou have 1 minute remaining!")), 4800);
                b2 = Bukkit.getScheduler().runTaskLater(BlockShuffle.instance, () -> Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[!] &cYou have 30 seconds remaining!")), 5400);
                b3 = Bukkit.getScheduler().runTaskLater(BlockShuffle.instance, () -> Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[!] &cYou have 15 seconds remaining!")), 5700);
                b4 = Bukkit.getScheduler().runTaskLater(BlockShuffle.instance, () -> Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[!] &cYou have 5 seconds remaining!")), 5900);
                b5 = Bukkit.getScheduler().runTaskLater(BlockShuffle.instance, () -> Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[!] &cYou have 4 seconds remaining!")), 5920);
                b6 = Bukkit.getScheduler().runTaskLater(BlockShuffle.instance, () -> Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[!] &cYou have 3 seconds remaining!")), 5940);
                b7 = Bukkit.getScheduler().runTaskLater(BlockShuffle.instance, () -> Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[!] &cYou have 2 seconds remaining!")), 5960);
                b8 =Bukkit.getScheduler().runTaskLater(BlockShuffle.instance, () -> Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c&l[!] &cYou have 1 second remaining!")), 5980);
            }
        }, 0, 20 * 60 * 5);
    }

    public static void cancelTask(BukkitTask task)
    {
        task.cancel();
        aidanSmells();
    }
}
