package japiim.dic.morekuyubim.por.recycle_view_adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.get_table_values.GetEntriesTableValues;

public class RecyclerViewAdapterIntroduction extends RecyclerView.Adapter<RecyclerViewAdapterIntroduction.ViewHolder> {


    Context context;
    RecyclerView rvIntro;

    RecyclerView.LayoutManager layoutManager;

    @NonNull
    @Override
    public RecyclerViewAdapterIntroduction.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.base_layout_introduction, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterIntroduction.ViewHolder holder, int position) {



    }


    public static class ViewHolder extends RecyclerView.ViewHolder{


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public RecyclerViewAdapterIntroduction(Context context, RecyclerView rvIntro){
        this.context = context;
        this.rvIntro = rvIntro;
    }


    @Override
    public int getItemCount() {
        return 0;
    }
}
