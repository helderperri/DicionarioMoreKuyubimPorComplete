
package japiim.dic.morekuyubim.por.recycle_view_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetExamplePronsTableValues;
import japiim.dic.morekuyubim.por.R;

public class RecyclerViewAdapterExamplePhonetics extends RecyclerView.Adapter<RecyclerViewAdapterExamplePhonetics.ViewHolder> {

    Context context;
    List<GetExamplePronsTableValues> examplePronsList;
    RecyclerView rvExamplePhonetics;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowExamplePhoneticId;
        TextView rowExamplePhonetic;
        ImageButton rowExamplePhoneticBtn;





        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //rowExamplePhoneticId = itemView.findViewById(R.id.tvExamplePhoneticId);
            rowExamplePhonetic = itemView.findViewById(R.id.tvExamplePhonetic);
            }
    }

    public RecyclerViewAdapterExamplePhonetics(Context context, List<GetExamplePronsTableValues> examplePronsList, RecyclerView rvExamplePhonetics){
        this.context = context;
        this.examplePronsList = examplePronsList;
        this.rvExamplePhonetics = rvExamplePhonetics;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterExamplePhonetics.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_example_phonetic, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterExamplePhonetics.ViewHolder viewHolder, int i) {
        GetExamplePronsTableValues getExamplePronsTableValues = examplePronsList.get(i);
        String examplePhonetic = getExamplePronsTableValues.getExamplePhonetic();


        //viewHolder.rowExamplePhoneticId.setText("examplePhonetic id: "+examplePhoneticStringNoExtension);

        //viewHolder.rowExamplePhoneticId.setText("examplePhonetic id: "+getExamplePhoneticTableValues.getExamplePhoneticId());
        viewHolder.rowExamplePhonetic.setText(""+getExamplePronsTableValues.getExamplePhonetic());






    }

    @Override
    public int getItemCount() {
        return examplePronsList.size();
    }

}
