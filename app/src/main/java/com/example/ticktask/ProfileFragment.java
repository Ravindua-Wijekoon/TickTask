package com.example.ticktask;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileFragment extends Fragment {

    TextView textView_name1, textView_email, textView_name2;
    Button btn_logout;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "tickTask";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        textView_name1 = view.findViewById(R.id.name1);
        textView_name2 = view.findViewById(R.id.name2);
        textView_email = view.findViewById(R.id.email);
        btn_logout = view.findViewById(R.id.btn_logout);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String name = sharedPreferences.getString(KEY_NAME, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);

        if (name != null || email != null) {
            textView_name1.setText(name);
            textView_name2.setText(name);
            textView_email.setText(email);
        }

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear only the login flag
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(KEY_IS_LOGGED_IN, false); // Clear login flag
                editor.apply();
                Toast.makeText(getContext(), "Logout Successfully", Toast.LENGTH_SHORT).show();

                // Navigate to login activity
                Intent intent = new Intent(getActivity(), Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear the back stack
                startActivity(intent);
                getActivity().finish(); // Finish the current activity to prevent going back
            }
        });

        return view;
    }
}
