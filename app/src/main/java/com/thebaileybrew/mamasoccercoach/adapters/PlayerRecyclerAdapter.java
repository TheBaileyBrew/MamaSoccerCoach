package com.thebaileybrew.mamasoccercoach.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thebaileybrew.mamasoccercoach.R;
import com.thebaileybrew.mamasoccercoach.models.Player;

import java.util.List;

public class PlayerRecyclerAdapter extends RecyclerView.Adapter<PlayerRecyclerAdapter.ViewHolder>{

    private final LayoutInflater layoutInflater;
    private List<Player> playerList;


    final private PlayerRecyclerClickListener playerRecyclerClickListener;

    public interface PlayerRecyclerClickListener {
        void onClick(View view, Player player);
    }

    public PlayerRecyclerAdapter(Context context, List<Player> playerList, PlayerRecyclerClickListener listener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.playerList = playerList;
        this.playerRecyclerClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.player_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Player currentPlayer = playerList.get(position);

        holder.playerFirstName.setText(currentPlayer.getmPlayerFirstName());
        holder.playerLastName.setText(currentPlayer.getmPlayerLastName());
        holder.playerNumber.setText(currentPlayer.getmPlayerNumber());

    }

    @Override
    public int getItemCount() {
        if (playerList == null) {
            return 0;
        } else {
            return playerList.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView playerFirstName;
        final TextView playerLastName;
        final TextView playerNumber;

        private ViewHolder(View playerView) {
            super(playerView);
            playerFirstName = playerView.findViewById(R.id.player_first_name);
            playerLastName = playerView.findViewById(R.id.player_last_name);
            playerNumber = playerView.findViewById(R.id.player_number);
        }

        @Override
        public void onClick(View v) {
            Player currentPlayer = playerList.get(getAdapterPosition());
            playerRecyclerClickListener.onClick(v, currentPlayer);
        }
    }
}
