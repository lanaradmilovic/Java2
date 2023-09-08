/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Administrator;
import domain.Ucesnik;
import domain.Trka;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author Korisnik
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.ADD_UCESNIK:
                    ServerController.getInstance().addUcesnik((Ucesnik) request.getData());
                    break;
                case Operation.ADD_TRKA:
                    ServerController.getInstance().addTrka((Trka) request.getData());
                    break;
                case Operation.DELETE_UCESNIK:
                    ServerController.getInstance().deleteUcesnik((Ucesnik) request.getData());
                    break;
                case Operation.DELETE_TRKA:
                    ServerController.getInstance().deleteTrka((Trka) request.getData());
                    break;
                case Operation.UPDATE_UCESNIK:
                    ServerController.getInstance().updateUcesnik((Ucesnik) request.getData());
                    break;
                case Operation.UPDATE_TRKA:
                    ServerController.getInstance().updateTrka((Trka) request.getData());
                    break;
                case Operation.GET_ALL_ADMINISTRATOR:
                    response.setData(ServerController.getInstance().getAllAdministrator());
                    break;
                case Operation.GET_ALL_SPONZOR:
                    response.setData(ServerController.getInstance().getAllSponzor());
                    break;
                case Operation.GET_ALL_ODREDISTE:
                    response.setData(ServerController.getInstance().getAllOdrediste());
                    break;
                case Operation.GET_ALL_PRIJAVA:

                    response.setData(ServerController.getInstance().getAllPrijavaVoznje((Trka) request.getData()));

                    break;
                case Operation.GET_ALL_UCESNIK:
                    response.setData(ServerController.getInstance().getAllUcesnik());
                    break;
                case Operation.GET_ALL_TRKA:
                    response.setData(ServerController.getInstance().getAllTrka());
                    break;
                case Operation.LOGIN:
                    Administrator administrator = (Administrator) request.getData();
                    Administrator ulogovani = ServerController.getInstance().login(administrator);
                    response.setData(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(e);
        }
        return response;
    }

}
