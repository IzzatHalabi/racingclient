import interfaces.GameLogicServerIF;
import models.GameLogicClient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class CommunicationManager {

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        GameLogicServerIF chatServer = (GameLogicServerIF) Naming.lookup("rmi://localhost/MultiplayerRacingServer");
        GameLogicClient chatClient = new GameLogicClient(chatServer);
        Scanner scanner = new Scanner(System.in);
        Boolean isRunning = true;

        System.out.println("[System] Connected to server.");

        // LOGIN
        System.out.println("[System] Please Login first.");
        do {
            System.out.println();
            System.out.println("[System] 1. Login");
            System.out.println("[System] 2. Exit");
            System.out.print("> ");

            switch (scanner.nextLine()) {
                case "1" : chatClient.login(); break;
                case "2": isRunning = chatClient.exit(); break;
                default: System.out.println("[System] Wrong input. Please try again.");
            }
        } while (! chatClient.isLogin && isRunning);

        // MULTIPLE ACTION
        String input;
        while (isRunning){
            System.out.println("[System] Please choose your action:");
            System.out.println("[System] 1. Get participant lists and their coordinates");
            System.out.println("[System] 2. Move forward");
            System.out.println("[System] 3. Exit");
            System.out.print("> ");

            input = scanner.nextLine();
            switch (input) {
                case "1": chatClient.listParticipants(); break;
                case "2": chatClient.moveForward(); break;
                case "3": isRunning = chatClient.exit(); break;
                default: System.out.println("[System] Wrong input. Please try again.");
            }
        }
    }
}
