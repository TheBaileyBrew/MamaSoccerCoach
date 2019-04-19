package com.thebaileybrew.mamasoccercoach.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thebaileybrew.mamasoccercoach.MamaSoccerCoach;
import com.thebaileybrew.mamasoccercoach.R;
import com.thebaileybrew.mamasoccercoach.adapters.PlayerRecyclerAdapter;
import com.thebaileybrew.mamasoccercoach.models.Player;

import java.util.ArrayList;
import java.util.List;

public class FragmentPlayerUpdate extends Fragment {
    public static final String TAG = FragmentPlayerUpdate.class.getSimpleName();


    private FirebaseDatabase mPlayerDatabase;
    private DatabaseReference mPlayerDatabaseReference;

    private RecyclerView playerRecyclerList;
    private PlayerRecyclerAdapter playerRecyclerAdapter;
    private List<Player> playerList = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlayerDatabase = FirebaseDatabase.getInstance();
        mPlayerDatabaseReference = mPlayerDatabase.getReference("Players");
        mPlayerDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //Update the Recycler
                Player newPlayer = dataSnapshot.getValue(Player.class);
                Log.e(TAG, "onDataChange: player name: " + newPlayer.getmPlayerFirstName());
                playerList.add(newPlayer);
                playerRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_player_update, container, false);
        Button addPlayerButton = inflateView.findViewById(R.id.add_player_button);
        playerRecyclerList = inflateView.findViewById(R.id.player_list);
        playerRecyclerAdapter = new PlayerRecyclerAdapter(MamaSoccerCoach.getContext(), playerList, new PlayerRecyclerAdapter.PlayerRecyclerClickListener() {
            @Override
            public void onClick(View view, Player player) {

            }
        });

        //Add RecyclerAdapter

        //Set Recycler Adapter
        playerRecyclerList.setAdapter(playerRecyclerAdapter);
        playerRecyclerList.setLayoutManager(new LinearLayoutManager(MamaSoccerCoach.getContext(),
                RecyclerView.VERTICAL, false));
        return inflateView;
    }
}
