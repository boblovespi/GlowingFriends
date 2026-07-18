package boblovespi.glowingfriends.client;

import dev.isxander.yacl3.api.utils.Dimension;
import dev.isxander.yacl3.gui.AbstractWidget;
import dev.isxander.yacl3.gui.controllers.ColorController;
import dev.isxander.yacl3.gui.controllers.string.StringControllerElement;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.ContainerEventHandler;
import net.minecraft.client.gui.components.events.GuiEventListener;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NameColorWidget extends AbstractWidget implements ContainerEventHandler
{
	private final StringControllerElement nameWidget;
	private final ColorController.ColorControllerElement colorWidget;
	private boolean focused;
	private @Nullable GuiEventListener eventListener;

	public NameColorWidget(Dimension<Integer> dim, StringControllerElement nameWidget, ColorController.ColorControllerElement colorWidget)
	{
		super(dim);
		this.nameWidget = nameWidget;
		this.colorWidget = colorWidget;
		var halfWidth = dim.width() / 2;
		var nameDim = dim.clone().withWidth(halfWidth);
		var colorDim = dim.clone().withWidth(halfWidth).withX(dim.x() + halfWidth);
		nameWidget.setDimension(nameDim);
		colorWidget.setDimension(colorDim);
	}

	@Override
	public void render(GuiGraphics graphics, int x, int y, float delta)
	{
		nameWidget.render(graphics, x, y, delta);
		colorWidget.render(graphics, x, y, delta);
	}

	@Override
	public List<? extends GuiEventListener> children()
	{
		return List.of(nameWidget, colorWidget);
	}

	@Override
	public boolean isDragging()
	{
		return false;
	}

	@Override
	public void setDragging(boolean bl)
	{

	}

	@Override
	public GuiEventListener getFocused()
	{
		return eventListener;
	}

	@Override
	public void setFocused(@Nullable GuiEventListener guiEventListener)
	{
		this.eventListener = guiEventListener;
	}

	@Override
	public void setDimension(Dimension<Integer> dim)
	{
		super.setDimension(dim);
		var halfWidth = dim.width() / 2;
		var nameDim = dim.clone().withWidth(halfWidth);
		var colorDim = dim.clone().withWidth(halfWidth).withX(dim.x() + halfWidth);
		nameWidget.setDimension(nameDim);
		colorWidget.setDimension(colorDim);
	}
}
