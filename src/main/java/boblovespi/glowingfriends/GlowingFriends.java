package boblovespi.glowingfriends;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlowingFriends implements ModInitializer
{
	public static final String MOD_ID = "bob-glowing-friends";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static ResourceLocation id(String path)
	{
		return new ResourceLocation(MOD_ID, path);
	}

	@Override
	public void onInitialize()
	{
	}
}
