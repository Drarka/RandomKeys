package com.drarka.network;

import com.drarka.RandomKeys;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.PacketType;

public class RandomizeKeysS2CPacket implements Packet<ClientPlayPacketListener>, CustomPayload {

    public static final CustomPayload.Id<RandomizeKeysS2CPacket> ID = new CustomPayload.Id<>(RandomKeys.RANDOMIZE_KEYS_PACKET.id());

    public static final PacketCodec<PacketByteBuf, RandomizeKeysS2CPacket> CODEC = Packet.createCodec(RandomizeKeysS2CPacket::write, RandomizeKeysS2CPacket::new);

    public RandomizeKeysS2CPacket() {}
    public RandomizeKeysS2CPacket(PacketByteBuf buf) {}

    private void write(PacketByteBuf buf) {

    }


    @Override
    public PacketType<? extends Packet<ClientPlayPacketListener>> getPacketId() {
        return null;
    }

    @Override
    public void apply(ClientPlayPacketListener listener) {

    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
