package boblovespi.glowingfriends.client;

import com.google.common.collect.ImmutableSet;
import dev.isxander.yacl3.api.*;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LensOption<T, U> implements Option<T>
{
	private final Option<U> inner;
	private final Controller<T> controller;
	private final StateManager<T> state;

	public LensOption(Option<U> inner, Function<Option<T>, Controller<T>> controller, Function<U, T> get, BiFunction<U, T, U> set)
	{
		this.inner = inner;
		this.controller = controller.apply(this);
		state = StateManager.createInstant(get.apply(inner.pendingValue()), () -> get.apply(inner.pendingValue()),
				t -> inner.requestSet(set.apply(inner.pendingValue(), t)));
	}

	// mostly copied from ListOptionEntryImpl
	@Override
	public @NotNull Component name()
	{
		return Component.empty();
	}

	@Override
	public @NotNull OptionDescription description()
	{
		return inner.description();
	}

	@Override
	public @NotNull Component tooltip()
	{
		return inner.tooltip();
	}

	@Override
	public @NotNull Controller<T> controller()
	{
		return controller;
	}

	@Override
	public @NotNull StateManager<T> stateManager()
	{
		return state;
	}

	@Override
	public @NotNull Binding<T> binding()
	{
		throw new RuntimeException("binding not allowed!");
	}

	@Override
	public boolean available()
	{
		return inner.available();
	}

	@Override
	public void setAvailable(boolean available)
	{
		inner.setAvailable(available);
	}

	@Override
	public @NotNull ImmutableSet<OptionFlag> flags()
	{
		return inner.flags();
	}

	@Override
	public boolean changed()
	{
		return inner.changed();
	}

	@Override
	public @NotNull T pendingValue()
	{
		return state.get();
	}

	@Override
	public void requestSet(@NotNull T value)
	{
		state.set(value);
	}

	@Override
	public boolean applyValue()
	{
		return inner.applyValue();
	}

	@Override
	public void forgetPendingValue()
	{
		inner.forgetPendingValue();;
	}

	@Override
	public void requestSetDefault()
	{
		inner.requestSetDefault();
	}

	@Override
	public boolean isPendingValueDefault()
	{
		return inner.isPendingValueDefault();
	}

	@Override
	public void addEventListener(OptionEventListener<T> listener)
	{

	}

	@Override
	public void addListener(BiConsumer<Option<T>, T> changedListener)
	{

	}
}
