package server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import common.forFlat.Flat;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FlatHashMap {
    private final HashMap<Integer, Flat> flats;
    private final String fileName;
    private final ArrayList<Integer> ids = new ArrayList<>();
    private final LocalDateTime initTime;

    public FlatHashMap(LocalDateTime initTime, String fileName) throws IOException {
        this.initTime = initTime;
        this.fileName = fileName;
        File file = new File(fileName.trim());
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String json = "";
        while (br.ready()) {
            json = json.concat(br.readLine().trim());
        }
        Type itemsHashMapType = new TypeToken<HashMap<Integer, Flat>>() {}.getType();
        flats = new Gson().fromJson(json, itemsHashMapType);
        flats.values().forEach(flat -> ids.add(flat.getId()));
    }

    public void clear() {
        flats.clear();
    }

    public LocalDateTime getInitTime() {
        return initTime;
    }

    public String getFileName() {
        return fileName;
    }

    public int size() {
        return flats.size();
    }

    public HashMap<Integer, Flat> getFlats() {
        return flats;
    }

    public boolean containsKey(int key) {
        return flats.containsKey(key);
    }

    public void remove(int key) {
        flats.remove(key);
    }

    public void put(int key, Flat flat) {
        flats.put(key, flat);
    }

    public Flat get(int key) {
        return flats.get(key);
    }

    public ArrayList<Integer> getIds() {
        return ids;
    }
}
