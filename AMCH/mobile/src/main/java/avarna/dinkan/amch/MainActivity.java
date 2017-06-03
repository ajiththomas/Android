package avarna.dinkan.amch;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.text.DateFormat;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    //
    private static final String TAG ="Not found.";
    String completeinformation ="Not found.";
    String pretty_good_string = "Not found.";
    String pretty_bad_string = "Not found.";
    String holycrap="Not found.";
    String holycrap_gone_bad="Not found.";

    public class MystifiedDinkoism
    {



        public String getCellInfo(CellInfo cellInfo)
        {
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            int SDK_INT = android.os.Build.VERSION.SDK_INT;
            if(SDK_INT>8)
            {
                StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                pretty_good_string="Not found.";
                try {
                    pretty_good_string= FindTheCrapCalledPublicIPAddress();

                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }


            if (cellInfo instanceof CellInfoGsm)
            {

                CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                CellIdentityGsm cellIdentityGsm = cellInfoGsm.getCellIdentity();


                completeinformation = "___________________________\n" + " TimeStamp: " + currentDateTimeString + "\n" +
                        "|| GSM CELL Info ||\n Cell Identity: " + cellIdentityGsm.getCid() + "\n" +
                        "|| Public IP address: " + pretty_good_string + "\n" + "|| Mobile Country Code: " + cellIdentityGsm.getMcc() + "\n" +
                        "|| Mobile Network Code: " + cellIdentityGsm.getMnc() + "\n" +
                        "|| Local Area: " + cellIdentityGsm.getLac() + "\n";
                PutTheStuffInTheFile(completeinformation);
                holycrap = "__";
                holycrap.equals(cellIdentityGsm.getCid());
            } else if (cellInfo instanceof CellInfoLte)
            {

                CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                CellIdentityLte cellIdentityLte = cellInfoLte.getCellIdentity();

                completeinformation = "___________________________\n" + " TimeStamp: " + currentDateTimeString + "\n" +
                        "|| LTE CELL Info ||\n Cell Identity: " + cellIdentityLte.getCi() + "\n" +
                        "|| Public IP address: " + pretty_good_string + "\n" + "|| Mobile Country Code: " + cellIdentityLte.getMcc() + "\n" +
                        "|| Mobile Network Code: " + cellIdentityLte.getMnc() + "\n" +
                        "|| Physical CELL: " + cellIdentityLte.getPci() + "\n" +
                        "|| Tracking Area Code: " + cellIdentityLte.getTac() + "\n";
                PutTheStuffInTheFile(completeinformation);
                holycrap = "__";
                holycrap.equals(cellIdentityLte.getCi());


            }
            else if (cellInfo instanceof CellInfoWcdma)
            {

                CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                CellIdentityWcdma cellIdentityWcdma = cellInfoWcdma.getCellIdentity();

                completeinformation = "___________________________\n" + " TimeStamp: " + currentDateTimeString + "\n" +
                        "|| WCDMA CELL Info ||\n Cell Identity: " + cellIdentityWcdma.getCid() + "\n" +
                        "|| Public IP address: " + pretty_good_string + "\n" + "|| Mobile Country Code: " + cellIdentityWcdma.getMcc() + "\n" +
                        "|| Mobile Network Code: " + cellIdentityWcdma.getMnc() + "\n" +
                        "|| Local Area: " + cellIdentityWcdma.getLac() + "\n";
                PutTheStuffInTheFile(completeinformation);
                holycrap = "__";
                holycrap.equals(cellIdentityWcdma.getCid());
            }

            return completeinformation;
        }


    }

///




    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS=0;
    private boolean CheckIfTHESushiChefHasEnoughPermissions()
    {
        int loc= ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION);
        int loc2= ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int loc3= ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        int loc4= ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE);
        int loc5= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int loc6= ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (loc != PackageManager.PERMISSION_GRANTED)
        {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if (loc2 !=PackageManager.PERMISSION_GRANTED)
        {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (loc3 !=PackageManager.PERMISSION_GRANTED)
        {
            listPermissionsNeeded.add(Manifest.permission.INTERNET);
        }
        if (loc4 !=PackageManager.PERMISSION_GRANTED)
        {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_NETWORK_STATE);
        }
        if (loc5 !=PackageManager.PERMISSION_GRANTED)
        {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (loc6 !=PackageManager.PERMISSION_GRANTED)
        {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if(!listPermissionsNeeded.isEmpty())
        {

            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }


//

    private void DinkanPrintsNetworkInfo()
    {
        MystifiedDinkoism MystifiedDinkoism = new MystifiedDinkoism();
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        List <CellInfo> cellInfos = tm.getAllCellInfo();
        String completeinformation = MystifiedDinkoism.getCellInfo(cellInfos.get(0));

        TextView tv = (TextView) findViewById(R.id.TextOutput);
        tv.setText(completeinformation);
    }

    private String FindTheCrapCalledPublicIPAddress() throws IOException
    {
        URL connection = new URL("http://checkip.amazonaws.com/");
        URLConnection con= connection.openConnection();
        String str = "Not found.";
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        str = reader.readLine();
        return str;
    }

    private void PutTheStuffInTheFile(String s)
    {
        try
        {
            File damnFile = new File("/sdcard/NetworkInfo.txt");
            if(damnFile.exists()==false)
            {
                damnFile.createNewFile();
            }
            FileOutputStream HellOut= new FileOutputStream(damnFile, true);
            OutputStreamWriter HellOutWriter = new OutputStreamWriter(HellOut);
            HellOutWriter.append(s);
            HellOutWriter.close();
            HellOut.close();
            Toast.makeText(getBaseContext(),"Done writing Timestamp",Toast.LENGTH_SHORT).show();
        } catch (Exception e)
        {
            Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }








    //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //*

        CheckIfTHESushiChefHasEnoughPermissions();
        MystifiedDinkoism MystifiedDinkoism= new MystifiedDinkoism();
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        List<CellInfo> cellInfos = tm.getAllCellInfo();
        String completeinformation = MystifiedDinkoism.getCellInfo(cellInfos.get(0));
        DinkanPrintsNetworkInfo();


        Button button = (Button) findViewById(R.id.refresh);
        button.setOnClickListener(new View.OnClickListener(){
                                      public void onClick(View v){
                                          DinkanPrintsNetworkInfo();
                                      }
                                  }

        );

        final Handler handler = new Handler();
        Timer timer2 = new Timer();
        TimerTask testing = new TimerTask(){
            public void run() {
                handler.post(new Runnable(){
                    public void run(){
                        DinkanPrintsNetworkInfo();
                    }
                });
            }

        };
        timer2.schedule(testing,10000);

    }

    //*


    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG,"On Destroy....");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG,"On Pause....");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG,"On Restart....");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG,"On Resume....");
    }


}
