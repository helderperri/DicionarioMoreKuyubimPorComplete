package japiim.dic.morekuyubim.por.get_table_values;


public class GetPronsTableValues {
    private long phoneticId;
    private long pronId;
    private String entryRef;
    private String pron;

    public GetPronsTableValues(long phoneticId, String entryRef, long pronId, String pron) {
        this.phoneticId = phoneticId;
        this.pronId = pronId;
        this.entryRef = entryRef;
        this.pron = pron;

    }


    public long getPronId() {
        return pronId;
    }

    public long getPhoneticId() {
        return phoneticId;
    }


    public String getEntryRef() {
        return entryRef;
    }

    public String getPron() {
        return pron;
    }

}
