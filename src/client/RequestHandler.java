package client;

import common.Data;
import common.DataType;
import common.forFlat.Flat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;

public class RequestHandler {
    private final DeliveryHandlerIO deliveryHandlerIO;
    private final FlatCreator flatCreator;
    private final HashSet<String> nowExecuting = new HashSet<>();
    private final InputHelper inputHelper;

    public RequestHandler(DeliveryHandlerIO deliveryHandlerIO, FlatCreator flatCreator, InputHelper inputHelper) {
        this.deliveryHandlerIO = deliveryHandlerIO;
        this.inputHelper = inputHelper;
        this.flatCreator = flatCreator;
    }

    public void process(Data aData, String string) throws IOException, ClassNotFoundException {
        if (aData.getDataType() == DataType.SCRIPT) {
            executeScript(string.split(" ")[1]);
        } else if (aData.getDataType() == DataType.ADDITEM) {
            add(string, (String) aData.getObject());
        } else if (aData.getDataType() == DataType.EXECUTION) {
            System.out.println((String) aData.getObject());
        } else if (aData.getDataType() == DataType.BAD) {
            System.out.println("Соединение потеряно. Введите название коллекции:");
            deliveryHandlerIO.connectTo(new Scanner(System.in).nextLine());
        }
    }

    public void add(String string, String request) throws IOException, ClassNotFoundException {
        System.out.println(request);
        Flat flat = flatCreator.createStandardFlat(string.split(" ")[1]);
        deliveryHandlerIO.sendPacket(deliveryHandlerIO.serialize(new Data(DataType.ADDITEM, flat)));
        Data dataButResponse = deliveryHandlerIO.read();
        System.out.println((String) dataButResponse.getObject());
    }

    public void executeScript(String fileName) {
        /// СКРИПТ ААААА
        try {
            File file = new File(fileName);
            Scanner scriptScanner = new Scanner(new InputStreamReader(new FileInputStream(file)));
            if (nowExecuting.contains(fileName)) {
                System.out.printf("Во избежание рекурсии %s запущен не будет%n", fileName);
                return;
            }
            nowExecuting.add(fileName);
            inputHelper.setScriptMode(scriptScanner);
            String line;
            while (inputHelper.hasNext()) {
                line = inputHelper.nextLine().trim();
                deliveryHandlerIO.sendCommand(line);
                Data aData = deliveryHandlerIO.read();
                process(aData, line);
            }
            nowExecuting.remove(fileName);
            inputHelper.endScriptMode();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка с чтением файла");
        }
    }
}
