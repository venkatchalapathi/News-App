package com.venkat.newsapp.Model;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SourceNews implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sources")
    @Expose
    private List<Source> sources = null;

    protected SourceNews(Parcel in) {
        status = in.readString();
        sources = in.createTypedArrayList(Source.CREATOR);
    }

    public static final Creator<SourceNews> CREATOR = new Creator<SourceNews>() {
        @Override
        public SourceNews createFromParcel(Parcel in) {
            return new SourceNews(in);
        }

        @Override
        public SourceNews[] newArray(int size) {
            return new SourceNews[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(status);
        parcel.writeTypedList(sources);
    }
}