package japiim.dic.morekuyubim.por.get_table_values;


public class GetEntriesGlossesTableValues {
    private long entryBundleId;
    private long entryId;
    private long senseBundleId;
    private long glossId;
    private long glossOrder;
    private long targetLang;
    private String langCode;
    private String gloss;
    private long classId;
    private String className;
    private long glyphId;

    public GetEntriesGlossesTableValues(long entryBundleId, long entryId, long senseBundleId, long glossId, long glossOrder, long targetLang, String lang_code, String gloss, long classId, String className, long glyphId) {
        this.entryBundleId = entryBundleId;
        this.entryId = entryId;
        this.senseBundleId = senseBundleId;
        this.glossId = glossId;
        this.glossOrder = glossOrder;
        this.targetLang = targetLang;
        this.langCode = lang_code;
        this.gloss = gloss;
        this.classId = classId;
        this.className = className;
        this.glyphId = glyphId;

    }

    public long getEntryBundleId() {
        return entryBundleId;
    }

    public long getEntryId() {
        return entryId;
    }

    public long getSenseBundleId() {
        return senseBundleId;
    }

    public long getGlossId() {
        return glossId;
    }

    public long getGlossOrder() {
        return glossOrder;
    }

    public long getTargetLang() {
        return targetLang;
    }


    public String getLangCode() {
        return langCode;
    }

    public String getGloss() {
        return gloss;
    }

    public long getClassId() {
        return classId;
    }


    public String getClassName() {
        return className;
    }


    public long getGlyphId() {
        return glyphId;
    }
}
