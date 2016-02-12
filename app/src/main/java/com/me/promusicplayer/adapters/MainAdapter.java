package com.me.promusicplayer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.me.promusicplayer.R;
import com.me.promusicplayer.models.Song;
import java.util.ArrayList;

public class MainAdapter extends BaseAdapter {
  private ArrayList<Song> data;
  private LayoutInflater inflater = null;

  public MainAdapter(Context context, ArrayList<Song> inputData) {

    this.data = inputData;
    inflater = LayoutInflater.from(context);
  }

  public int getCount() {
    return data.size();
  }

  public Object getItem(int position) {
    return position;
  }

  public long getItemId(int position) {
    return position;
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    View vi = convertView;
    if (convertView == null) vi = inflater.inflate(R.layout.row_main, null);

    TextView textView_row_name = (TextView) vi.findViewById(R.id.textView_row_name);
    TextView textView_row_artist = (TextView) vi.findViewById(R.id.textView_row_artist);

    textView_row_name.setText(data.get(position).getName());
    textView_row_artist.setText(data.get(position).getArtist());

    return vi;
  }
}