package common;

import java.io.Serializable;
import java.net.SocketAddress;

public class Data implements Serializable {
    private final DataType dataType;
    private final Serializable object;
    private SocketAddress address;

    public Data(DataType dataType, Serializable object) {
        this.dataType = dataType;
        this.object = object;
    }

    public void setAddress(SocketAddress address) {
        this.address = address;
    }

    public SocketAddress getAddress() {
        return address;
    }

    public DataType getDataType() {
        return dataType;
    }

    public Serializable getObject() {
        return object;
    }
}
