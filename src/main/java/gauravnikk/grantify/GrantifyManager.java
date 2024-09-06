package gauravnikk.grantify;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class GrantifyManager {

    private static GrantifyManager instance;
    private PermissionResultCallback callback;

    public interface PermissionResultCallback {
        void onPermissionsGranted();
        void onPermissionsDenied();
    }

    private GrantifyManager() {}

    public static GrantifyManager getInstance() {
        if (instance == null) {
            instance = new GrantifyManager();
        }
        return instance;
    }

    public void requestPermissions(Activity activity, String[] permissions, PermissionResultCallback callback) {
        this.callback = callback;
        List<String> listPermissionsNeeded = new ArrayList<>();

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[0]), Grantify.PERMISSION_REQUEST_CODE);
        } else {
            callback.onPermissionsGranted();
        }
    }

    public void handlePermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Grantify.PERMISSION_REQUEST_CODE) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
            if (allGranted) {
                if (callback != null) callback.onPermissionsGranted();
            } else {
                if (callback != null) callback.onPermissionsDenied();
            }
        }
    }
}
