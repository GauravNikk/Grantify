package gaurav.test.applicationpermission;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import gauravnikk.grantify.GrantifyManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        GrantifyManager.getInstance().requestPermissions(MainActivity.this, MyApplication.getAllPermissions(), new GrantifyManager.PermissionResultCallback() {
            @Override
            public void onPermissionsGranted() {

                // Your Method();
            }

            @Override
            public void onPermissionsDenied() {
                // Permissions denied, handle accordingly
                Toast.makeText(MainActivity.this, "Permissions are required to create file!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}