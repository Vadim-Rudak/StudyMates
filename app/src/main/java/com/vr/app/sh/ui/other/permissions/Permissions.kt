package com.vr.app.sh.ui.other.permissions

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import com.vr.app.sh.R
import com.vr.app.sh.app.PERMISSIONS
import com.vr.app.sh.ui.other.UseAlert.Companion.permissionMsg
import com.vr.app.sh.ui.other.customAlert.permissionAlert

class Permissions {

    companion object{
        fun checkPermissions(fragmentManager: FragmentManager,context: Context){
            for (i in PERMISSIONS.indices){
                if(ActivityCompat.checkSelfPermission(context, PERMISSIONS[i]) != PackageManager.PERMISSION_GRANTED){
                    when(i){
                        0->{
                            //show alert to set memory permission
                            permissionMsg(
                                fragmentManager = fragmentManager,
                                id=0,
                                titel = context.getString(R.string.titel_memory),
                                info = context.getString(R.string.info_memory),
                                nameAnim = "memory-permission.json"
                            )
                        }
                        1->{
                            //show alert to set camera permission
                            permissionMsg(
                                fragmentManager = fragmentManager,
                                id=1,
                                titel = context.getString(R.string.titel_camera),
                                info = context.getString(R.string.info_camera),
                                nameAnim = "permission-camera.json"
                            )
                        }
                    }
                }
            }

        }
    }
}