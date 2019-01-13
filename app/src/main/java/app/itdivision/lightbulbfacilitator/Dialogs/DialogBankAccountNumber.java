package app.itdivision.lightbulbfacilitator.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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

public class DialogBankAccountNumber  extends AppCompatDialogFragment {

    EditText bankAccNum;
    DialogBankAccountNumber.DialogBankAccountNumberListener listener;
    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());

    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_bankaccnumber, null);

        builder.setView(view);
        builder.setTitle("Change Bank Account Number");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newBankAccNum = bankAccNum.getText().toString();
                ActiveIdPassing activeIdPassing = ActiveIdPassing.getInstance();
                int id = activeIdPassing.getActiveId();
                databaseAccess.open();
                databaseAccess.changeBankAccountNumber(newBankAccNum, id);
                databaseAccess.close();
                Toast.makeText(getActivity(), "Bank Account Number Changed!", Toast.LENGTH_SHORT).show();
                listener.applyTextsBankAccountNumber(newBankAccNum);
            }
        });
        bankAccNum = view.findViewById(R.id.editBankAccountNumber);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DialogBankAccountNumber.DialogBankAccountNumberListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    public interface DialogBankAccountNumberListener{
        void applyTextsBankAccountNumber(String bankAccountNumber);
    }
}
