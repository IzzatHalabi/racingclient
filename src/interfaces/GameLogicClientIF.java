package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameLogicClientIF extends Remote {
    int getId() throws RemoteException;
    int getUserId() throws RemoteException;
    String getUserName() throws RemoteException;
    int getUserCoordinate() throws RemoteException;
    void assignIdentity(int id, String name, int coordinate) throws RemoteException;
    void moveForward() throws RemoteException;
}
