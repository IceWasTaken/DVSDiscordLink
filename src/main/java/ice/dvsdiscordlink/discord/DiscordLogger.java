package ice.dvsdiscordlink.discord;

import com.mojang.brigadier.context.CommandContext;
import ice.dvsdiscordlink.Config;
import ice.dvsdiscordlink.DVSDiscordLink;
import ice.dvsdiscordlink.util.ParameterStringBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = DVSDiscordLink.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)

public class DiscordLogger {
    public static void sendToWebhook(String message) throws IOException {
        URL url = new URL(Config.hookurl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        Map<String, String> param = new HashMap<>();
        param.put("content", message);
        param.put("username", "DVS Logger");

        connection.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(param));
        out.flush();
        out.close();

        int status = connection.getResponseCode();
    }
}
