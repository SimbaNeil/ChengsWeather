package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import service.AutoUpdateService;

public class AutoUpdateReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent)
	{
		// TODO Auto-generated method stub
		Intent i = new Intent(context,AutoUpdateService.class);
		context.startActivity(i);
	}

}
