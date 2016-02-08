package clienteAndroid;


import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.proyecto.R;

public class PowerPoint extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.powerpoint);
	}

	public void pressNext(View v){
		sendCommand("Der");
	}

	public void pressBack(View v){
		sendCommand("Izq");
	}

	public void pressStart(View v){
		sendCommand("F5");
	}

	public void pressStop(View v){
		sendCommand("Esc");
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			sendCommand("Exit");
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void sendCommand(String Command){
		((SocketConnection)getApplication()).getPrintWriter().println(Command);
		((SocketConnection)getApplication()).getPrintWriter().flush();
	}
	
}

