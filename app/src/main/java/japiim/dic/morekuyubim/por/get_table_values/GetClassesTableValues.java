package japiim.dic.morekuyubim.por.get_table_values;


public class GetClassesTableValues {
    private long senseBundleId;
    private long classId;
    private long classTokenId;
    private String entryRef;

    public GetClassesTableValues(long senseBundleId, String entryRef, long classTokenId, long classId) {
        this.senseBundleId = senseBundleId;
        this.classId = classId;
        this.entryRef = entryRef;
        this.classTokenId = classTokenId;

    }


    public long getSenseBundleId() {
        return senseBundleId;
    }

    public long getClassId() {
        return classId;
    }


    public long getTokenClassId() {
        return classTokenId;
    }


    public String getEntryRef() {
        return entryRef;
    }
}
