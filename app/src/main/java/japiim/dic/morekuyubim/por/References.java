package japiim.dic.morekuyubim.por;

import static android.content.res.Resources.*;
import static android.provider.Settings.System.getString;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.IntegerRes;

import com.bumptech.glide.load.engine.Resource;


public class References {

//    public static final String dicNameFinal = Resources.getSystem().getString(R.string.app_name);
//    public static final int numberOfSls = Resources.getSystem().getInteger(number_of_sls);


    public String dicNameFinal = "Moré Kuyubim";
    public int numberOfSls = Integer .valueOf(R.integer.number_of_sls);

    public final String getDicName() { return dicNameFinal;}
    public final int getNumberOfSlsInteger() { return numberOfSls;}
    public final String getNumberOfSlsString() {
        String numberOfSls_s = String .valueOf(numberOfSls);
        return numberOfSls_s;}

}
