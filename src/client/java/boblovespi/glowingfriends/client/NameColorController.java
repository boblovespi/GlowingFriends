package boblovespi.glowingfriends.client;

import dev.isxander.yacl3.api.Controller;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.utils.Dimension;
import dev.isxander.yacl3.gui.AbstractWidget;
import dev.isxander.yacl3.gui.YACLScreen;
import dev.isxander.yacl3.gui.controllers.ColorController;
import dev.isxander.yacl3.gui.controllers.string.StringController;
import dev.isxander.yacl3.gui.controllers.string.StringControllerElement;
import net.minecraft.network.chat.Component;

import java.awt.*;

public class NameColorController implements Controller<NameColor>
{
	private final Option<NameColor> option;
	private final Controller<String> nameController;
	private final Controller<Color> colorController;

	public NameColorController(Option<NameColor> option)
	{
		this.option = option;
		var nameOption = new LensOption<>(option, StringController::new, NameColor::name, (n, s) -> new NameColor(s, n.color()));
		nameController = nameOption.controller();
		var colorOption = new LensOption<>(option, ColorController::new, NameColor::color, (n, c) -> new NameColor(n.name(), c));
		colorController = colorOption.controller();
	}

	@Override
	public Option<NameColor> option()
	{
		return option;
	}

	@Override
	public Component formatValue()
	{
		return Component.literal(String.format("%s: 0x%06X", option.pendingValue().name(), option.pendingValue().color().getRGB()));
	}

	@Override
	public AbstractWidget provideWidget(YACLScreen screen, Dimension<Integer> widgetDimension)
	{
		return new NameColorWidget(widgetDimension,
				(StringControllerElement) nameController.provideWidget(screen, widgetDimension),
				(ColorController.ColorControllerElement) colorController.provideWidget(screen, widgetDimension));
	}
}
