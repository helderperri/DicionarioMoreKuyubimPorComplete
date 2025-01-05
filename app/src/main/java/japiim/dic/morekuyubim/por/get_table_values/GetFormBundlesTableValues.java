package japiim.dic.morekuyubim.por.get_table_values;


public class GetFormBundlesTableValues {
    private long formBundleId;
    private long entryId;
    private String entryRef;

    public GetFormBundlesTableValues(long entryId, String entryRef, long formBundleId) {
        this.formBundleId = formBundleId;
        this.entryId = entryId;
        this.entryRef = entryRef;

    }


    public long getFormBundleId() {
        return formBundleId;
    }

    public long getEntryId() {
        return entryId;
    }


    public String getEntryRef() {
        return entryRef;
    }
}
