package com.json.viewmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

/**
 * https://developer.android.com/topic/libraries/architecture/viewmodel#java
 * https://developer.android.com/jetpack/androidx/releases/lifecycle#declaring_dependencies
 */
public class MainActivity extends AppCompatActivity {

    private MasterFragment mMasterFragment;
    private DetailFragment mDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button masterFragmentButton = findViewById(R.id.main_master_fragment_button);
        Button detailFragmentButton = findViewById(R.id.main_detail_fragment_button);
        mMasterFragment = new MasterFragment();
        mDetailFragment = new DetailFragment();

        masterFragmentButton.setOnClickListener(View -> changeFragment(mMasterFragment));
        detailFragmentButton.setOnClickListener(View -> changeFragment(mDetailFragment));

        // 최초 화면에 표시할 Fragment 화면
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, mMasterFragment, MasterFragment.TAG).commit();
        }
    }

    // 버튼을 누를 경우 선택한 Fragment 변경
    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }
}