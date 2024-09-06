/*
 * Copyright (c) 2024 Gaurav_Nikk
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package gauravnikk.grantify;

import android.Manifest;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

public class Grantify {
    public static final int PERMISSION_REQUEST_CODE = 10;

    /**
     * Method to return required permissions based on OS version and user input.
     * @param permissions List of permissions requested by the user.
     * @return Adjusted permissions based on the Android version.
     */
    public static String[] getRequiredPermissions(String[] permissions) {
        List<String> finalPermissions = new ArrayList<>();

        for (String permission : permissions) {
            switch (permission) {
                case Manifest.permission.READ_EXTERNAL_STORAGE:
                case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                    handleStoragePermissions(finalPermissions);
                    break;

                case Manifest.permission.ACCESS_BACKGROUND_LOCATION:
                    handleLocationPermissions(finalPermissions);
                    break;

                case Manifest.permission.READ_MEDIA_IMAGES:
                case Manifest.permission.READ_MEDIA_VIDEO:
                case Manifest.permission.READ_MEDIA_AUDIO:
                    handleMediaPermissions(finalPermissions);
                    break;

                case Manifest.permission.FOREGROUND_SERVICE:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        finalPermissions.add(Manifest.permission.FOREGROUND_SERVICE);
                    }
                    break;

                case Manifest.permission.BLUETOOTH_CONNECT:
                case Manifest.permission.BLUETOOTH_SCAN:
                case Manifest.permission.BLUETOOTH_ADVERTISE:
                    handleBluetoothPermissions(finalPermissions);
                    break;

                case Manifest.permission.POST_NOTIFICATIONS:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        finalPermissions.add(Manifest.permission.POST_NOTIFICATIONS);
                    }
                    break;

                // Add other permissions that require special handling

                default:
                    finalPermissions.add(permission);
                    break;
            }
        }

        return finalPermissions.toArray(new String[0]);
    }

    private static void handleStoragePermissions(List<String> finalPermissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            finalPermissions.add(Manifest.permission.READ_MEDIA_IMAGES);
            finalPermissions.add(Manifest.permission.READ_MEDIA_VIDEO);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            finalPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        } else {
            finalPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            finalPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    private static void handleLocationPermissions(List<String> finalPermissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            finalPermissions.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            finalPermissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            finalPermissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
    }

    private static void handleMediaPermissions(List<String> finalPermissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            finalPermissions.add(Manifest.permission.READ_MEDIA_IMAGES);
            finalPermissions.add(Manifest.permission.READ_MEDIA_VIDEO);
            // AUDIO permission can be added based on user needs
        } else {
            finalPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    private static void handleBluetoothPermissions(List<String> finalPermissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            finalPermissions.add(Manifest.permission.BLUETOOTH_CONNECT);
            finalPermissions.add(Manifest.permission.BLUETOOTH_SCAN);
            finalPermissions.add(Manifest.permission.BLUETOOTH_ADVERTISE);
        }
    }
}
