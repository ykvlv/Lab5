package client;

import common.Data;
import common.DataType;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

public class DeliveryHandlerIO {
    private final SocketAddress address;
    private final DatagramSocket socket;

    public DeliveryHandlerIO(SocketAddress address, DatagramSocket socket) {
        this.address = address;
        this.socket = socket;
    }

    public Data read() throws IOException, ClassNotFoundException {
        byte[] b = new byte[8192];
        DatagramPacket packet = new DatagramPacket(b, b.length);
        socket.receive(packet);

        ByteArrayInputStream byteArrayIS = new ByteArrayInputStream(b);
        ObjectInputStream objectIS = new ObjectInputStream(byteArrayIS);

        return (Data) objectIS.readObject();
    }

    public void sendCommand(String args) throws IOException {
        Data aData = new Data(DataType.EXECUTION, args);
        sendPacket(serialize(aData));
    }

    public void sendPacket(byte[] bytes) throws IOException {
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address);
        socket.send(packet);
    }

    public byte[] serialize(Serializable object) throws IOException {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        ObjectOutputStream objectOS = new ObjectOutputStream(byteArrayOS);
        objectOS.writeObject(object);
        objectOS.close();
        byteArrayOS.close();
        return byteArrayOS.toByteArray();
    }
}
