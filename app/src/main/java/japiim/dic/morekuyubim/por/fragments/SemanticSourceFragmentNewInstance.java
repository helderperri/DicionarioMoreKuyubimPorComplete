
package japiim.dic.morekuyubim.por.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import japiim.dic.morekuyubim.por.database_adapters.DatabaseEntriesSdsAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseEntriesVernacularSdsAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetEntriesSdsTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetEntriesVernacularSdsTableValues;
import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntriesSdsScrollDisplaySl;


public class  SemanticSourceFragmentNewInstance extends Fragment {

    RecyclerView.LayoutManager layoutManager;

    RecyclerViewAdapterEntriesSdsScrollDisplaySl recyclerViewAdapterEntriesSdsScrollDisplaySl;

    DatabaseEntriesSdsAdapter databaseEntriesSdsAdapter;

    List<GetEntriesSdsTableValues> entriesSdsList = new ArrayList<>();



    DatabaseEntriesVernacularSdsAdapter databaseEntriesVernacularSdsAdapter;
    List<GetEntriesVernacularSdsTableValues> entriesVernacularList = new ArrayList<>();


    public static  SemanticSourceFragmentNewInstance newInstance() {
        return new  SemanticSourceFragmentNewInstance();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.dynamic_fragment_layout, container, false);

//        Log.d("GNM-JPP", "onCreateView DYNAMIC FRAGMENT SOURCE SEMANTIC ");

        initViews(view);
        return view;
    }

    private void initViews(View view) {

        databaseEntriesSdsAdapter = new DatabaseEntriesSdsAdapter(getContext(), 0, "");
        entriesSdsList = databaseEntriesSdsAdapter.getSdsToSearchDisplay();

            int position = getArguments().getInt("position");
            GetEntriesSdsTableValues getEntriesSdsTableValues = entriesSdsList.get(position);

            long sdId = getEntriesSdsTableValues.getSdId();



            databaseEntriesVernacularSdsAdapter = new DatabaseEntriesVernacularSdsAdapter(getContext(), 0, 0, 0);

            entriesVernacularList = databaseEntriesVernacularSdsAdapter.getVernacularsToSearchDisplayBySdId(sdId);

            RecyclerView rvEntries = view.findViewById(R.id.rvEntries);

            recyclerViewAdapterEntriesSdsScrollDisplaySl = new RecyclerViewAdapterEntriesSdsScrollDisplaySl(getActivity(), entriesVernacularList, rvEntries);

            rvEntries.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getActivity());
            rvEntries.setLayoutManager(layoutManager);

            rvEntries.setAdapter(recyclerViewAdapterEntriesSdsScrollDisplaySl);



    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("GNM-JPP", "onActivityCreated: FRAGMENT SEMANTIC SOURCE");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("GNM-JPP", "onAttach: FRAGMENT SEMANTIC SOURCE");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("GNM-JPP", "onPause: FRAGMENT SEMANTIC SOURCE");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("GNM-JPP", "onResume: FRAGMENT SEMANTIC SOURCE");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("GNM-JPP", "onStop: FRAGMENT SEMANTIC SOURCE");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("GNM-JPP", "onDestroyView: FRAGMENT SEMANTIC SOURCE");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("GNM-JPP", "onDestroy: FRAGMENT SEMANTIC SOURCE");

    }
}
