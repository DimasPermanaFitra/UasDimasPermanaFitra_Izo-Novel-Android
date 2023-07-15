package com.sata.izonovel.adpter;

import android.content.Context;
import android.content.Intent;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sata.izonovel.BiodataActivity;
import com.sata.izonovel.DetailNovelActivity;
import com.sata.izonovel.Model.FavoriteNovelResponse;
import com.sata.izonovel.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class FavoritNovelAdapter extends RecyclerView.Adapter<FavoritNovelAdapter.AdapterHolder> {
    private Context context;
    private List<FavoriteNovelResponse.Document> documentsList;

    public FavoritNovelAdapter(Context context, List<FavoriteNovelResponse.Document> documentList){
        this.context = context;
        this.documentsList = documentList;
    }


    @NonNull
    @Override
    public FavoritNovelAdapter.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_novel, parent, false);
        AdapterHolder holder = new AdapterHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritNovelAdapter.AdapterHolder holder, int position) {
        final FavoriteNovelResponse.Document documents = documentsList.get(position);

        String judulNovel = documents.getJudul();
        String tahunDanPengarang = documents.getTahunTerbit() +" | "+ documents.getPengarang();
        String sinopsis = documents.getSinopsis();
        String genre = documents.getGenre();

        holder.JudulNovel.setText(judulNovel);
        holder.TahunDanPengarang.setText(tahunDanPengarang);
        holder.Sinopsis.setText(trimString(sinopsis));
        holder.Genre.setText(genre);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailNovelActivity.class);
                intent.putExtra("id",documents.get_id());
                intent.putExtra("judul", judulNovel);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return documentsList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView JudulNovel, TahunDanPengarang, Sinopsis, Genre;
        ImageView imgPoster;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            JudulNovel = itemView.findViewById(R.id.tvJudulNovel);
            TahunDanPengarang = itemView.findViewById(R.id.tvTahunDanPengarang);
            Sinopsis = itemView.findViewById(R.id.tvSinopsis);
            Genre = itemView.findViewById(R.id.tvGenre);
            imgPoster = itemView.findViewById(R.id.image_poster);
        }
    }

    public String trimString(String item) {
        if (item.length() > 140){
            return  item.substring(0,140 )+"...";
        }
        return item;
    }
}