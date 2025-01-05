
package japiim.dic.morekuyubim.por.recycle_view_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetExamplesTranslationTableValues;
import japiim.dic.morekuyubim.por.R;

public class RecyclerViewAdapterExamplesTranslation extends RecyclerView.Adapter<RecyclerViewAdapterExamplesTranslation.ViewHolder> {

    Context context;
    List<GetExamplesTranslationTableValues> examplesTranslationList;
    RecyclerView rvExamplesTranslation;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowExampleTranslationId;
        TextView rowExampleTranslation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //rowExampleTranslationId = itemView.findViewById(R.id.tvExampleTranslationId);
            rowExampleTranslation = itemView.findViewById(R.id.tvExampleTranslation);
        }
    }

    public RecyclerViewAdapterExamplesTranslation(Context context, List<GetExamplesTranslationTableValues> examplesTranslationList, RecyclerView rvExamplesTranslation){
        this.context = context;
        this.examplesTranslationList = examplesTranslationList;
        this.rvExamplesTranslation = rvExamplesTranslation;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterExamplesTranslation.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_example_translation, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterExamplesTranslation.ViewHolder viewHolder, int i) {
        GetExamplesTranslationTableValues getExampleTranslationTableValues = examplesTranslationList.get(i);

        //viewHolder.rowExampleTranslationId.setText("example translation id: "+getExampleTranslationTableValues.getExampleTranslationId());
        viewHolder.rowExampleTranslation.setText(""+getExampleTranslationTableValues.getExampleTranslation());


    }

    @Override
    public int getItemCount() {
        return examplesTranslationList.size();
    }

}
