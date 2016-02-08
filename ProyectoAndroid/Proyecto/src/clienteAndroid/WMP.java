package clienteAndroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.proyecto.R;

public class WMP extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wmp);			
	}
	
	public void pressNext(View v){
		sendCommand("Next");
	}
	
	public void pressBack(View v){
		sendCommand("Back");
	}
	
	public void pressPlay(View v){
		sendCommand("Play");
	}
	
	public void pressStop(View v){
		sendCommand("Stop");
	}
	
	public void pressPause(View v){
		sendCommand("Pause");
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
