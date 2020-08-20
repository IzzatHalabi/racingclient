package models;

public class User {

    public int id = 0;
    public String name;
    public String password;
    public int coordinate = 0;

    User(String name){
        this.name = name;
    }

    User(String name, int coordinate) {
        this.name = name;
        this.coordinate = coordinate;
    }

    User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    User(int id, String name, int coordinate) {
        this.id = id;
        this.name = name;
        this.coordinate = coordinate;
    }
}
