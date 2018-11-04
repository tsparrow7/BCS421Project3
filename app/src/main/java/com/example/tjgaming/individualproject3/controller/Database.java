package com.example.tjgaming.individualproject3.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import com.example.tjgaming.individualproject3.R;
import com.example.tjgaming.individualproject3.model.Game;
import com.example.tjgaming.individualproject3.model.User;
import com.example.tjgaming.individualproject3.view.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TJ on 10/17/2018.
 */
public class Database {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore mFirestore;
    private DocumentReference mDocRef;
    private CollectionReference mCollectionRef;
    private Context context;
    private static final String TAG = "in DATABASE";
    private List<Game> gameList;


    public Database(Context context) {
        this.context = context;
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        gameList = new ArrayList<>();
    }

    public void login(User user) {

        mFirebaseAuth.signInWithEmailAndPassword(user.getEmail(),user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG,"User Login success");
                        } else {
                            Log.d(TAG,"User login not successful"
                                    + task.getException().getMessage());
                        }
                    }
                });
    }

    public void register(User user) {

        mFirebaseAuth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG,"User Creation success");
                        } else {
                            Log.d(TAG,"User creation not successful"
                                    + task.getException().getMessage());
                        }
                    }
                });
    }

    public void logout() {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    public void saveGame(Game game) {

        mDocRef = mFirestore.collection("Games")
                .document(getUserLoggedIn().getUid())
                .collection("Games Beaten")
                .document(game.getDifficulty().concat(game.getLevel()));

        Map<String, Object> beatenGame = new HashMap<>();
        beatenGame.put("difficulty",game.getDifficulty());
        beatenGame.put("level",game.getLevel());
        beatenGame.put("beaten",game.isComplete());

        mDocRef.set(beatenGame);
    }

    public FirebaseUser getUserLoggedIn() {
        return mFirebaseAuth.getCurrentUser();
    }

    public class AsyncChildStatsTask extends AsyncTask {

        Activity activity;

        public AsyncChildStatsTask(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            mCollectionRef = mFirestore.collection("Games" + "/" + getUserLoggedIn().getUid() + "/" + "Games Beaten");
            mCollectionRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots){
                        Game game = queryDocumentSnapshot.toObject(Game.class);

                        gameList.add(game);
                    }
                    ((TextView)activity.findViewById(R.id.parent_child_statistics)).setText(gameList.toString());
                }
            });
            return gameList;
        }
    }
}