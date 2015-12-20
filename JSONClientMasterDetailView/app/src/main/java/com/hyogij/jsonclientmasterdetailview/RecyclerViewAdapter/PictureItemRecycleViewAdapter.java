package com.hyogij.jsonclientmasterdetailview.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyogij.jsonclientmasterdetailview.Const.Constants;
import com.hyogij.jsonclientmasterdetailview.ImageLoader.ImageLoader;
import com.hyogij.jsonclientmasterdetailview.JsonDatas.Picture;
import com.hyogij.jsonclientmasterdetailview.PictureViewActivity;
import com.hyogij.jsonclientmasterdetailview.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * An adapter class to display Picture item.
 */
public class PictureItemRecycleViewAdapter extends RecyclerView
        .Adapter<PictureItemRecycleViewAdapter.ViewHolder> {
    private static final String CLASS_NAME = PictureItemRecycleViewAdapter
            .class.getCanonicalName();

    private ArrayList<Picture> items = null;
    private ArrayList<Picture> list = null; // Original Picture list
    private Context context = null;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean twoPane;
    public ImageLoader imageLoader = null;

    public PictureItemRecycleViewAdapter(Context context, ArrayList<Picture>
            items, boolean twoPane) {
        this.context = context;
        this.items = items;

        this.list = new ArrayList<Picture>();
        this.list.addAll(items);
        this.twoPane = twoPane;
        imageLoader = new ImageLoader(context.getApplicationContext());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.picture_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        final Picture picture = items.get(position);

        viewHolder.picture = picture;
        viewHolder.albumId.setText(context.getString(R.string.albumId) +
                picture.getAlbumId());
        viewHolder.id.setText(context.getString(R.string.id) + picture.getId());
        viewHolder.title.setText(context.getString(R.string.title) + picture
                .getTitle());
        imageLoader.DisplayImage(picture.getThumbnailUrl(), viewHolder.image);

        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = viewHolder.picture.getUrl();
                String id = viewHolder.picture.getId();
                Intent pictureViewIntent = new Intent(context,
                        PictureViewActivity.class);
                pictureViewIntent.putExtra(Constants.TAG_URL, url);
                pictureViewIntent.putExtra(Constants.TAG_ID, id);
                context.startActivity(pictureViewIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        items.clear();
        if (charText.length() == 0) {
            items.addAll(list);
        } else {
            for (Picture picture : list) {
                if (picture.toString().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    items.add(picture);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public TextView albumId;
        public TextView id;
        public TextView title;
        public ImageView image;

        public Picture picture;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            albumId = (TextView) view.findViewById(R.id.albumId);
            id = (TextView) view.findViewById(R.id.id);
            title = (TextView) view.findViewById(R.id.title);
            image = (ImageView) view.findViewById(R.id.image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + picture.toString() + "'";
        }
    }
}