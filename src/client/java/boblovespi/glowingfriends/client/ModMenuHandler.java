package boblovespi.glowingfriends.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.ListOption;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import net.minecraft.network.chat.Component;

import java.awt.*;
import java.util.stream.Collectors;

public class ModMenuHandler implements ModMenuApi
{
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory()
	{
		Config.HANDLER.load();
		var inst = Config.HANDLER.instance();
		var defaults = Config.HANDLER.defaults();
		// @formatter:off
		return p -> YetAnotherConfigLib
							.createBuilder()
							.title(Component.literal("Glowing Friends Config"))
							.category(
							ConfigCategory
									.createBuilder()
									.name(Component.translatable("bob-glowing-friends.config.name"))
									.group(
											ListOption
											.<NameColor>createBuilder()
											.name(Component.translatable("bob-glowing-friends.config.friends_list.name"))
											.description(OptionDescription.of(Component.translatable("bob-glowing-friends.config.friends_list.tooltip")))
											.initial(() -> new NameColor("", Color.WHITE))
											.binding(
													defaults.friends.entrySet().stream().map(e -> new NameColor(e.getKey(), new Color(e.getValue()))).toList(),
													() -> inst.friends.entrySet().stream().map(e -> new NameColor(e.getKey(), new Color(e.getValue()))).toList(),
													b -> inst.friends = b.stream().collect(Collectors.toMap(NameColor::name, n -> n.color().getRGB(), (n, m) -> m))
													)
											.customController(NameColorController::new)
											.build()
										  )
									.build()
									 )
							.save(() -> Config.HANDLER.save())
							.build().generateScreen(p);
		// @formatter:on
	}
}
