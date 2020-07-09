package com.example.loadernasync;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

public class myasyncClass extends AsyncTask<Void,Integer,String> {

    Context cxt;
    ProgressDialog pd;

    void AsyncTask(Context cxt)
    {
        this.cxt=cxt;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd= new ProgressDialog(cxt);
        pd.setTitle("Downloading..");
        pd.setMessage("Please Wait..");
        pd.setMax(10);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setButton(ProgressDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                pd.dismiss();
            }
        });
    pd.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try{
            for(int i=0;i<10;i++)
            {
                Thread.sleep(1000);
                Log.i("Thread sleep ", String.valueOf(i));
                publishProgress(i);
            }
            return "success";
        }
        catch(Exception e)
        {
            Log.i("Exception ",e.getMessage());
            return "Failure";
        }
        //return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(cxt,s,Toast.LENGTH_SHORT).show();
        pd.dismiss();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int Val=values[0];
        pd.setProgress(Val);
    }
}
