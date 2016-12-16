package in.jaaga.learning.platform.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import in.jaaga.learning.platform.MainActivity;

public class BootReciever extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Intent myIntent = new Intent(context, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
    }

}