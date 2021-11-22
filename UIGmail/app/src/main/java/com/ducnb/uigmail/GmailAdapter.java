package com.ducnb.uigmail;

import android.content.Context;
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
import com.ducnb.uigmail.data.Gmail;

import java.util.ArrayList;

public class GmailAdapter extends RecyclerView.Adapter<GmailAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Gmail> mGmails;

    public GmailAdapter(Context mContext, ArrayList<Gmail> mGmails) {
        this.mContext = mContext;
        this.mGmails = mGmails;
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
        Gmail gmail = mGmails.get(position);
        Glide.with(mContext).load(gmail.getmImage()).into(holder.mImageAvatar);
        holder.mTextName.setText(gmail.getmName());
        holder.mTextContents.setText(gmail.getmContents());
        holder.mTextDate.setText(gmail.getmDate().toString());
        holder.setItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
                    //handle long clik here
                    Toast.makeText(mContext, "Long Click: "+mGmails.get(position), Toast.LENGTH_SHORT).show();
                }
                else{
                    //handle click here
                    Toast.makeText(mContext, " "+mGmails.get(position), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGmails.size();
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
