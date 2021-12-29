package com.ducnb.uigmail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ducnb.uigmail.Interface.IItemClickListener;
import com.ducnb.uigmail.data.Users;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Users> mUsers;

    public UserAdapter(Context mContext, ArrayList<Users> mUsers) {
        this.mContext = mContext;
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from((mContext));
        View gmailView = inflater.inflate(R.layout.item_activity,parent,false);
        ViewHolder viewHolder = new ViewHolder(gmailView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users user = mUsers.get(position);
        Glide.with(mContext).load(Utils.BASE_URL+user.getAvatar().getPhoto()).into(holder.mImageAvatar);
        holder.mTextName.setText(user.getName());
        holder.mTextContents.setText(user.getEmail());
        holder.mTextDate.setText(user.getPhone());
        holder.setItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){

                }
                else{
                    Intent intent = new Intent(mContext, InfoActivity.class);
                    Log.d("TAG",mUsers.get(position).getEmail());
                    intent.putExtra("User", mUsers.get(position));
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private ImageView mImageAvatar;
        private TextView mTextName;
        private TextView mTextContents;
        private TextView mTextDate;

        private IItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageAvatar = itemView.findViewById(R.id.image_avatar);
            mTextName = itemView.findViewById(R.id.text_name);
            mTextContents = itemView.findViewById(R.id.gmail_contents);
            mTextDate = itemView.findViewById(R.id.date_time);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void setItemClickListener(IItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }
    }
}
