package japiim.dic.morekuyubim.por.get_table_values;


public class GetClassNamesTableValues {
    private long classId;
    private long classNameId;
  private String className;
    private String langCode;

    public GetClassNamesTableValues(long classId,  long classNameId, String langCode, String className) {
        this.classId = classId;
       this.classNameId = classNameId;
        this.className = className;
        this.langCode = langCode;

    }


    public long getClassNameId() {
        return classNameId;
    }

    public long getClassId() {
        return classId;
    }



    public String getLangCode() {
        return langCode;
    }

    public String getClassName() {
        return className;
    }
}
