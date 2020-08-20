package models;

import interfaces.GameLogicClientIF;
import interfaces.GameLogicServerIF;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameLogicClient extends UnicastRemoteObject implements GameLogicClientIF {

    private int id;
    private static final long serialVersionUID = 1L;
    private GameLogicServerIF server;
    private Scanner scanner = new Scanner(System.in);
    public boolean isLogin;
    public User user = new User("Guest");

    public ArrayList<User> cacheUsers = new ArrayList<User>();
    
    public GameLogicClient(GameLogicServerIF server) throws RemoteException {
        Random random = new Random();
        id = random.nextInt(99);
        this.server = server;
        this.server.registerClient(this);
    }

    public void login() throws  RemoteException {
        System.out.println();
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (isLogin = server.authenticate(id, name, password)) {
            System.out.println("[System] " + user.name + ", welcome back!");
        } else {
            System.out.println("[System] Wrong name or password. Please retry.");
        }
        System.out.println();
    }


    public void listParticipants() throws RemoteException {
        cacheUsers = refreshCache();

        System.out.println("[System] Listing all users in the system");
        for (User user : cacheUsers) {
            System.out.println("[System] " + user.name + " is at coordinate " + user.coordinate);
        }
        System.out.println();
        System.out.println();
    }

    public ArrayList<User> refreshCache() throws RemoteException {

        ArrayList<String> names = server.participantNames();
        ArrayList<Integer> coordinates = server.participantCoordinate();
        ArrayList<User> users = new ArrayList<User>();

        for (int i = 0; i < server.participantSize(); i++) {
            users.add(new User(names.get(i), coordinates.get(i)));
        }

        return users;
    }

    public boolean exit() throws RemoteException {
        server.exit(this);
        UnicastRemoteObject.unexportObject(this, true);

        System.out.println();
        System.out.println("[System] Disconnected from server.");

        return false;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getUserId() throws RemoteException {
        return user.id;
    }

    @Override
    public String getUserName() throws RemoteException {
        return user.name;
    }

    @Override
    public int getUserCoordinate() throws RemoteException {
        return user.coordinate;
    }

    @Override
    public void assignIdentity(int id, String name, int coordinate) throws RemoteException {
        user = new User(id, name, coordinate);
    }

    @Override
    public void moveForward() throws RemoteException {
        user.coordinate = server.updateUser(user.id, 1);
        System.out.println("[System] Your current coordinate is " + user.coordinate);
        System.out.println();
        System.out.println();
    }
}
