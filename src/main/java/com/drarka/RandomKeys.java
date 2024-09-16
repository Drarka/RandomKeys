package com.drarka;

import com.drarka.network.RandomizeKeysS2CPacket;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.PacketType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RandomKeys implements ModInitializer {
	public static final String MODID = "randomkeys";
	//public static Map<UUID, Map<String, String>> playersBindings;

	public static final PacketType<RandomizeKeysS2CPacket> RANDOMIZE_KEYS_PACKET = s2c("randomize_keys_packet");

	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		/*playersBindings = new HashMap<>();
		ServerEntityEvents.ENTITY_LOAD.register(((entity, world) -> {
			if(entity instanceof ServerPlayerEntity player) {
				if(!playersBindings.containsKey(player.getUuid())) {
					Map<String, String> keyBinds = new HashMap<>();

					playersBindings.put(player.getUuid(), new HashMap<>())
				}
			}
		}));*/
		PayloadTypeRegistry.playS2C().register(RandomizeKeysS2CPacket.ID, RandomizeKeysS2CPacket.CODEC);
	}


	private static <T extends Packet<ClientPlayPacketListener>> PacketType<T> s2c(String id) {
		return new PacketType(NetworkSide.CLIENTBOUND, Identifier.of(MODID, id));
	}

	private static <T extends Packet<ServerPlayPacketListener>> PacketType<T> c2s(String id) {
		return new PacketType(NetworkSide.SERVERBOUND, Identifier.of(MODID, id));
	}
}