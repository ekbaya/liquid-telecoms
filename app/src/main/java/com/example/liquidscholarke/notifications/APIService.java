package com.example.liquidscholarke.notifications;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers({
            "Content-Type:application/json",
            "Authorization:key= AAAAJ2rbTek:APA91bGtenX6m4K9iao0PYY1wQrNNXqyxy8jUwUmQ2m6TckkTjsKDjvycLIpHI4vsCbO3rCySj_dj0Nu2zyKUZTEctWiAGu_R2m8ODZZg0LvE8gT434tac7al2kcPMux2xZGNo-uXj9-"
    })

    @POST("fcm/send")
    Call<Response> sendNotification(@Body Sender body);
}
