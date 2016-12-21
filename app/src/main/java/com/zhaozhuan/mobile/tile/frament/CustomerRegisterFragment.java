package com.zhaozhuan.mobile.tile.frament;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhaozhuan.mobile.tile.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class CustomerRegisterFragment extends Fragment {

    public CustomerRegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}
