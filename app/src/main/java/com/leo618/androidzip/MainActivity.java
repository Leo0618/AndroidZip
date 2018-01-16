package com.leo618.androidzip;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.leo618.splashpermissionsauth.SplashAuthUI;
import com.leo618.zip.IZipCallback;
import com.leo618.zip.ZipManager;

import java.io.File;
import java.util.ArrayList;

/**
 * function:压缩解压测试
 *
 * <p>
 * Created by Leo on 2018/1/16.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        authPermission();
    }

    private String dir_path        = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aaa";
    private String dir_zip_path    = dir_path + "/zip_files/";
    private String unzip_file_path = dir_path + "/zipFile.zip";
    private File   file1           = new File(dir_path + "/图片.png");
    private File   file2           = new File(dir_path + "/123.mp4");


    public void zip(View view) {
        if (!authPermission()) return;
        ArrayList<File> files = new ArrayList<>();
        files.add(file1);
        files.add(file2);
        ZipManager.zip(files, unzip_file_path, new IZipCallback() {
            @Override
            public void onStart() {
                loadingShow(-1);
            }

            @Override
            public void onProgress(int percentDone) {
                loadingShow(percentDone);
            }

            @Override
            public void onFinish(boolean success) {
                loadingHide();
                toast(success ? "成功" : "失败");
            }
        });
    }

    public void unZip(View view) {
        if (!authPermission()) return;
        ZipManager.unzip(unzip_file_path, dir_zip_path, new IZipCallback() {
            @Override
            public void onStart() {
                loadingShow(-1);
            }

            @Override
            public void onProgress(int percentDone) {
                loadingShow(percentDone);
            }

            @Override
            public void onFinish(boolean success) {
                loadingHide();
                toast(success ? "成功" : "失败");
            }
        });
    }

    ///------Progress Loading
    private ProgressDialog mLoading;

    private void loadingShow(int percent) {
        if (mLoading == null) {
            mLoading=  new ProgressDialog(this);
            mLoading.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mLoading.setMax(100);
        }
        if (percent > 0) {
            mLoading.setProgress(percent);
            mLoading.setMessage(String.valueOf(percent) + "%");
        }
        if (!mLoading.isShowing()) mLoading.show();
    }

    private void loadingHide() {
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismiss();
            mLoading = null;
        }
    }

    ///------Toast
    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    ///------Permissions
    private boolean hasPermission = false;

    private boolean authPermission() {
        if (hasPermission) return true;
        SplashAuthUI.launch(MainActivity.this, 100, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        });
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        hasPermission = (requestCode == 100) && (resultCode == RESULT_OK);
    }
}
