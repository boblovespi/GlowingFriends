package boblovespi.glowingfriends.client.mixin;

import boblovespi.glowingfriends.client.Config;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.scores.PlayerTeam;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin
{
	@Shadow
	@Nullable
	public abstract PlayerTeam getTeam();

	@Inject(method = "getTeamColor()I", at = @At("RETURN"), cancellable = true)
	public void onGetTeamColor(CallbackInfoReturnable<Integer> cir)
	{
		if (!Config.HANDLER.instance().ignoreTeamColors && (getTeam() != null && getTeam().getColor().getColor() != null))
			return;
		// intellij doesn't know about mixins
		//noinspection ConstantValue
		if ((Object) this instanceof AbstractClientPlayer player)
		{
			var name = player.getGameProfile().name();
			if (Config.HANDLER.instance().friends.containsKey(name))
				cir.setReturnValue(Config.HANDLER.instance().friends.get(name));
		}
	}
}
