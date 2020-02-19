package com.xsz.workbook;

import android.text.TextUtils;
import android.widget.Toast;

public class BasicMethods {

    public static boolean emailFormat(String email) {
        boolean tag = true;
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            tag = false;
        }
        return tag;
    }

}
