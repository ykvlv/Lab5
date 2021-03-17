package factory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import forFlat.Flat;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FlatHashMap {
    private HashMap<Integer, Flat> flats;
    private final String path;
    private final LocalDateTime initTime;

    public FlatHashMap(LocalDateTime initTime, String[] args) {
        this.initTime = initTime;
        try {
            File file = new File("files/Flats.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String json = "", line;
            while ((line = br.readLine()) != null) {
                json = json.concat(line.trim());
            }
            Type itemsHashMapType = new TypeToken<HashMap<Integer, Flat>>() {}.getType();
            this.flats = new Gson().fromJson(json, itemsHashMapType);
        } catch (IOException e) {
            e.printStackTrace();
            this.flats = new HashMap<>();
        }
        this.path = "none";
    }

    public void clear() {
        flats.clear();
    }

    public LocalDateTime getInitTime() {
        return initTime;
    }

    public String getPath() {
        return path;
    }

    public int size() {
        return flats.size();
    }

    public Set<Map.Entry<Integer, Flat>> entrySet() {
        return flats.entrySet();
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
}
