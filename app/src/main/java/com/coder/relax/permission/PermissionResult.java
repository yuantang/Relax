package com.coder.relax.permission;

/**
 * Created by TUS on 2017/9/25.
 */

public interface PermissionResult {
    void onGranted();

    void onDenied();
}
