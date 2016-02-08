package clienteAndroid;

import java.io.PrintWriter;
import java.net.Socket;

import android.app.Application;

public class SocketConnection extends Application {
    private Socket socket;
    private PrintWriter out;
    
    public Socket getSocket(){
    	return socket;
    }

    public PrintWriter getPrintWriter(){
    	return out;
    }
    
    public void setSocket(Socket socket){
    	this.socket = socket;
    }
    
    public void setPrintWriter(PrintWriter out){
    	this.out = out;
    }
    
    @Override
    public void onCreate() {
    	socket = null;
    	out = null;
    }
    
    
}