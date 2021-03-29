package command;

import factory.CommandRegister;
import factory.FlatCreator;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ExecuteScriptCommand implements Command {
    private final CommandRegister cr;
    private final ArrayList<String> nowExecuting = new ArrayList<>();
    private final FlatCreator flatCreator;

    public ExecuteScriptCommand(CommandRegister cr, FlatCreator flatCreator) {
        this.cr = cr;
        this.flatCreator = flatCreator;
    }

    @Override
    public void execute(String[] params) {
        if (params.length != 1) {
            System.out.println("usage: execute_script path");
            return;
        }
        try {
            File file = new File(params[0]);
            Scanner scriptScanner = new Scanner(new InputStreamReader(new FileInputStream(file)));
            if (nowExecuting.contains(params[0])) {
                System.out.printf("Во избежание рекурсии %s запущен не будет%n", params[0]);
                return;
            }
            nowExecuting.add(params[0]);
            flatCreator.setScriptScanner(scriptScanner);
            flatCreator.setUserInput(false);
            String line;
            while (scriptScanner.hasNext()) {
                line = scriptScanner.nextLine();
                System.out.println(line);
                cr.decryptAndRun(line);
            }
            nowExecuting.remove(params[0]);
            flatCreator.setUserInput(true);
        } catch (IOException e) {
            System.out.println("Ошибка с чтением файла");
        }
    }

    @Override
    public String shortInfo() {
        return "Исполнить скрипт из указанного файла";
    }

    @Override
    public String name() {
        return "execute_script";
    }
}
