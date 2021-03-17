package command;

import factory.CommandRegister;

import java.io.*;

public class ExecuteScriptCommand implements Command {
    private final CommandRegister cr;

    public ExecuteScriptCommand(CommandRegister cr) {
        this.cr = cr;

    }

    @Override
    public void execute(String[] params) {
        if (params.length != 1) {
            System.out.println("Первым параметром должен быть path");
            return;
        }
        try {
            File file = new File(params[0]);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                cr.decryptAndRun(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
