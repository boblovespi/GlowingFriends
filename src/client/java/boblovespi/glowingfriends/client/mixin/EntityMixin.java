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
		if ((Object) this instanceof AbstractClientPlayer player)
		{
			var name = player.getGameProfile().name();
			// GlowingFriends.LOGGER.info("player: {} color: {} team: {}", name, String.format("%06x", Config.HANDLER.instance().friends.getOrDefault(name, 0xdead00)), getTeam() == null ? "no team" : getTeam().getName());
			if (Config.HANDLER.instance().friends.containsKey(name))
				cir.setReturnValue(Config.HANDLER.instance().friends.get(name));
		}
	}
}
