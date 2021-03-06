package com.sao.mobile.sao.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sao.mobile.sao.R;
import com.sao.mobile.sao.ui.activity.BarActivity;
import com.sao.mobile.sao.ui.activity.BarInfoActivity;
import com.sao.mobile.saolib.entities.Bar;
import com.sao.mobile.saolib.entities.User;
import com.sao.mobile.saolib.entities.api.FriendBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seb on 07/03/2017.
 */

public class FriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FriendBar> mItems;
    private Context mContext;

    private LayoutInflater mLayoutInflater;

    public FriendAdapter(Context context, List<FriendBar> items) {
        this.mContext = context;
        this.mItems = items != null ? items : new ArrayList<FriendBar>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }

        View view = mLayoutInflater.inflate(R.layout.item_friend, parent, false);
        return new FriendAdapter.FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final FriendAdapter.FriendViewHolder friendViewHolder = (FriendAdapter.FriendViewHolder) holder;
        final FriendBar userFriend = (FriendBar) mItems.get(position);

        friendViewHolder.friendName.setText(userFriend.getFriend().getName()+' ');
        friendViewHolder.friendLocalization.setText(userFriend.getBar().getName());

        int avatarSize = mContext.getResources().getDimensionPixelSize(R.dimen.user_profile_avatar_size);
        Picasso.with(mContext).load(userFriend.getFriend().getThumbnail())
                .resize(avatarSize, avatarSize)
                .centerCrop()
                .into(friendViewHolder.friendThumbnail);

        friendViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToBarDetail(friendViewHolder, userFriend.getBar());
            }
        });
    }

    private void goToBarDetail(FriendAdapter.FriendViewHolder friendViewHolder, Bar bar) {
        Activity activity = (Activity) mContext;
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                new Pair(friendViewHolder.friendThumbnail, BarActivity.IMAGE_TRANSITION_NAME)
        );

        Intent intent = new Intent(activity, BarInfoActivity.class);
        intent.putExtra(BarActivity.BAR_EXTRA, bar);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addListItem(List<FriendBar> users) {
        mItems = users;
        notifyDataSetChanged();
    }

    private static class FriendViewHolder extends RecyclerView.ViewHolder {
        ImageView friendThumbnail;
        TextView friendName;
        TextView friendLocalization;

        FriendViewHolder(View view) {
            super(view);
            friendThumbnail = (ImageView) view.findViewById(R.id.friendThumbnail);
            friendName = (TextView) view.findViewById(R.id.friendName);
            friendLocalization = (TextView) view.findViewById(R.id.friendLocalization);
        }
    }
}
