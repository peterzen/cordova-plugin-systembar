package com.gorzio.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;


import android.util.Log;

import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.execution.*;


public class SystembarPlugin extends CordovaPlugin {

	private static final String TAG = "Systembar";

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("show")) {

			this.showSystemBar();
            callbackContext.success();

            return true;

        } else {
            
			this.hideSystemBar();
            callbackContext.success();
            return true;

        }
    }
	

    protected void showSystemBar() {
        String commandStr = "am startservice -n com.android.systemui/.SystemUIService";
        runAsRoot(commandStr);
        Log.d(TAG, ".show() executed");
    }

    protected void hideSystemBar() {
        try {
            String ProcID = "42";
            String commandStr = "service call activity " + ProcID + " s16 com.android.systemui";
            runAsRoot(commandStr);
            Log.d(TAG, ".hide() executed");
        } catch (Exception e) {
            // something went wrong, deal with it here
        }
    }


    private void runAsRoot(String commandStr) {
        try {
            CommandCapture command = new CommandCapture(0, commandStr);
            RootTools.getShell(true).add(command).waitForFinish();
        } catch (Exception e) {
            // something went wrong, deal with it here
        }
    }
}
