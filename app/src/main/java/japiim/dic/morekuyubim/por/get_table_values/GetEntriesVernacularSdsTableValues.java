package japiim.dic.morekuyubim.por.get_table_values;


public class GetEntriesVernacularSdsTableValues {
    private long entryBundleId;
    private long entryId;
    private long senseBundleId;
    private long formBundleId;
    private long formId;
    private long formOrder;
    private long sourceLang;
    private String langCode;
    private String vernacular;
    private long sdId;
    private String sdName;
    private long sdTargetLang;
    private String sdLangCode;

    public GetEntriesVernacularSdsTableValues(long entryBundleId, long entryId, long senseBundleId, long formBundleId, long formId, long formOrder, long sourceLang, String lang_code, String vernacular, long sdId, String sdName, long sdTargetLang, String sdLangCode) {
        this.entryBundleId = entryBundleId;
        this.entryId = entryId;
        this.senseBundleId = senseBundleId;
        this.formBundleId = formBundleId;
        this.formId = formId;
        this.formOrder = formOrder;
        this.sourceLang = sourceLang;
        this.langCode = lang_code;
        this.vernacular = vernacular;
        this.sdId = sdId;
        this.sdName = sdName;
        this.sdTargetLang = sdTargetLang;
        this.sdLangCode = sdLangCode;

    }

    public long getEntryBundleId() {
        return entryBundleId;
    }

    public long getEntryId() {
        return entryId;
    }

    public long getFormBundleId() {
        return formBundleId;
    }
    public long getSenseBundleId() {
        return senseBundleId;
    }

    public long getFormId() {
        return formId;
    }

    public long getFormOrder() {
        return formOrder;
    }

    public long getSourceLang() {
        return sourceLang;
    }


    public String getLangCode() {
        return langCode;
    }

    public String getVernacular() {
        return vernacular;
    }

    public long getSdId() {
        return sdId;
    }

    public String getSdName() {
        return sdName;
    }




    public long getSdTargetLang() {
        return sdTargetLang;
    }


    public String getSdLangCode() {
        return sdLangCode;
    }


}
