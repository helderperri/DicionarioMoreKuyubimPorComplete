package japiim.dic.morekuyubim.por.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import japiim.dic.morekuyubim.por.database_adapters.DatabaseEntriesSdsAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetEntriesSdsTableValues;
import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.fragments.SemanticTargetFragmentAdapter;

public class SemanticTargetFragment extends Fragment {
    DatabaseEntriesSdsAdapter databaseEntriesSdsAdapter;

    List<GetEntriesSdsTableValues> entriesSdsList;



    private ViewPager viewPager;
    private TabLayout mTabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        super.onCreate(savedInstanceState);

        View view;
        view = inflater.inflate(R.layout.activity_dynamic, container, false);


        Log.d("GNM-JPP", "onCreateView TARGET SEMANTIC");


        initViews(view);


        return view;
    }
    private void initViews(View v){
        Log.d("GNM-JPP", "initViews");

        databaseEntriesSdsAdapter = new DatabaseEntriesSdsAdapter(getContext(), 0, "");
        entriesSdsList = databaseEntriesSdsAdapter.getSdsToSearchDisplay();

        viewPager = v.findViewById(R.id.viewpager);

        mTabLayout =  v.findViewById(R.id.tabs);

        // Por que setOffscreenPageLimit é 5 (valor original). Agora setado para 0 para debug?;
        viewPager.setOffscreenPageLimit(1);

        //a  linha abaixo guarda a posição na semantic target tab
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

                //Log.d("GNM-JPP", "onTabSelected3: TARGET SEMANTIC");
                Log.d("GNM-JPP", "onTabSelected3: TARGET SEMANTIC tba-position: "+tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("GNM-JPP", "onTabUnselected2: TARGET SEMANTIC");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(1);
//                viewPager.setCurrentItem(tab.getPosition());
                Log.d("GNM-JPP", "onTabReselected5: TARGET SEMANTIC");
            }
        });

        setDynamicFragmentToTabLayout();
    }

    private void setDynamicFragmentToTabLayout() {
        Log.d("GNM-JPP", "setDynamicFragmentToTabLayout");
        for (int i=0; i<entriesSdsList.size(); i++)
        {

            GetEntriesSdsTableValues getEntriesSdsTableValues = entriesSdsList.get(i);

            String sdName = getEntriesSdsTableValues.getSdName();
            Log.d("GNM-JPP", "sdName7: "+sdName);

            mTabLayout.addTab(mTabLayout.newTab().setText(sdName));


        }
        SemanticTargetFragmentAdapter mDynamicFragmentAdapter = new SemanticTargetFragmentAdapter(requireActivity().getSupportFragmentManager(), mTabLayout.getTabCount());

        viewPager.setAdapter(mDynamicFragmentAdapter);
        viewPager.setCurrentItem(0);
    }

}

