package forFlat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flat implements Comparable<Flat> {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float area; //Максимальное значение поля: 668, Значение поля должно быть больше 0
    private Integer numberOfRooms; //Значение поля должно быть больше 0
    private Furnish furnish; //Поле может быть null
    private View view; //Поле может быть null
    private Transport transport; //Поле может быть null
    private House house; //Поле не может быть null

    public Flat(int id, String name, Coordinates coordinates, LocalDateTime creationDate, Float area, Integer numberOfRooms, Furnish furnish, View view, Transport transport, House house) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.furnish = furnish;
        this.view = view;
        this.transport = transport;
        this.house = house;
    }

    @Override
    public String toString() {
        return "\tID: " + id +
                "\n\tДата добавления в список: " + creationDate.format(DateTimeFormatter.ofPattern("dd.MM.uuuu HH:mm:ss")) +
                "\n\tВладелец: " + name +
                "\n\tКоординаты: " + coordinates.toString() +
                "\n\tПлощадь: " + area +
                "\n\tКоличество комнат: " + numberOfRooms +
                ((furnish == null) ? "" : "\n\tОтделка: " + furnish) +
                ((view == null) ? "" : "\n\tВид из окна: " + view) +
                ((transport == null) ? "" : "\n\tТранспорт: " + transport) +
                "\n\tИнформация о доме: " + house.toString();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getArea() {
        return area;
    }

    @Override
    public int compareTo(Flat o) {
        return (int) (this.area - o.getArea());
    }
}