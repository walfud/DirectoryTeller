package com.example.songdeming.directoryteller;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.File;


public class MainActivity extends ActionBarActivity {

    public static final String TAG = "MainActivity";

    private TextView mPath;
    private TextView mDesc;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPath = (TextView) findViewById(R.id.path);
        mDesc = (TextView) findViewById(R.id.desc);
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);

        //
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                File path;
                String desc = "";
                int api = 1;            // Api 最低需求.
                switch (checkedId) {

                    // Internal Storage
                    case R.id.getDataDirectory:
                        path = Environment.getDataDirectory();
                        desc = "数据目录";
                        break;

                    case R.id.getFilesDir:
                        path = getFilesDir();
                        desc = "程序数据目录";
                        break;

                    case R.id.getCacheDir:
                        path = getCacheDir();
                        desc = "程序缓存目录";
                        break;

                    case R.id.getDownloadCacheDirectory:
                        path = Environment.getDownloadCacheDirectory();
                        desc = "下载缓存内容目录";
                        break;

                    // External Storage
                    // Don't be confused by the word "external" here.
                    // These directories can better be thought as media/shared storage.
                    case R.id.getExternalStorageDirectory:
                        path = Environment.getExternalStorageDirectory();
                        desc = "共享目录. 但是这个目录很可能当前不能访问，比如这个目录被用户的PC挂载，或者从设备中移除，或者其他问题发生，你可以通过getExternalStorageState()来获取当前状态。";
                        break;

                    case R.id.Context_getExternalFilesDir:
                        path = getExternalFilesDir(Environment.DIRECTORY_MUSIC);
                        desc = "任何应用私有的文件的应该被放置在 Context.getExternalFilesDir 返回的目录下，在应用被卸载的时候，系统会清理的就是这个目录。";
                        api = 8;
                        break;

                    case R.id.getExternalStoragePublicDirectory:
                        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                        desc = "共享目录下的分类目录";
                        api = 8;
                        break;

                    case R.id.getExternalCacheDir:
                        path = getExternalCacheDir();
                        api = 8;
                        break;

                    default:
                        path = null;
                        desc = null;
                        api = 0;
                        break;
                }

                if (api != 1) {
                    desc = String.format("%s (API: %d)", desc, api);
                }

                mPath.setText(path.getAbsolutePath());
                mDesc.setText(desc);
            }
        });

        //
        mRadioGroup.check(R.id.getDataDirectory);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
