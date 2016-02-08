package clienteAndroid;

import java.io.IOException;

import com.example.proyecto.R;

import ListViewImages.IconifiedText;
import ListViewImages.IconifiedTextListAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;

public class MenuOpciones extends ListActivity {

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		IconifiedTextListAdapter itla = new IconifiedTextListAdapter(this);

		itla.addItem(new IconifiedText("PowerPoint", getResources().getDrawable(R.drawable.powerpoint_icon)));
		itla.addItem(new IconifiedText("Windows Media Player", getResources().getDrawable(R.drawable.wmp_icon)));
		itla.addItem(new IconifiedText("Mouse Emulator", getResources().getDrawable(R.drawable.mouse_icon)));
		itla.addItem(new IconifiedText("Salir", getResources().getDrawable(R.drawable.exit)));
		setListAdapter(itla);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Log.d("",position+"");
		if(position == 0){
			sendCommand("PowerPoint");
			Intent intent = new Intent(v.getContext(), PowerPoint.class);
			startActivityForResult(intent, 0);
		}
		else if(position == 1){
			sendCommand("WMP");
			Intent intent = new Intent(v.getContext(), WMP.class);
			startActivityForResult(intent, 0);
		}else if(position == 2){
			sendCommand("Mouse");
			Intent intent = new Intent(v.getContext(), Mouse.class);
			startActivityForResult(intent, 0);
		}
		else if(position == 3){
			closeConnection();
			finish();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			closeConnection();
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	private void sendCommand(String Command){
		((SocketConnection)getApplication()).getPrintWriter().println(Command);
		((SocketConnection)getApplication()).getPrintWriter().flush();
	}
	
	private void closeConnection(){
		((SocketConnection)getApplication()).getPrintWriter().println("Exit");
		((SocketConnection)getApplication()).getPrintWriter().flush();
		((SocketConnection)getApplication()).getPrintWriter().close();
		try {
			((SocketConnection)getApplication()).getSocket().close();
		} catch (IOException e) {
		}
	}
}

