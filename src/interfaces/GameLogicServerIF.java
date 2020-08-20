package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GameLogicServerIF extends Remote {
    boolean authenticate(int clientId, String name, String password) throws RemoteException;
    void registerClient(GameLogicClientIF client) throws RemoteException;
    int participantSize() throws RemoteException;
    ArrayList<String> participantNames() throws RemoteException;
    ArrayList<Integer> participantCoordinate() throws RemoteException;
    int updateUser(int id, int distance) throws RemoteException;
    void exit(GameLogicClientIF client) throws RemoteException;
}
