package command;

import factory.FlatHashMap;
import forFlat.Flat;

import java.util.Map;

public class ShowCommand implements Command {
    private final FlatHashMap flatHashMap;

    public ShowCommand(FlatHashMap flatHashMap) {
        this.flatHashMap = flatHashMap;
    }

    @Override
    public void execute(String[] params) {
        if (flatHashMap.size() == 0) {
            System.out.println("Коллекция пустая");
            return;
        }
        for (Map.Entry<Integer, Flat> entry : flatHashMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().toString());
        }
    }

    @Override
    public String shortInfo() {
        return "Вывести все элементы коллекции в строковом представлении";
    }

    @Override
    public String name() {
        return "show";
    }
}
