package server;

import common.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class Server {
    private final Selector selector;
    private final ServerIOHandler serverIOHandler;
    private final ServerExecutor serverExecutor;
    private final BufferedReader reader;

    public Server(Selector selector, ServerIOHandler serverIOHandler, ServerExecutor serverExecutor, BufferedReader reader) {
        this.selector = selector;
        this.reader = reader;
        this.serverIOHandler = serverIOHandler;
        this.serverExecutor = serverExecutor;
    }

    public void run() throws IOException, ClassNotFoundException {
        while (true) {
            if (selector.select(1000) == 0) {
                if (reader.ready()) {
                    String str = reader.readLine();
                    switch (str) {
                        case "save":
                            serverExecutor.saveAll();
                            break;
                        case "exit":
                            selector.close();
                            reader.close();
                            return;
                        default:
                            System.out.println("А че ты вводишь то?");
                    }
                }
                continue;
            }

            Set<SelectionKey> keys = selector.selectedKeys();
            for (Iterator<SelectionKey> it = keys.iterator(); it.hasNext();) {
                SelectionKey key = it.next();
                if (key.isValid()) {
                    if (key.isReadable()) {
                        System.out.println("READ");
                        Data data = serverIOHandler.readFrom((DatagramChannel) key.channel());
                        Data responseData = serverExecutor.execute(data);

                        key.attach();
                        key.interestOps(SelectionKey.OP_WRITE);
                    } else if (key.isWritable()) {
                        System.out.println("WRITE");
                        Data aData = (Data) key.attachment();
                        Data dataButResponse = serverExecutor.execute(aData);

                        serverIOHandler.writeTo(dataButResponse, (DatagramChannel) key.channel(), aData.getAddress());
                        key.interestOps(SelectionKey.OP_READ);
                    }
                } else {
                    System.out.println("key is not valid. (oT-T)尸");
                }
                it.remove();
            }
        }
    }
}
