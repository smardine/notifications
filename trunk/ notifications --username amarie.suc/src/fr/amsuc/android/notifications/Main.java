package fr.amsuc.android.notifications;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

public class Main extends Activity implements OnClickListener {
	NotificationManager notificationManager;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ((Button)this.findViewById(R.id.bouton1)).setOnClickListener(this);
        
        //Notification est un service qui tourne en background et sert à interagir avec les notifications
        notificationManager=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        //annuler toute notification précédente
        notificationManager.cancelAll();
        
    }

	@Override
	public void onClick(View v) {
		// Créer un notification à l'action clic sur le bouton de l'activité Main
		Notification nf = new Notification(R.drawable.eqx_icon,"Petit titre",System.currentTimeMillis());
		// pour exécuter la notification il faut:
		nf.contentView = new RemoteViews(getPackageName(), R.layout.note);
		//contentView sera le contenu du message qui sera affiché dans la notification
		//RemoteViews sont des vues qui ne sont pas rattachées à l'activité en cours
		nf.icon = R.drawable.eqx_icon;
		nf.vibrate = new long[]{0,100,25,100};
		//indiquer quel fichier de son à jouer à réception de la notification
		// le fichier son doit être de format .ogg
		nf.sound = Uri.parse("android.resource://"+this.getPackageName()+"/"+R.raw.droid);  
		nf.ledOffMS = 25; //en milliseconde quand la led du téléphone s'éteind
		nf.ledOnMS = 100; // quand la led du téléphone va s'allumer
		nf.ledARGB = Color.CYAN; // définir la couleur de la LED
		nf.flags = nf.flags | Notification.FLAG_SHOW_LIGHTS;
		
		//contentIntent est une activité qui va se lancer lorsqu'on clic sur la notification qui apparait dans la barre de statut
		//obligatoire à toute création de notification
		Intent activity = new Intent(this, Note.class);
		activity.putExtra("param", 1);
		Log.i("", ">> Main, affecter l'activity à la visualisation de la notification");
		PendingIntent pendingintent = PendingIntent.getActivity(this, 0, activity, 0);
		nf.contentIntent = pendingintent;
		
		activity = new Intent(this, Note.class);
		activity.putExtra("param", 2);
		Log.i("", ">> Main, affecter l'activity à la suppression de la notification");
		//il est possible d'appeler une activité lorsque la notification est supprimée
		nf.deleteIntent = PendingIntent.getActivity(this, 0, activity, 0);
		
		//pour lancer la notification
		notificationManager.notify(-1, nf);
	}
}