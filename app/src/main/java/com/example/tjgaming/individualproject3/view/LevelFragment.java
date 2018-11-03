package com.example.tjgaming.individualproject3.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tjgaming.individualproject3.R;

public class LevelFragment extends Fragment implements View.OnClickListener {

    public static String TAG = "LevelFragment";

    OnLevelChosenListener mCallback;
    OnResumeListener mResumed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View root = inflater.inflate(R.layout.fragment_level, container, false);

        root.findViewById(R.id.level_one).setOnClickListener(this);
        root.findViewById(R.id.level_two).setOnClickListener(this);
        root.findViewById(R.id.level_three).setOnClickListener(this);

       return root;
    }
    @Override
    public void onClick(View view) {
        if (mCallback != null){
            mCallback.onLevelChosen(view,
                    ((TextView)((RelativeLayout) view ).getChildAt(0)).getText().toString() );
        }
    }

    public interface OnLevelChosenListener {
        void onLevelChosen(View view, String level);
    }

    public interface OnResumeListener {
        void onResumed(View view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnLevelChosenListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + "must implement OnLevelChosenListener");
        }

        try {
            mResumed = (OnResumeListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + "must implement OnResumeListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mResumed != null){
            mResumed.onResumed(getView());
        }
    }
}
