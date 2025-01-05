package japiim.dic.morekuyubim.por.get_table_values;


public class GetEntriesDefsTableValues {
    private long entryBundleId;
    private long entryId;
    private long senseBundleId;
    private long senseId;
    private long senseOrder;
    private long targetLang;
    private String langCode;
    private String def;
    private long classId;
    private String className;

    public GetEntriesDefsTableValues(long entryBundleId, long entryId, long senseBundleId, long senseId, long senseOrder, long targetLang, String lang_code, String def, long classId, String className) {
        this.entryBundleId = entryBundleId;
        this.entryId = entryId;
        this.senseBundleId = senseBundleId;
        this.senseId = senseId;
        this.senseOrder = senseOrder;
        this.targetLang = targetLang;
        this.langCode = lang_code;
        this.def = def;
        this.classId = classId;
        this.className = className;

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

    public long getSenseId() {
        return senseId;
    }

    public long getSenseOrder() {
        return senseOrder;
    }

    public long getTargetLang() {
        return targetLang;
    }


    public String getLangCode() {
        return langCode;
    }

    public String getDef() {
        return def;
    }

    public long getClassId() {
        return classId;
    }


    public String getClassName() {
        return className;
    }
}
