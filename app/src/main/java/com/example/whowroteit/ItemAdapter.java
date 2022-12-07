// Lu Lu
package com.example.whowroteit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    private ArrayList<GoogleBookModel> dataList;
    private Context context;

    public ItemAdapter(Context context, ArrayList<GoogleBookModel> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtTitle;
        private ImageView coverImage;

        ItemViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.title);
            coverImage = mView.findViewById(R.id.coverImage);
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.model_list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        GoogleBookModel googleBookModel = dataList.get(position);
        holder.txtTitle.setText(googleBookModel.getTitle());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getThumbnail())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);
        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, ModelDetailPage.class);
            i.putExtra("title", googleBookModel.getTitle());
            i.putExtra("thumbnail", googleBookModel.getThumbnail());
            i.putExtra("subtitle", googleBookModel.getSubtitle());
            i.putExtra("publisher", googleBookModel.getPublisher());
            i.putExtra("publishedDate", googleBookModel.getPublishedDate());
            i.putExtra("description", googleBookModel.getDescription());
            i.putExtra("authors", googleBookModel.getAuthors());
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
