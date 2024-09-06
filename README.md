# Grantify

## Description

Grantify is an Android library designed to simplify the process of requesting and managing runtime permissions across various Android versions. It provides an easy-to-use API for handling permissions, ensuring that your app complies with the latest Android permission requirements and improving the user experience.

## Features

- **Support for Multiple Android Versions**: Handles permissions based on the Android version.
- **Simplified Permission Requests**: Easily request multiple permissions at once.
- **Callbacks for Permission Results**: Handle permissions granted or denied with a callback interface.
- **Easy Integration**: Simple setup and usage in your Android projects.

## Repository Link

You can find the source code and additional information on the [Grantify GitHub repository](https://github.com/yourusername/grantify).

## How to Use

### Step 1: Add Dependency

Include the following dependency in your `build.gradle` file:

```gradle
implementation 'com.example:grantify:1.0.0'
```

# Step 2: Initialize and Request Permissions

**Use the Grantify library to request permissions in your activity:**

```MainActivity.java
package com.example.grantifyexample;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.grantify.Grantify;
import com.example.grantify.GrantifyManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Request permissions using Grantify
        GrantifyManager.getInstance().requestPermissions(this, Grantify.getAllPermissions(), new GrantifyManager.PermissionResultCallback() {
            @Override
            public void onPermissionsGranted() {
                // Permissions granted, proceed with your method
                Toast.makeText(MainActivity.this, "Permissions granted!", Toast.LENGTH_SHORT).show();
                // Your method or functionality that requires permissions
                performAction();
            }

            @Override
            public void onPermissionsDenied() {
                // Permissions denied, handle accordingly
                Toast.makeText(MainActivity.this, "Permissions are required to proceed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void performAction() {
        // Your method or functionality that requires permissions
    }
}
```

