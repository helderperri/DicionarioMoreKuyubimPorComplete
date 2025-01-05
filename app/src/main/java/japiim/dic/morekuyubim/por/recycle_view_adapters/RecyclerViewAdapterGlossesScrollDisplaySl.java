package japiim.dic.morekuyubim.por.recycle_view_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetGlossesTableValues;
import japiim.dic.morekuyubim.por.R;

public class RecyclerViewAdapterGlossesScrollDisplaySl extends RecyclerView.Adapter<RecyclerViewAdapterGlossesScrollDisplaySl.ViewHolder> {

    Context context;
    List<GetGlossesTableValues> glossesList;
    RecyclerView rvGlosses;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        //TextView rowSenseBundleId;
        //TextView rowFormId;
        //ImageView rowImage;
        TextView rowGloss;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //rowSenseBundleId = itemView.findViewById(R.id.sense_bundle_id);
            //rowFormId = itemView.findViewById(R.id.gloss_id);
            rowGloss = itemView.findViewById(R.id.tvGlossScrollDisplaySl);
            //rowEntryRef = itemView.findViewById(R.id.entryRefScrollDisplaySl);
            //rowImage = itemView.findViewById(R.id.item_image);
        }
    }

    public RecyclerViewAdapterGlossesScrollDisplaySl(Context context, List<GetGlossesTableValues> glossesList, RecyclerView rvGlosses){
        this.context = context;
        this.glossesList = glossesList;
        this.rvGlosses = rvGlosses;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterGlossesScrollDisplaySl.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_glosses_scroll_display_sl, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterGlossesScrollDisplaySl.ViewHolder viewHolder, int i) {
        GetGlossesTableValues gloss = glossesList.get(i);
        //viewHolder.rowSenseBundleId.setText(""+gloss.getSenseBundleId());
        //viewHolder.rowFormId.setText(""+gloss.getFormId());
        viewHolder.rowGloss.setText(""+gloss.getGloss());
        //viewHolder.rowEntryRef.setText(""+gloss.getEntryRef());
        //String imageString = country.getImage();
        //int image = context.getResources().getIdentifier(imageString, "drawable", context.getPackageName());
        //viewHolder.rowImage.setImageResource(image);
        
        


    }

    @Override
    public int getItemCount() {
        return glossesList.size();
    }
}
