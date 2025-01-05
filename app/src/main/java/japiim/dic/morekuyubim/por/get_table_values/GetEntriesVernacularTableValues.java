package japiim.dic.morekuyubim.por.get_table_values;


public class GetEntriesVernacularTableValues {
    private long entryBundleId;
    private long entryId;
    private long formBundleId;
    private long formId;
    private long formOrder;
    private long sourceLang;
    private String langCode;
    private String vernacular;
    private long glyphId;

    public GetEntriesVernacularTableValues(long entryBundleId, long entryId, long formBundleId, long formId, long formOrder, long sourceLang, String lang_code, String vernacular, long glyphId) {
        this.entryBundleId = entryBundleId;
        this.entryId = entryId;
        this.formBundleId = formBundleId;
        this.formId = formId;
        this.formOrder = formOrder;
        this.sourceLang = sourceLang;
        this.langCode = lang_code;
        this.vernacular = vernacular;
        this.glyphId = glyphId;

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

    public long getGlyphId() {
        return glyphId;
    }
}
