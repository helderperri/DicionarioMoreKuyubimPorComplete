package japiim.dic.morekuyubim.por.get_table_values;

public class GetEntriesSdsTableValues {
    private long sdId;
    private String langCode;
    private long targetLang;
    private String sdName;

    public GetEntriesSdsTableValues(long targetLang, String langCode, long sdId, String sdName) {
        this.sdId = sdId;
        this.sdName = sdName;
        this.targetLang = targetLang;
        this.langCode = langCode;

    }


    public long getSdId() {
        return sdId;
    }

    public long getTargetLang() {
        return targetLang;
    }




    public String getSdName() {
        return sdName;
    }

    public String getLangCode() {
        return langCode;
    }

}
