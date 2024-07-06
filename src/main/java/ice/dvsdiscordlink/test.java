package ice.dvsdiscordlink;

import com.mojang.brigadier.context.CommandContext;
import ice.dvsdiscordlink.util.ParameterStringBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.player.Player;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://discord.com/api/webhooks/1258850385670705285/N2qgMxfWhkP3wTuSMdYTEGmnxqaezJ_fbRtkEcnSktq3QrHJtDDBjQK7MXBd7zhE9vmY");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        Map<String, String> param = new HashMap<>();
        param.put("content","Test");
        param.put("username", "DVS Logger");

        connection.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(param));
        out.flush();
        out.close();

        int status = connection.getResponseCode();

    }
}
