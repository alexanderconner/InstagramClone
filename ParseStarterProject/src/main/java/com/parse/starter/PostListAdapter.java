package com.parse.starter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander.conner on 4/13/2018.
 *
 * This adapter sets the post_views based on the Post object properties.
 */

public class PostListAdapter extends ArrayAdapter<Post> {

    private Context mContext;
    private List<Post> usersPostList = new ArrayList<>();

    public PostListAdapter(@NonNull Context context, ArrayList<Post> list) {
        super(context, 0, list);
        mContext = context;
        usersPostList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItemView = convertView;

        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(mContext).inflate(R.layout.post_view, parent, false);
        }

        Post currentPost = usersPostList.get(position);


        TextView authorText = (TextView) listItemView.findViewById(R.id.authorText);
        authorText.setText(currentPost.getAuthor());

        TextView dateText = (TextView) listItemView.findViewById(R.id.dateText);
        dateText.setText(currentPost.getDateCreated());

        //Get bitmap and convert to image
        //ImageView image = (ImageView) listItemView.findViewById(R.id.postImageView);
        //image.setImageBitmap(currentPost.getImage());

        ListView commentsList = (ListView) listItemView.findViewById(R.id.commentsList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, currentPost.getComments());
        commentsList.setAdapter(arrayAdapter);

        return listItemView;

    }

}
