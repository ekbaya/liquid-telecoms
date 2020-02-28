package com.example.liquidscholarke.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liquidscholarke.ChatActivity;
import com.example.liquidscholarke.R;
import com.example.liquidscholarke.TheirProfileActivity;
import com.example.liquidscholarke.UserInfoActivity;
import com.example.liquidscholarke.models.ModelUsers;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.MyHolder> {

    Context context;
    List<ModelUsers> usersList;

    //constractor


    public AdapterUser(Context context, List<ModelUsers> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //inflate layout(row_users.xml)
        View view = LayoutInflater.from(context).inflate(R.layout.row_users, viewGroup, false);


        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        // get data
        final String hisUID = usersList.get(i).getUid();
        String userImage = usersList.get(i).getImage();
        String userName = usersList.get(i).getName();
        final String userEmail = usersList.get(i).getEmail();

        //set data
        myHolder.mNameTv.setText(userName);
        myHolder.mEmailTv.setText(userEmail);

        try {
            Picasso.get().load(userImage)
                    .placeholder(R.drawable.ic_default_img)
                    .into(myHolder.mAvatarIv);

        }catch (Exception e){

        }
        // Handling items clicks
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setItems(new String[]{"Profile", "Chat", "About"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==0){
                            //profile clicked
                            /*clicked to go to TheirProfileActivity with uid, this uid is of clicked user
                             * which will be used to show user specific data/posts*/
                            Intent intent = new Intent(context, TheirProfileActivity.class);
                            intent.putExtra("uid", hisUID);
                            context.startActivity(intent);
                        }
                        if (which==1){
                            //chat clicked
                            /*Click user from list to start chatting/messages
                             * start activity by putting UID of receiver
                             * we will use that UID to identity the user we are gonna chat*/

                            Intent intent= new Intent(context, ChatActivity.class);
                            intent.putExtra("hisUid",hisUID);
                            context.startActivity(intent);

                        }
                        if (which==2){
                            //About clicked
                            /*clicked to go to UserInfoActivity with uid, this uid is of clicked user
                             * which will be used to show user specific Info*/
                            Intent intent= new Intent(context, UserInfoActivity.class);
                            intent.putExtra("uid",hisUID);
                            context.startActivity(intent);
                        }
                    }
                });
                builder.create().show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    //view holder class
    class MyHolder extends RecyclerView.ViewHolder{

        ImageView mAvatarIv;
        TextView mNameTv, mEmailTv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //init views
            mAvatarIv = itemView.findViewById(R.id.avatarIv);
            mNameTv = itemView.findViewById(R.id.nameTv);
            mEmailTv = itemView.findViewById(R.id.emailTv);
        }
    }
}
