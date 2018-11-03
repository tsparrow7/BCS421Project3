package com.example.tjgaming.individualproject3.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tjgaming.individualproject3.R;
import com.example.tjgaming.individualproject3.controller.Database;
import com.example.tjgaming.individualproject3.model.Game;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ParentActivity extends AppCompatActivity {

    public TextView textView;
    CollectionReference mCollectionRef;
    FirebaseFirestore mFirestore;
    List<Game> gameList;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        db = new Database(getApplicationContext());

//        AsyncTask task = new GetChildStatisticsAsyncTask();
//        task.execute();

        mFirestore = FirebaseFirestore.getInstance();
        gameList = new ArrayList<>();
        textView = findViewById(R.id.parent_child_statistics);

        mCollectionRef = mFirestore.collection("Games" + "/" + db.getUserLoggedIn().getUid() + "/" + "Games Beaten");

        mCollectionRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                    Game game = queryDocumentSnapshot.toObject(Game.class);
                    gameList.add(game);
                }
                textView.setText(gameList.toString());
            }
        });
    }

//    public void initViews() {
//        textView = findViewById(R.id.parent_child_statistics);
//    }
//
//    public class GetChildStatisticsAsyncTask extends AsyncTask {
//
//        @Override
//        protected Object doInBackground(Object[] objects) {
//
//            db.getChildStatistics();
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Object o) {
//            textView.setText(db.getGameList().toString());
//        }
//    }
}
