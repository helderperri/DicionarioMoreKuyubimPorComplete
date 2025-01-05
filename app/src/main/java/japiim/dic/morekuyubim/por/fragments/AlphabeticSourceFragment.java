package japiim.dic.morekuyubim.por.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.database_adapters.DatabaseSourceLettersAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetSourceLettersTableValues;
import japiim.dic.morekuyubim.por.R;

public class AlphabeticSourceFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout mTabLayout;

    DatabaseSourceLettersAdapter databaseSourceLettersAdapter;
    List<GetSourceLettersTableValues> sourceLettersList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);

        View root = inflater.inflate(R.layout.activity_dynamic, container, false);


        initViews(root);

        return root;
    }
    private void initViews(View v){
        //Assign variable
        databaseSourceLettersAdapter = new DatabaseSourceLettersAdapter(getActivity(), 0);
        sourceLettersList = databaseSourceLettersAdapter.getSourceLetters();

        viewPager = v.findViewById(R.id.viewpager);
        mTabLayout =  v.findViewById(R.id.tabs);
        viewPager.setOffscreenPageLimit(0);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setDynamicFragmentToTabLayout();

    }

    private void setDynamicFragmentToTabLayout() {
        for (int i=0; i<sourceLettersList.size(); i++)
        {
            GetSourceLettersTableValues getSourceLettersTableValues = sourceLettersList.get(i);
            String glyph = getSourceLettersTableValues.getGlyph();


            mTabLayout.addTab(mTabLayout.newTab().setText(glyph));

        }
        AlphabeticSourceFragmentAdapter mDynamicFragmentAdapter = new AlphabeticSourceFragmentAdapter(requireActivity().getSupportFragmentManager(), mTabLayout.getTabCount());
        viewPager.setAdapter(mDynamicFragmentAdapter);
        viewPager.setCurrentItem(0);
    }


}