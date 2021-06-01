package server;

import common.Data;

import java.io.*;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ServerIOHandler {
    private final ByteBuffer byteBuffer = ByteBuffer.allocate(8192);

    public Data readFrom(DatagramChannel channel) throws IOException, ClassNotFoundException {
        byteBuffer.clear();
        SocketAddress address = channel.receive(byteBuffer);
        if (address != null) {
            ByteArrayInputStream byteArrayIS = new ByteArrayInputStream(byteBuffer.array());
            ObjectInputStream objectIS = new ObjectInputStream(byteArrayIS);
            Data aData = (Data) objectIS.readObject();
            objectIS.close();
            byteArrayIS.close();
            aData.setAddress(address);
            return aData;
        }
        return null;
    }

    public void writeTo(Data aData, DatagramChannel channel, SocketAddress address) throws IOException {
        byteBuffer.clear();
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        ObjectOutputStream objectOS = new ObjectOutputStream(byteArrayOS);
        objectOS.writeObject(aData);
        byteBuffer.put(byteArrayOS.toByteArray());
        byteBuffer.flip();
        channel.send(byteBuffer, address);
    }


}
