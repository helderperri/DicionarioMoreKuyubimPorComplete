package japiim.dic.morekuyubim.por.get_table_values;


public class GetImagesTableValues {
    private long senseBundleId;
    private long imageId;
    private String image;
    private String entryRef;

    public GetImagesTableValues(long senseBundleId, String entryRef, long imageId, String image) {
        this.senseBundleId = senseBundleId;
        this.imageId = imageId;
        this.image = image;
        this.entryRef = entryRef;

    }


    public long getSenseBundleId() {
        return senseBundleId;
    }

    public long getImageId() {
        return imageId;
    }

    public String getImage() {
        return image;
    }


    public String getEntryRef() {
        return entryRef;
    }
}
