package com.json.viewmodel;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class MasterFragment extends Fragment {
    public static String TAG = "MasterFragment:";

    private SharedViewModel model;

    private Button mAddDataButton;
    private TextView mInfoTextView;
    private TextView mDataEditView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.master_fragment, container, false);
        mAddDataButton = view.findViewById(R.id.master_add_data_button);
        mInfoTextView = view.findViewById(R.id.master_info_text_view);
        mDataEditView = view.findViewById(R.id.master_input_edit_text);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        mAddDataButton.setOnClickListener(item -> {
            Item inputItem = new Item();
            inputItem.setName(mDataEditView.getText().toString());
            model.select(inputItem);
            mDataEditView.setText("");
        });

        model.getSelected().observe(getViewLifecycleOwner(), item -> {
            Log.d(TAG, "[onViewCreated] getName = " + item.getName());
            mInfoTextView.setText(item.getName());
        });
    }
}
