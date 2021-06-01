package client;

import common.Data;

import java.io.IOException;

public class Client {
    private final DeliveryHandlerIO deliveryHandlerIO;
    private final InputHelper inputHelper;
    private final RequestHandler requestHandler;

    public Client(DeliveryHandlerIO deliveryHandlerIO, InputHelper inputHelper, RequestHandler requestHandler) {
        this.deliveryHandlerIO = deliveryHandlerIO;
        this.requestHandler = requestHandler;
        this.inputHelper = inputHelper;
    }

    public void run() throws IOException, ClassNotFoundException {
        try {
            deliveryHandlerIO.connectTo();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Подключение к серверу не удалось.");
        }

        String args;
        do {
            System.out.print("% ");
            args = inputHelper.nextLine().trim();
            deliveryHandlerIO.sendCommand(args);
            Data aData = deliveryHandlerIO.read();
            requestHandler.process(aData, args);
        } while (!args.equals("exit"));
    }
}
