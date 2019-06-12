package de.maxhenkel.corpse.net;

import de.maxhenkel.corpse.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;

public class MessageShowCorpseInventory implements Message {

    private UUID playerUUID;
    private UUID deathID;

    public MessageShowCorpseInventory() {

    }

    public MessageShowCorpseInventory(UUID playerUUID, UUID deathID) {
        this.playerUUID = playerUUID;
        this.deathID = deathID;
    }


    @Override
    public void executeServerSide(NetworkEvent.Context context) {
        PlayerEntity player = context.getSender().world.getPlayerByUuid(playerUUID);

        if (player != null && player instanceof ServerPlayerEntity) {
            ScreenManager.openCorpseGUI(context.getSender(), (ServerPlayerEntity) player, deathID);
        }
    }

    @Override
    public void executeClientSide(NetworkEvent.Context context) {

    }

    @Override
    public MessageShowCorpseInventory fromBytes(PacketBuffer buf) {
        playerUUID = new UUID(buf.readLong(), buf.readLong());
        deathID = new UUID(buf.readLong(), buf.readLong());
        return this;
    }

    @Override
    public void toBytes(PacketBuffer buf) {
        buf.writeLong(playerUUID.getMostSignificantBits());
        buf.writeLong(playerUUID.getLeastSignificantBits());
        buf.writeLong(deathID.getMostSignificantBits());
        buf.writeLong(deathID.getLeastSignificantBits());
    }
}
