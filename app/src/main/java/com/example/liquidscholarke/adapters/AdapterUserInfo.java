package com.example.liquidscholarke.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liquidscholarke.R;
import com.example.liquidscholarke.models.ModelUserInfo;

import java.util.List;

public class AdapterUserInfo extends RecyclerView.Adapter<AdapterUserInfo.MyHolder>{

    Context context;
    List<ModelUserInfo> infoList;

    //constractor


    public AdapterUserInfo(Context context, List<ModelUserInfo> infoList) {
        this.context = context;
        this.infoList = infoList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //inflate layout(row_user_info.xml)
        View view = LayoutInflater.from(context).inflate(R.layout.row_user_info, viewGroup, false);


        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        //get data
        String name = infoList.get(i).getName();
        String email = infoList.get(i).getEmail();
        String phone = infoList.get(i).getPhone();
        String career = infoList.get(i).getCareer();
        String university = infoList.get(i).getUniversity();
        String course = infoList.get(i).getCourse();
        String country = infoList.get(i).getCountry();
        String sholarshipType = infoList.get(i).getScholarshipType();
        String profile = infoList.get(i).getProfile();

        //set data to views
        myHolder.userNameTv.setText("Full name:   "+name);
        myHolder.userPhoneTv.setText("Phone :     "+phone);
        myHolder.userEmailTv.setText("Email :     "+email);
        myHolder.userCareerTv.setText("Career :   "+career);
        myHolder.userUniversityTv.setText("University :  "+university);
        myHolder.userCourseTv.setText("Course :    "+course);
        myHolder.userCountryTv.setText("Country :   "+country);
        myHolder.userScholarshipTypeTv.setText("Scholarship Type  :  "+sholarshipType);
        myHolder.userProfileTv.setText("Profile:    "+profile);


    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    //View Holder class
    class MyHolder extends RecyclerView.ViewHolder {
        //view from row_user_info.xml
        TextView userNameTv, userEmailTv, userPhoneTv, userCareerTv, userUniversityTv, userCourseTv, userCountryTv, userScholarshipTypeTv, userProfileTv;


        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //init views
            userNameTv = itemView.findViewById(R.id.userNameTv);
            userEmailTv = itemView.findViewById(R.id.userEmailTv);
            userPhoneTv = itemView.findViewById(R.id.userPhoneTv);
            userCareerTv = itemView.findViewById(R.id.userCareerTv);
            userUniversityTv = itemView.findViewById(R.id.userUniversityTv);
            userCourseTv = itemView.findViewById(R.id.userCourseTv);
            userCountryTv = itemView.findViewById(R.id.userCountryTv);
            userScholarshipTypeTv = itemView.findViewById(R.id.userScholarshipTypeTv);
            userProfileTv = itemView.findViewById(R.id.userProfileTv);
        }
    }
}
