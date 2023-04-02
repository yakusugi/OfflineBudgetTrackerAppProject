package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.myproject.offlinebudgettrackerappproject.data.CloudUserDao;
import com.myproject.offlinebudgettrackerappproject.dto.BudgetTrackerUserDto;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCloudFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCloudFragment extends Fragment {

    EditText enterEmail, enterUserId, enterPassword;
    Button loginButton, registerButton;
    BudgetTrackerUserDto budgetTrackerUserDto;
    CloudUserDao cloudUserDao;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCloudFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCloudFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCloudFragment newInstance(String param1, String param2) {
        AddCloudFragment fragment = new AddCloudFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_cloud, container, false);

        enterEmail = (EditText) view.findViewById(R.id.upload_login_email);
        enterPassword = (EditText) view.findViewById(R.id.upload_login_password);
        loginButton = (Button) view.findViewById(R.id.upload_login_btn);
        registerButton = (Button) view.findViewById(R.id.upload_register_btn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = enterEmail.getText().toString();
                String userPassword = enterPassword.getText().toString();
                budgetTrackerUserDto = new BudgetTrackerUserDto();
                budgetTrackerUserDto.setEmail(userEmail);
                budgetTrackerUserDto.setPassword(userPassword);
                cloudUserDao = new CloudUserDao(getActivity());

                try {
                    int result = cloudUserDao.logIn(budgetTrackerUserDto);
                    if (result == 1) {
                        Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.activity_add_container, new AddSyncCloudFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    } else {
                        Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_add_container, new AddCloudRegisterFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}