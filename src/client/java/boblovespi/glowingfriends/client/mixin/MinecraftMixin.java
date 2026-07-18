package boblovespi.glowingfriends.client.mixin;

import boblovespi.glowingfriends.client.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin
{
	@Inject(method = "shouldEntityAppearGlowing(Lnet/minecraft/world/entity/Entity;)Z", at = @At("RETURN"), cancellable = true)
	public void onShouldEntityAppearGlowing(Entity entity, CallbackInfoReturnable<Boolean> cir)
	{
		if (entity instanceof AbstractClientPlayer player)
		{
			var name = player.getGameProfile().getName();
			if (Config.HANDLER.instance().friends.containsKey(name))
				cir.setReturnValue(true);
		}
	}
}
