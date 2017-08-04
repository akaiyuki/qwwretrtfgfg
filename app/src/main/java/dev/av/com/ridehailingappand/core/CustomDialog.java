package dev.av.com.ridehailingappand.core;

import android.app.Dialog;
import android.view.Window;

import dev.av.com.ridehailingappand.R;

/**
 * Created by CodeSyaona on 27/07/2017.
 */

public class CustomDialog {

    public static void showDestinationDialog(BaseActivity baseActivity){
        final Dialog dialog = new Dialog(baseActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_destination);

        dialog.show();
    }

}
