package japiim.dic.morekuyubim.por.get_table_values;


public class GetVideosTableValues {
    private long senseBundleId;
    private long videoId;
    private String video;
    private String entryRef;

    public GetVideosTableValues(long senseBundleId, String entryRef, long videoId, String video) {
        this.senseBundleId = senseBundleId;
        this.videoId = videoId;
        this.video = video;
        this.entryRef = entryRef;

    }


    public long getSenseBundleId() {
        return senseBundleId;
    }

    public long getVideoId() {
        return videoId;
    }

    public String getVideo() {
        return video;
    }


    public String getEntryRef() {
        return entryRef;
    }
}
