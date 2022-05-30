package com.example.employeemanager.ui.manageAdmin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.employeemanager.R;
import com.example.employeemanager.ui.activity.LoginActivity;
import com.example.employeemanager.ui.activity.UpdateAdminActivity;

public class ManageAdminFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_manage_admin, container, false);
        //Get Ids of all the required fields
        //final TextView txtDeleteAccount = root.findViewById(R.id.txtDeleteAccount);
        final Button btnUpdateAdmin = root.findViewById(R.id.btnUpdateAdmin);
       // txtDeleteAccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setTitle("Are you sure you want to delete your account?");
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent intent = new Intent(getActivity(), LoginActivity.class);
//                        startActivity(intent);
//                        //remove all the intermediate activity
//                        getActivity().finishAffinity();
//                    }
//                });

//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//                final AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });

        //Click on update admin profile
        btnUpdateAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open update admin profile screen
                Intent intent = new Intent(getActivity(), UpdateAdminActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}