package ice.dvsdiscordlink.util;

import ice.dvsdiscordlink.Config;
import ice.dvsdiscordlink.DVSDiscordLink;
import ice.dvsdiscordlink.discord.DiscordLogger;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mod.EventBusSubscriber(modid = DVSDiscordLink.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Events {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) throws IOException {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatted = formatter.format(date);

        DiscordLogger.sendToWebhook("[" + formatted + "]: Player " + event.getEntity().getName().getString() + " Has Joined The Server");
    }

    @SubscribeEvent
    public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) throws IOException {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatted = formatter.format(date);

        DiscordLogger.sendToWebhook("[" + formatted + "]: Player " + event.getEntity().getName().getString() + " Has Left The Server");
    }

    @SubscribeEvent
    public static void onPlayerChangeGamemode(PlayerEvent.PlayerChangeGameModeEvent event) throws IOException {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatted = formatter.format(date);

        if(event.getNewGameMode() == GameType.CREATIVE && Config.disableCreative) {
            event.setCanceled(true);
            DiscordLogger.sendToWebhook("[" + formatted + "]: Player " + event.getEntity().getName().getString() + " Attempted To Change Their Gamemode To Creative, Canceled");
        } else {
            DiscordLogger.sendToWebhook("[" + formatted + "]: Player " + event.getEntity().getName().getString() + " Has Changed Their Gamemode To " + event.getNewGameMode().getName());
        }

    }

    @SubscribeEvent
    public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) throws IOException {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatted = formatter.format(date);

        DiscordLogger.sendToWebhook("[" + formatted + "]: Player " + event.getEntity().getName().getString() + " Has Changed Their Dimension To " + event.getTo().toString());
    }


}
