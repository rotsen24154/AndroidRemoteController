package clienteAndroid;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.example.proyecto.R;

public class Mouse extends Activity implements OnTouchListener {

	private View Area;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.mouse);
		Area = (View)findViewById(R.id.Area);
		Area.setOnTouchListener(this);
	}

	public void clickDerecho(View v){
		sendCommand("Izq");
	}
	
	public void clickIzquierdo(View v){
		sendCommand("Der");
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			sendCommand("Exit");
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int Height = Area.getMeasuredHeight();
		int Width = Area.getMeasuredWidth();

		int x = (int) event.getX();
		int y = (int) event.getY();

		if(x < 0){
			x = 0;
		}
		else if (x > Width){
			x = Width;
		}
		if(y < 0){
			y = 0;
		}
		else if(y>Height){
			y = Height;
		}
		((SocketConnection)getApplication()).getPrintWriter().write(x+" "+y+"\n");
		((SocketConnection)getApplication()).getPrintWriter().flush();
		return true;
	}
	
	private void sendCommand(String Command){
		((SocketConnection)getApplication()).getPrintWriter().println(Command);
		((SocketConnection)getApplication()).getPrintWriter().flush();
	}
}

