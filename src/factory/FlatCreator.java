package factory;

import forFlat.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class FlatCreator {
    private Scanner scanner;
    private final Scanner userScanner;
    private boolean userInput = true;
    private Scanner scriptScanner;
    private final FlatHashMap flatHashMap;

    public FlatCreator(Scanner userScanner, FlatHashMap flatHashMap) {
        this.userScanner = userScanner;
        this.scanner = userScanner;
        this.flatHashMap = flatHashMap;
    }

    public void setUserInput(boolean b) {
        userInput = b;
        scanner = userInput ? userScanner : scriptScanner;
    }

    public void setScriptScanner(Scanner scriptScanner) {
        this.scriptScanner = scriptScanner;
    }


    public Flat createStandardFlat() {
        int id = generateId();
        String name = requestName();
        Coordinates coordinates = requestCoordinates();
        Float area = requestArea();
        Integer numberOfRooms = requestNumberOfRooms();
        Furnish furnish = requestFurnish();
        View view = requestView();
        Transport transport = requestTransport();
        House house = requestHouse();
        LocalDateTime creationDate = LocalDateTime.now();
        return new Flat(id, name, coordinates, creationDate, area, numberOfRooms, furnish, view, transport, house);
    }

    private int generateId() {
        flatHashMap.updateIds();
        int id = new Random().nextInt(2147483647);
        if (flatHashMap.getIds().contains(id)) {
            id = new Random().nextInt(2147483647);
        }
        flatHashMap.getIds().add(id);
        return id;
    }

    private String requestName() {
        String name;
        do {
            System.out.printf(userInput ? "Имя владельца квартиры:%n" : "");
            name = scanner.nextLine().trim();
        } while (name.equals(""));
        return name;
    }

    private Coordinates requestCoordinates() {
        System.out.printf(userInput ? "Координаты квартиры X и Y через пробел:%n" : "");
        long x;
        float y;
        do {
            try {
                String[] xy = scanner.nextLine().trim().split(" ");
                x = Long.parseLong(xy[0]);
                y = Float.parseFloat(xy[1]);
                if (xy.length != 2) {
                    System.out.printf(userInput ? "Введите 2 числа, X и Y квартиры:%n" : "");
                } else if (x >= 39) {
                    System.out.printf(userInput ? "X должен быть меньше 39. Введите X и Y:%n" : "");
                } else if (x <= 0 || y <= 0) {
                    System.out.printf(userInput ? "X или Y должны быть больше 0. Повторите попытку:%n" : "");
                } else {
                    break;
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.printf(userInput ? "Введите X и Y через пробел:%n" : "");
            }
        } while (true);
        return new Coordinates(x, y);
    }

    private Float requestArea() {
        System.out.printf(userInput ? "Площадь квартиры:%n" : "");
        float area;
        while (true) {
            try {
                area = Float.parseFloat(scanner.nextLine().trim());
                if (area > 669) {
                    System.out.printf(userInput ? "Площадь не может быть больше 668. Повтори попытку:%n" : "");
                } else if (area <= 0) {
                    System.out.printf(userInput ? "Введите площадь квартиры больше 0:%n" : "");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.printf(userInput ? "Введите площадь квартиры:%n" : "");
            }
        }
        return area;
    }

    private Integer requestNumberOfRooms() {
        System.out.printf(userInput ? "Количество комнат:%n" : "");
        int numberOfRooms;
        while (true) {
            try {
                numberOfRooms = Integer.parseInt(scanner.nextLine().trim());
                if (numberOfRooms <= 0) {
                    System.out.printf(userInput ? "Должна быть хотя бы одна комната. Введи количество комнат:%n" : "");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.printf(userInput ? "Введи количество комнат в числовом формате:%n" : "");
            }
        }
        return numberOfRooms;
    }

    private Furnish requestFurnish() {
        System.out.printf(userInput ? "Ну как там с отделкой? (можно пропустить) Варианты:%n" : "");
        System.out.printf(userInput ? Arrays.toString(Furnish.class.getEnumConstants()) + "%n" : "");
        Furnish furnish;
        while (true) {
            try {
                String sc = scanner.nextLine().trim().toUpperCase();
                if (sc.equals("")) {
                    furnish = null;
                    break;
                }
                furnish = Furnish.valueOf(sc);
                break;
            } catch (IllegalArgumentException e) {
                System.out.printf(userInput ? "Введите значение из предложенных (можно пропустить):%n" : "");
                System.out.printf(userInput ? Arrays.toString(Furnish.class.getEnumConstants()) + "%n" : "");
            }
        }
        return furnish;
    }

    private View requestView() {
        System.out.printf(userInput ? "Вид из окон? (можно пропустить) Варианты:%n" : "");
        System.out.printf(userInput ? Arrays.toString(View.class.getEnumConstants()) + "%n" : "");
        View view;
        while (true) {
            try {
                String sc = scanner.nextLine().trim().toUpperCase();
                if (sc.equals("")) {
                    view = null;
                    break;
                }
                view = View.valueOf(sc);
                break;
            } catch (IllegalArgumentException e) {
                System.out.printf(userInput ? "Введите значение из предложенных (можно пропустить):%n" : "");
                System.out.printf(userInput ? Arrays.toString(View.class.getEnumConstants()) + "%n" : "");
            }
        }
        return view;
    }

    private Transport requestTransport() {
        System.out.printf(userInput ? "Какой вид транспорта рядом? (можно пропустить) Варианты:%n" : "");
        System.out.printf(userInput ? Arrays.toString(Transport.class.getEnumConstants()) + "%n" : "");
        Transport transport;
        while (true) {
            try {
                String sc = scanner.nextLine().trim().toUpperCase();
                if (sc.equals("")) {
                    transport = null;
                    break;
                }
                transport = Transport.valueOf(sc);
                break;
            } catch (IllegalArgumentException e) {
                System.out.printf(userInput ? "Введите значение из предложенных (можно пропустить):%n" : "");
                System.out.printf(userInput ? Arrays.toString(Transport.class.getEnumConstants()) + "%n" : "");
            }
        }
        return transport;
    }

    private House requestHouse() {
        System.out.printf(userInput ? "Информация о доме:%n" : "");
        System.out.printf(userInput ? "Название дома (можно пропустить):%n" : "");
        String houseName = null;
        String houseNameStr = scanner.nextLine().trim();
        if (!houseNameStr.equals("")) {
            houseName = houseNameStr;
        }
        System.out.printf(userInput ? "Год постройки (можно пропустить):%n" : "");
        Long houseYear;
        while (true) {
            try {
                String houseYearStr = scanner.nextLine().trim();
                if (houseYearStr.equals("")) {
                    houseYear = null;
                    break;
                }
                houseYear = Long.parseLong(houseYearStr);
                if (houseYear <= 0) {
                    System.out.printf(userInput ? "Введите год постройки больше 0 (можно пропустить):%n" : "");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.printf(userInput ? "Год постройки в числовом формате (можно пропустить):%n" : "");
            }
        }

        System.out.printf(userInput ? "Количество лифтов:%n" : "");
        long numberOfLifts;
        while (true) {
            try {
                numberOfLifts = Long.parseLong(scanner.nextLine().trim());
                if (numberOfLifts < 0) {
                    System.out.printf(userInput ? "Не может быть отрицательное количество лифтов:%n" : "");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.printf(userInput ? "Введите количество лифтов в числовом формате:%n" : "");
            }
        }
        return new House(houseName, houseYear, numberOfLifts);
    }
}
