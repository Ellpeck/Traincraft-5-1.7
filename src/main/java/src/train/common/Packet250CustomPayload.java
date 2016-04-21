package src.train.common;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;

import java.io.IOException;


public class Packet250CustomPayload extends Packet {
        public String channel;
        public int length;
        public byte message[];
        public Packet250CustomPayload() {}
        public Packet250CustomPayload(String channel, byte[] message) {
                this.channel = channel;
                this.length = message.length;
                this.message = message;
        }
        public void readPacketData(PacketBuffer datainputstream)
                throws IOException {
                channel = datainputstream.readStringFromBuffer(16);
                length = datainputstream.readShort();
                if (length > 0 && length < 32767)
                {
                        message = new byte[length];
                        datainputstream.readBytes(message);
                }
        }
        public void writePacketData(PacketBuffer dataoutputstream)
                throws IOException {
                dataoutputstream.writeStringToBuffer(channel);
                dataoutputstream.writeShort((short) length);
                if (message != null)
                {
                        dataoutputstream.writeBytes(message);
                }
        }
        public void processPacket(INetHandler nethandler) {
                //nethandler.func_44028_a(this);
        }
        public int getPacketSize() {
                return 2 + channel.length() * 2 + 2 + length;
        }
}