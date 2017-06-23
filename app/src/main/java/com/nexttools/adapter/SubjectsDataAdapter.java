package com.nexttools.adapter;


import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nexttools.R;
import com.nexttools.activity.MainActivity;
import com.nexttools.model.Cards;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by next on 8/5/17.
 */
public class SubjectsDataAdapter extends RecyclerView.Adapter<SubjectsDataAdapter.MyViewholder> {
    Context mContext;
    private static final String TAG="";
    List<Cards> cardsList = new ArrayList<>();


    public SubjectsDataAdapter(Context mContext, List<Cards> cardsList) {
        this.mContext = mContext;
        this.cardsList = cardsList;
    }

    @Override
    public SubjectsDataAdapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(final SubjectsDataAdapter.MyViewholder holder, int position) {

        Cards cards = cardsList.get(position);
        holder.textinfo.setImageResource(R.drawable.ic_info_outline_black_18dp);
        final String  data = cards.getInfo();
        holder.textinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(mContext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.string.app_name );
                LinearLayout.LayoutParams dialogParams = new LinearLayout.LayoutParams(800,400);
                final LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dislogView = inflater.inflate(R.layout.custom_dailog, null);
                TextView text = (TextView) dislogView.findViewById(R.id.text_info_subjects);
                text.setText(data);
                dialog.setContentView(dislogView, dialogParams);

                dialog.show();

               // Toast.makeText(mContext,"llll",Toast.LENGTH_SHORT).show();
            }
        });
        holder.title.setText(cards.getTitle());
        holder.mImageView.setImageBitmap(ImageViaAssets("no-image.png"));


    }

    @Override
    public int getItemCount() {
       // Log.i(TAG, "getItemCount: "+mainObjects.size());
        return cardsList.size();

    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView textinfo;
        ImageView mImageView;

        public MyViewholder(final View itemView) {
            super(itemView);

            textinfo= (ImageView) itemView.findViewById(R.id.textinfo);
            title= (TextView) itemView.findViewById(R.id.title_name);
            mImageView= (ImageView) itemView.findViewById(R.id.imageView);

        }
    }

    //Loading images from assets folder
    public Bitmap ImageViaAssets(String fileName){

        AssetManager assetmanager = mContext.getAssets();
        InputStream is = null;
        try{

            is = assetmanager.open("/images/" + fileName);
        }catch(IOException e){
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return bitmap;
    }
}
