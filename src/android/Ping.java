
package org.tiste.cordova.ping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Ping extends CordovaPlugin {
  public static final String TAG = "Ping";

  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
  }

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if ("getPingInfo".equals(action)) {
      this.ping(args, callbackContext);
      return true;
    }
    return false;
  }

  private void ping(JSONArray args, CallbackContext callbackContext) {
    try {
      if (args != null && args.length() > 0) {
        JSONArray resultList = new JSONArray();
        int length = args.length();
        for (int index = 0; index < length; index++) {
          String ip = args.getString(index);
          double result = doPing(ip);
          JSONObject r = new JSONObject();
          r.put("ip", ip);
          if (result > 0) {
            r.put("ping", "success");
            r.put("avg", result);
            resultList.put(r);
            System.out.println("success \n");
          } else {
            r.put("ping", "timeout");
            r.put("avg", 0);
            resultList.put(r);
            System.out.println("timeout \n");
          }
        }
        callbackContext.success(resultList);
      } else {
        callbackContext.error("Error occurred");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private double doPing(String ip){
    System.out.println("doPing \n");
    System.out.println(ip + "\n");
    String inputLine = "";
    double avgRtt = 0;
    Runtime runtime = Runtime.getRuntime();
    try {
      Process mIpAddrProcess = runtime.exec("/system/bin/ping -c 1 " + ip);
      int mExitValue = mIpAddrProcess.waitFor();
      if (mExitValue == 0) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mIpAddrProcess.getInputStream()));
        inputLine = bufferedReader.readLine();
        while ((inputLine != null)) {
          if (inputLine.length() > 0 && inputLine.contains("avg")) {
            break;
          }
          inputLine = bufferedReader.readLine();
        }
        String afterEqual = inputLine.substring(inputLine.indexOf("="), inputLine.length()).trim();
        String afterFirstSlash = afterEqual.substring(afterEqual.indexOf('/') + 1, afterEqual.length()).trim();
        String strAvgRtt = afterFirstSlash.substring(0, afterFirstSlash.indexOf('/'));
        avgRtt = Double.valueOf(strAvgRtt);
      } else {
        avgRtt = 0;
      }
    } catch (InterruptedException ignore) {
      ignore.printStackTrace();
      System.out.println(" Exception:" + ignore);
    }  catch (IOException e) {
      e.printStackTrace();
      System.out.println(" Exception:" + e);
    }
    return avgRtt;
  }
}
