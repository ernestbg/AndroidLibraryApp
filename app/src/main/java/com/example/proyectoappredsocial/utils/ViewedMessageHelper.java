package com.example.proyectoappredsocial.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

import com.example.proyectoappredsocial.providers.AuthProvider;
import com.example.proyectoappredsocial.providers.UsersProvider;

import java.util.List;

public class ViewedMessageHelper {

    public static void updateOnline(boolean status, Context context) {
        UsersProvider usersProvider = new UsersProvider();
        AuthProvider authProvider = new AuthProvider();
        if (authProvider.getUid() != null) {
            if (isApplicationSentToBackground(context)) {
                usersProvider.updateOnline(authProvider.getUid(), status);
            } else if (status) {
                usersProvider.updateOnline(authProvider.getUid(), status);

            }
        }
    }

    public static boolean isApplicationSentToBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
