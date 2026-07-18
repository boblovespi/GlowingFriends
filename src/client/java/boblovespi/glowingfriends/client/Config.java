package boblovespi.glowingfriends.client;

import boblovespi.glowingfriends.GlowingFriends;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.util.LinkedHashMap;
import java.util.Map;

public class Config
{
	public static ConfigClassHandler<Config> HANDLER = ConfigClassHandler.createBuilder(Config.class)
																		 .id(GlowingFriends.id("config"))
																		 .serializer(c -> GsonConfigSerializerBuilder.create(c)
																													 .setPath(FabricLoader.getInstance()
																																		  .getConfigDir()
																																		  .resolve(
																																				  GlowingFriends.MOD_ID + ".json5"))
																													 .setJson5(true)
																													 .build())
																		 .build();

	@SerialEntry
	public Map<String, Integer> friends = new LinkedHashMap<>();
}
