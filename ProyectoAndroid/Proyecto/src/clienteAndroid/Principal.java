package clienteAndroid;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.example.proyecto.R;

import android.app.AlertDialog;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Principal extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);
		Button btnConectar = (Button) findViewById(R.id.Boton);
		final EditText IPServer = (EditText) findViewById(R.id.IPServer);
		final EditText Puerto = (EditText) findViewById(R.id.Puerto);
		btnConectar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Socket socket = setConnection(IPServer.getText().toString(),Puerto.getText().toString());
				PrintWriter out = setPrintWriter(socket);
				if(socket != null && out != null){
					((SocketConnection)getApplication()).setSocket(socket);
					((SocketConnection)getApplication()).setPrintWriter(out);
					Intent intent = new Intent(v.getContext(), MenuOpciones.class);
					startActivityForResult(intent, 0);
				}
			}
		});
	}

	public Socket setConnection(String IP, String Port) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
		Socket socket = null;
		try {
			socket= new Socket(IP, Integer.parseInt(Port));
		} catch (NumberFormatException e) {
			showError(builder,"Verifica el puerto");
		} catch (UnknownHostException e) {
			showError(builder,"Error al conectar con el servidor");
		} catch (IOException e) {
			showError(builder,"Error al conectar con el servidor");
		}
		return socket;
	}
	
	public PrintWriter setPrintWriter(Socket socket){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
		PrintWriter out = null;
		try {
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			showError(builder,"Verifica el puerto");
		}
		return out;
	}
	
	public void showError(AlertDialog.Builder builder, String Mensaje){
		builder.setTitle("Error");
        builder.setMessage(Mensaje);
        builder.setPositiveButton("OK",null);
        builder.create();
        builder.show(); 
	}
}
