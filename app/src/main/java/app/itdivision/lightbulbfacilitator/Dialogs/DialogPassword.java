package app.itdivision.lightbulbfacilitator.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import app.itdivision.lightbulbfacilitator.Database.DatabaseAccess;
import app.itdivision.lightbulbfacilitator.Instance.ActiveIdPassing;
import app.itdivision.lightbulbfacilitator.R;

public class DialogPassword extends AppCompatDialogFragment {
    EditText password;
    EditText confpass;
    EditText oldPass;
    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
    ActiveIdPassing activeIdPassing = ActiveIdPassing.getInstance();

    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_password, null);

        builder.setView(view);
        builder.setTitle("Change Username");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String oldp = oldPass.getText().toString();
                int id = activeIdPassing.getActiveId();
                databaseAccess.open();
                if(oldp.equals(databaseAccess.getOldPassword(id))){
                    String newPass = password.getText().toString();
                    String conf = confpass.getText().toString();
                    if(newPass.equals(conf) && newPass.length() > 7){
                        databaseAccess.changePassword(newPass, id);
                        databaseAccess.close();
                        Toast.makeText(getActivity(), "Password Changed!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "Passwords not match or is less than 8 characters", Toast.LENGTH_LONG).show();
                        databaseAccess.close();
                    }
                }else{
                    Toast.makeText(getActivity(), "Wrong Old Password!", Toast.LENGTH_LONG).show();
                    databaseAccess.close();
                }
            }
        });

        oldPass = view.findViewById(R.id.oldPassword);
        password = view.findViewById(R.id.editPassword);
        confpass = view.findViewById(R.id.editConfirmPassword);
        return builder.create();
    }
}
