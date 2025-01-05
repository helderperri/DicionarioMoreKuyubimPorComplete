package japiim.dic.morekuyubim.por.get_table_values;


public class GetFormsTableValues {
    private long formBundleId;
    private long formId;
    private String entryRef;
    private String vernacular;
    private String langCode;
    private long sourceLang;

    public GetFormsTableValues(long formBundleId, String entryRef, long formId, String vernacular, String langCode, long sourceLang) {
        this.formBundleId = formBundleId;
        this.formId = formId;
        this.entryRef = entryRef;
        this.vernacular = vernacular;
        this.langCode = langCode;
        this.sourceLang = sourceLang;

    }


    public long getFormBundleId() {
        return formBundleId;
    }

    public long getFormId() {
        return formId;
    }


    public String getEntryRef() {
        return entryRef;
    }

    public String getVernacular() {
        return vernacular;
    }

    public String getLangCode() {
        return langCode;
    }

    public long getSourceLang() {
        return sourceLang;
    }

}
