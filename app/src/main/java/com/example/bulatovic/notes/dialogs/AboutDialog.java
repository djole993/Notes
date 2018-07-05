package com.example.bulatovic.notes.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AboutDialog extends AlertDialog.Builder {
    public AboutDialog(Context context) {
        super(context);

        setTitle("About");
        setMessage("Djordje Bulatovic");
        setCancelable(false);

        setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }
    public AlertDialog prepereDialog(){
        AlertDialog dialog = create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
