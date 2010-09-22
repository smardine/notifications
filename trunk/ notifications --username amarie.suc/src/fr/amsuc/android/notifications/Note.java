package fr.amsuc.android.notifications;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Note extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//associer l'activité note.java au layout note.xml sans ça la récup de 
		//TextView renvoie null ! 
		setContentView(R.layout.note);
		Log.i("", "activite Note : entree");
		//récupérer les indications passées au lancement de la notification
		Bundle extra = this.getIntent().getExtras();
		if (extra != null){
			Log.i("", "activite Note recup param : "+extra.getInt("param", 0));
		}
		//attraper une instance de la vue note.xml
		TextView view = ((TextView)this.findViewById(R.id.text));
		if (view == null){
			Log.i("", ">>Note : recuperation view null !");
		}else {
			Log.i("", "activité Note : "+view.getText());
			if (extra != null){
				switch (extra.getInt("param", 0)){
					case 1:view.setText("Cliqué"); break;
					case 2:view.setText("Supprimé"); break;
				}
			}
		}
	}

}
