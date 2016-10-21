package lessmeaning.x_filemanager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Максим on 11.10.2016.
 */
public class MyArrayAdapter extends ArrayAdapter<String> {
    MainActivity activity;
    ArrayList<String> values;
    final String TAG = "searchagain";
    private int numOfVisible = 0;
    private final ListView listView;
    public MyArrayAdapter(MainActivity context, ArrayList<String> values, ListView listView) {
        super(context, R.layout.filerow, values);
        activity = context;
        this.values = values;
        this.listView = listView;
        listView.setAdapter(this);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        numOfVisible++;
        Log.d(TAG, "getView: " + numOfVisible);
        Log.d(TAG, "getView: " + values.get(position));
        LayoutInflater inflater= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView newView = (TextView) inflater.inflate(R.layout.filerow, parent,false);
        new FileWrapper(activity,new File(values.get(position)),newView);
        String path[] = values.get(position).split("/");
        if(path.length > 0) {
            newView.setText(path[path.length - 1]);
        }else {
            newView.setText("Nothing");
        }
        return newView;
    }


    @Override
    public void remove(String object) {
        super.remove(object);
        if(true)throw new RuntimeException("in remove");
        numOfVisible--;
        Log.d(TAG, "remove: num = " + numOfVisible);
        Log.d(TAG, "remove: " + object);
    }

    @Override
    public void add(String object) {
        if(listView.getLastVisiblePosition() < values.size() - 1){
            values.add(object);
        } else {
            super.add(object);
        }
    }

}