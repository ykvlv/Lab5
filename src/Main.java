import factory.CommandRegister;
import factory.FlatCreator;
import factory.FlatHashMap;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FlatHashMap flatHashMap = new FlatHashMap(LocalDateTime.now(), args);
        FlatCreator flatCreator = new FlatCreator(scanner);
        CommandRegister cr = new CommandRegister(flatHashMap, flatCreator);

        String request;
        do {
//            System.out.print("% ");
            request = scanner.nextLine();
            cr.decryptAndRun(request);
        } while (!request.equals("exit"));
    }
}
