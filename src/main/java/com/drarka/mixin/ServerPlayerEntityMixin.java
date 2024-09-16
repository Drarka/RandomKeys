package com.drarka.mixin;

import com.drarka.network.RandomizeKeysS2CPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Inject(at=@At("HEAD"), method = "onDeath")
    public void onDeath(DamageSource damageSource, CallbackInfo info) {
        ServerPlayNetworking.send((ServerPlayerEntity) ((Object) this), new RandomizeKeysS2CPacket());
    }
}
