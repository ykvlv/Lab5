package command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import factory.FlatHashMap;
import forFlat.Flat;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SaveCommand implements Command {
    private final FlatHashMap flatHashMap;

    public SaveCommand(FlatHashMap flatHashMap) {
        this.flatHashMap = flatHashMap;
    }

    @Override
    public void execute(String[] params) {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        HashMap<Integer, Flat> allFlats = new HashMap<>();
        for (Map.Entry<Integer, Flat> entry : flatHashMap.entrySet()) {
            allFlats.put(entry.getKey(), entry.getValue());
        }
        String json = gson.toJson(allFlats);
        try {
            File file = new File("files/Flats.json");
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(json);
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String shortInfo() {
        return "Сохранить коллекцию в файл";
    }

    @Override
    public String name() {
        return "save";
    }
}
