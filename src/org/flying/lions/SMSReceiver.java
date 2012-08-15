package org.flying.lions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver 
{
	/** Logger-Tag. */
    public static final String TAG = "SMSReceiver";

	@Override
	public void onReceive(final Context context, final Intent intent)
	{
		Bundle extras = intent.getExtras();
        if (extras == null) return;
        Log.v(TAG, "SMS received.");

        // iterate over SMS
		Object[] pdus = (Object[]) extras.get("pdus");
		for (Object pdu : pdus)
		{
			SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdu);
			SMSReceiverPlugin.sendMessage(msg);
			Log.v(TAG, msg.getMessageBody());
		}
	}
}
