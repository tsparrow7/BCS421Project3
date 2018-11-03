package com.example.tjgaming.individualproject3.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tjgaming.individualproject3.R;

public class DifficultyFragment extends Fragment implements View.OnClickListener {

    public static String TAG = "DifficultyFragment";

    OnDifficultyChosenListener mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_difficulty, container, false);

        root.findViewById(R.id.easy_btn).setOnClickListener(this);
        root.findViewById(R.id.hard_btn).setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
        mCallback.onDifficultyChosen(view);
    }

    public interface OnDifficultyChosenListener {
        void onDifficultyChosen(View view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnDifficultyChosenListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + "must implement OnDifficultyChosenListener");
        }
    }
}
