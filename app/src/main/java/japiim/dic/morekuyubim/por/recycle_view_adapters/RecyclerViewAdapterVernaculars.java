
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

import japiim.dic.morekuyubim.por.database_adapters.DatabasePhonemicsAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetFormsTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetPhonemicsTableValues;
import japiim.dic.morekuyubim.por.R;

public class RecyclerViewAdapterVernaculars extends RecyclerView.Adapter<RecyclerViewAdapterVernaculars.ViewHolder> {

    Context context;
    List<GetFormsTableValues> vernacularsList;
    RecyclerView rvVernaculars;
    String vernacular;
    String lang_code;
    long source_lang;


    RecyclerView.LayoutManager layoutManager;

    DatabasePhonemicsAdapter databasePhonemicsAdapter;
    RecyclerView rvPhonemics;
    public static RecyclerView rvProns;
    RecyclerViewAdapterPhonemics recyclerViewAdapterPhonemics;
    List<GetPhonemicsTableValues> phonemicsList = new ArrayList<>();
    long formId;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowFormId;
        TextView rowVernacular;
        TextView rowLangCode;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //rowFormId = itemView.findViewById(R.id.tvFormId);
            rowVernacular = itemView.findViewById(R.id.tvVernacular);
            rowLangCode = itemView.findViewById(R.id.tvLangCode);
        }
    }

    public RecyclerViewAdapterVernaculars(Context context, List<GetFormsTableValues> vernacularsList, RecyclerView rvVernaculars){
        this.context = context;
        this.vernacularsList = vernacularsList;
        this.rvVernaculars = rvVernaculars;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterVernaculars.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_vernacular, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterVernaculars.ViewHolder viewHolder, int i) {
        GetFormsTableValues getFormsTableValues = vernacularsList.get(i);

        //viewHolder.rowFormId.setText("form id: "+getFormsTableValues.getFormId());
        vernacular = getFormsTableValues.getVernacular();
        source_lang = getFormsTableValues.getSourceLang();
        lang_code = getFormsTableValues.getLangCode();


        int numberOfSls = context.getResources().getInteger(R.integer.number_of_sls);

        viewHolder.rowVernacular.setText(""+ vernacular);

        if(numberOfSls == 1){
            viewHolder.rowLangCode.setText("");

        }else if (numberOfSls >= 2){

            viewHolder.rowLangCode.setText(""+" ["+lang_code+"]");
        }

        if(source_lang==1) {


            if(nightMode ==1){

                viewHolder.rowVernacular.setTextColor(ContextCompat.getColor(context,  R.color.source_01_dark));
                viewHolder.rowLangCode.setTextColor(ContextCompat.getColor(context,R.color.source_01_dark));

            }else{

                viewHolder.rowVernacular.setTextColor(ContextCompat.getColor(context,  R.color.source_01));
                viewHolder.rowLangCode.setTextColor(ContextCompat.getColor(context,R.color.source_01));

            }

        }else if(source_lang==2){

            if(nightMode ==1){

                viewHolder.rowVernacular.setTextColor(ContextCompat.getColor(context,  R.color.source_02_dark));
                viewHolder.rowLangCode.setTextColor(ContextCompat.getColor(context,R.color.source_02_dark));

            }else{

                viewHolder.rowVernacular.setTextColor(ContextCompat.getColor(context,  R.color.source_02));
                viewHolder.rowLangCode.setTextColor(ContextCompat.getColor(context,R.color.source_02));

            }

        }

        formId = getFormsTableValues.getFormId();


        databasePhonemicsAdapter = new DatabasePhonemicsAdapter(context, formId, 0);
        phonemicsList = databasePhonemicsAdapter.getPhonemics();
        rvPhonemics = viewHolder.itemView.findViewById(R.id.rvPhonemics);
        rvProns = viewHolder.itemView.findViewById(R.id.rvProns);

        rvPhonemics.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        rvPhonemics.setLayoutManager(layoutManager);
        recyclerViewAdapterPhonemics = new RecyclerViewAdapterPhonemics(context, phonemicsList, rvPhonemics);
        rvPhonemics.setAdapter(recyclerViewAdapterPhonemics);




    }


    public void onDestroy() {
        // TODO Auto-generated method stub

        recyclerViewAdapterPhonemics.onDestroy();
        Log.d("Main", " GNM-JPP ON DESTROY VERNACULAR ADAPTER");
    }

    @Override
    public int getItemCount() {
        return vernacularsList.size();
    }

}
