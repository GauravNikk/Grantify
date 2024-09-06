package gaurav.test.applicationpermission;
import android.app.Application;

public class MyApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();

    }

    public static String[] getAllPermissions() {
        final String[] LIST_PERMISSIONS = new String[]{
                "android.permission.INTERNET",
                "android.permission.READ_MEDIA_IMAGES",
                "android.permission.READ_MEDIA_VIDEO",
                "android.permission.READ_PHONE_STATE"
        };

        return LIST_PERMISSIONS;
    }
}
