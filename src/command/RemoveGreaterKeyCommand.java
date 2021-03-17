package command;

import factory.FlatHashMap;

public class RemoveGreaterKeyCommand implements Command {
    private final FlatHashMap flatHashMap;

    public RemoveGreaterKeyCommand(FlatHashMap flatHashMap) {
        this.flatHashMap = flatHashMap;
    }

    @Override
    public void execute(String[] params) {
        if (params.length != 1) {
            System.out.println("Первым аргументом вводится key.");
            return;
        }
        int key;
        try {
            key = Integer.parseInt(params[0]);
        } catch (NumberFormatException e) {
            System.out.println("Ключ должен быть числом.");
            return;
        }

        flatHashMap.entrySet().removeIf(entry -> entry.getKey() < key);
    }

    @Override
    public String shortInfo() {
        return "Удалить из коллекции все элементы, ключ которых превышает заданный";
    }

    @Override
    public String name() {
        return "remove_greater_key";
    }
}