package ice.dvsdiscordlink;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = DVSDiscordLink.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue DISABLE_CREATIVE = BUILDER
            .comment(" Whether to disable creative mode")
            .define("disableCreative", true);

    private static final ForgeConfigSpec.ConfigValue<String> DISCORD_WEBHOOK = BUILDER
            .comment(" Discord Webhook URL")
            .define("hookurl", "discord.com");


    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean disableCreative;
    public static String hookurl;
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        disableCreative = DISABLE_CREATIVE.get();
        hookurl = DISCORD_WEBHOOK.get();
    }
}
