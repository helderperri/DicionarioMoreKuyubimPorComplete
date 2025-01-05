
package japiim.dic.morekuyubim.por.recycle_view_adapters;

import static japiim.dic.morekuyubim.por.MainActivity.nightMode;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.database_adapters.DatabasePhoneticsAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetPhonemicsTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetPhoneticsTableValues;
import japiim.dic.morekuyubim.por.R;

public class RecyclerViewAdapterPhonemics extends RecyclerView.Adapter<RecyclerViewAdapterPhonemics.ViewHolder> {

    Context context;
    List<GetPhonemicsTableValues> phonemicsList;
    RecyclerView rvPhonemics;

    RecyclerView.LayoutManager layoutManager;

    DatabasePhoneticsAdapter databasePhoneticsAdapter;
    RecyclerView rvPhonetics;
    RecyclerViewAdapterPhonetics recyclerViewAdapterPhonetics;
    List<GetPhoneticsTableValues> phoneticsList = new ArrayList<>();
    long phonemicId;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowPhonemicId;
        TextView rowPhonemic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //rowPhonemicId = itemView.findViewById(R.id.tvPhonemicId);
            rowPhonemic = itemView.findViewById(R.id.tvPhonemic);
        }
    }

    public RecyclerViewAdapterPhonemics(Context context, List<GetPhonemicsTableValues> phonemicsList, RecyclerView rvPhonemics){
        this.context = context;
        this.phonemicsList = phonemicsList;
        this.rvPhonemics = rvPhonemics;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterPhonemics.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_phonemic, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterPhonemics.ViewHolder viewHolder, int i) {
        GetPhonemicsTableValues getPhonemicTableValues = phonemicsList.get(i);

        //viewHolder.rowPhonemicId.setText("phonemic id: "+getPhonemicTableValues.getPhonemicId());
        viewHolder.rowPhonemic.setText(""+getPhonemicTableValues.getPhonemic());


        phonemicId = getPhonemicTableValues.getPhonemicId();
        int source_lang = getPhonemicTableValues.getSourceLang();
        if(source_lang==1) {


            if(nightMode ==1){

                viewHolder.rowPhonemic.setTextColor(ContextCompat.getColor(context,  R.color.source_01_dark));

            }else{

                viewHolder.rowPhonemic.setTextColor(ContextCompat.getColor(context,  R.color.source_01));

            }

        }else if(source_lang==2){

            if(nightMode ==1){

                viewHolder.rowPhonemic.setTextColor(ContextCompat.getColor(context,  R.color.source_02_dark));

            }else{

                viewHolder.rowPhonemic.setTextColor(ContextCompat.getColor(context,  R.color.source_02));

            }

        }


        databasePhoneticsAdapter = new DatabasePhoneticsAdapter(context, phonemicId, 0);
        phoneticsList = databasePhoneticsAdapter.getPhonetics();
        rvPhonetics = viewHolder.itemView.findViewById(R.id.rvPhonetics);
        rvPhonetics.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        rvPhonetics.setLayoutManager(layoutManager);
        recyclerViewAdapterPhonetics = new RecyclerViewAdapterPhonetics(context, phoneticsList, rvPhonetics);
        rvPhonetics.setAdapter(recyclerViewAdapterPhonetics);



    }

    public void onDestroy() {
        // TODO Auto-generated method stub

        recyclerViewAdapterPhonetics.onDestroy();
            Log.d("Main", " GNM-JPP ON DESTROY PHONEMICS ADAPTER");
    }


    @Override
    public int getItemCount() {
        return phonemicsList.size();
    }

}
