package factory;

import forFlat.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class FlatCreator {
    Scanner scanner;
    ArrayList<Integer> ids = new ArrayList<>();

    public FlatCreator(Scanner scanner) {
        this.scanner = scanner;
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
        int id = new Random().nextInt(2147483647);
        if (ids.contains(id)) {
            id = new Random().nextInt(2147483647);
        }
        ids.add(id);
        return id;
    }

    private String requestName() {
        String name;
        do {
            System.out.println("Название квартиры:");
            name = scanner.nextLine().trim();
        } while (name.equals(""));
        return name;
    }

    private Coordinates requestCoordinates() {
        System.out.println("Этаж и номер квартиры через пробел:");
        long x;
        float y;
        do {
            try {
                String[] xy = scanner.nextLine().trim().split(" ");
                x = Long.parseLong(xy[0]);
                y = Float.parseFloat(xy[1]);
                if (xy.length != 2) {
                    System.out.println("Введите 2 числа, этаж и номер квартиры:");
                } else if (x >= 39) {
                    System.out.println("Этаж должен быть меньше 39. Введите этаж и номер:");
                } else if (x <= 0 || y <= 0) {
                    System.out.println("Этаж или номер не могут быть отрицательными. Повторите попытку:");
                } else {
                    break;
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Введите этаж и номер через пробел:");
            }
        } while (true);
        return new Coordinates(x, y);
    }

    private Float requestArea() {
        System.out.println("Площадь квартиры:");
        float area;
        while (true) {
            try {
                area = Float.parseFloat(scanner.nextLine().trim());
                if (area > 669) {
                    System.out.println("Площадь не может быть больше 668. Повтори попытку:");
                } else if (area <= 0) {
                    System.out.println("Отрицательная площадь? Ты в каком измерении живешь? Введи площадь квартиры больше 0:");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите площадь квартиры:");
            }
        }
        return area;
    }

    private Integer requestNumberOfRooms() {
        System.out.println("Количество комнат:");
        int numberOfRooms;
        while (true) {
            try {
                numberOfRooms = Integer.parseInt(scanner.nextLine().trim());
                if (numberOfRooms <= 0) {
                    System.out.println("Должна быть хотя бы одна комната. Введи количество комнат:");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Введи количество комнат в числовом формате:");
            }
        }
        return numberOfRooms;
    }

    private Furnish requestFurnish() {
        System.out.println("Ну как там с отделкой? (можно пропустить) Варианты:");
        System.out.println(Arrays.toString(Furnish.class.getEnumConstants()));
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
                System.out.println("Введите значение из предложенных (можно пропустить):");
                System.out.println(Arrays.toString(Furnish.class.getEnumConstants()));
            }
        }
        return furnish;
    }

    private View requestView() {
        System.out.println("Вид из окон? (можно пропустить) Варианты:");
        System.out.println(Arrays.toString(View.class.getEnumConstants()));
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
                System.out.println("Введите значение из предложенных (можно пропустить):");
                System.out.println(Arrays.toString(View.class.getEnumConstants()));
            }
        }
        return view;
    }

    private Transport requestTransport() {
        System.out.println("Какой вид транспорта рядом? (можно пропустить) Варианты:");
        System.out.println(Arrays.toString(Transport.class.getEnumConstants()));
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
                System.out.println("Введите значение из предложенных (можно пропустить):");
                System.out.println(Arrays.toString(Transport.class.getEnumConstants()));
            }
        }
        return transport;
    }

    private House requestHouse() {
        System.out.println("Информация о доме:");
        System.out.println("Название дома (можно пропустить):");
        String houseName = null;
        String houseNameStr = scanner.nextLine().trim();
        if (!houseNameStr.equals("")) {
            houseName = houseNameStr;
        }
        System.out.println("Год постройки (можно пропустить):");
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
                    System.out.println("Введите год постройки больше 0 (можно пропустить):");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Год постройки в числовом формате (можно пропустить):");
            }
        }

        System.out.println("Количество лифтов:");
        long numberOfLifts;
        while (true) {
            try {
                numberOfLifts = Long.parseLong(scanner.nextLine().trim());
                if (numberOfLifts < 0) {
                    System.out.println("Не может быть отрицательное количество лифтов:");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите количество лифтов в числовом формате:");
            }
        }
        return new House(houseName, houseYear, numberOfLifts);
    }
}
