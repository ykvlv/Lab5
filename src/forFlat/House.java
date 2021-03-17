package forFlat;

public class House {
    private String name; //Поле может быть null
    private Long year; //Поле может быть null, Значение поля должно быть больше 0
    private long numberOfLifts; //Значение поля должно быть больше 0

    public House(String name, Long year, long numberOfLifts) {
        this.name = name;
        this.year = year;
        this.numberOfLifts = numberOfLifts;
    }

    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", numberOfLifts=" + numberOfLifts +
                '}';
    }
}