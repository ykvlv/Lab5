package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.Data;
import common.DataType;
import common.forFlat.Flat;

import java.io.*;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

public class ServerExecutor {
    private final CommandRegister commandRegister;

    public ServerExecutor(CommandRegister commandRegister) {
        this.commandRegister = commandRegister;
    }

    public Data execute(Data data) {
        if (data == null) {
            return null;
        } else if (data.getDataType() == DataType.EXECUTION) {
            return execute(data.getAddress(), (String) data.getObject());
        } else {
            return null;
        }
    }

    private Data execute(SocketAddress address, String args) {
        String result = commandRegister.decryptAndRun(args);
        Data responseData = new Data(DataType.EXECUTION, result);
        responseData.setAddress(address);
        return responseData;
    }

    public void saveAll() {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        HashMap<Integer, Flat> allFlats = new HashMap<>();
        for (Map.Entry<Integer, Flat> entry : commandRegister.getFlats().entrySet()) {
            allFlats.put(entry.getKey(), entry.getValue());
        }
        String json = gson.toJson(allFlats);
        try {
            File file = new File(reg.getName());
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(json);
            osw.close();
            System.out.println("Коллекция " + reg.getName() + " сохранена.");
        } catch (FileNotFoundException e) {
            System.out.println("Доступ к " + reg.getName() + " ограничен.");
        } catch (IOException e) {
            System.out.println("Записать " + reg.getName() + " невозможно.");
        }
    }
}
