package de.maxhenkel.corpse.net;

import de.maxhenkel.corpse.gui.ContainerCorpse;
import net.minecraft.inventory.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageSwitchPage implements Message {

    private int page;

    public MessageSwitchPage() {

    }

    public MessageSwitchPage(int page) {
        this.page = page;
    }


    @Override
    public void executeServerSide(NetworkEvent.Context context) {
        Container container = context.getSender().openContainer;
        if (container instanceof ContainerCorpse) {
            ContainerCorpse containerCorpse = (ContainerCorpse) container;
            containerCorpse.setSlots(page * 54);
        }
    }

    @Override
    public void executeClientSide(NetworkEvent.Context context) {

    }

    @Override
    public MessageSwitchPage fromBytes(PacketBuffer buf) {
        page = buf.readInt();
        return this;
    }

    @Override
    public void toBytes(PacketBuffer buf) {
        buf.writeInt(page);
    }
}
