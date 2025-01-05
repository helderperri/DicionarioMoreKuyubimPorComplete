
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

public class RecyclerViewAdapterGlosses extends RecyclerView.Adapter<RecyclerViewAdapterGlosses.ViewHolder> {

    Context context;
    List<GetGlossesTableValues> glossesList;
    RecyclerView rvGlosses;




    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowGlossId;
        TextView rowGloss;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //rowGlossId = itemView.findViewById(R.id.tvGlossId);
            rowGloss = itemView.findViewById(R.id.tvGloss);
        }
    }

    public RecyclerViewAdapterGlosses(Context context, List<GetGlossesTableValues> glossesList, RecyclerView rvGlosses){
        this.context = context;
        this.glossesList = glossesList;
        this.rvGlosses = rvGlosses;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterGlosses.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_gloss, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterGlosses.ViewHolder viewHolder, int i) {
        GetGlossesTableValues getGlossTableValues = glossesList.get(i);

        //viewHolder.rowGlossId.setText("gloss id: "+getGlossTableValues.getGlossId());
        viewHolder.rowGloss.setText(""+getGlossTableValues.getGloss());



    }

    @Override
    public int getItemCount() {
        return glossesList.size();
    }

}
