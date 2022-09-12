package co.ostorlab.insecure_app.bugs.calls;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.ostorlab.insecure_app.BugRule;

public class ParcelableMemoryCorruption extends BugRule{
    public static class MemoryObjectParcelable implements Parcelable {
        public Object obj;
        ObjectMapper objectMapper = new ObjectMapper();

        protected MemoryObjectParcelable(android.os.Parcel in) {
            try {
                Class tmp = Class.forName(in.readString());
                obj = objectMapper.readValue(in.readString(), tmp);
            } catch (ClassNotFoundException | JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        public final Creator<MemoryObjectParcelable> CREATOR = new Creator<MemoryObjectParcelable>() {
            @Override
            public MemoryObjectParcelable createFromParcel(android.os.Parcel in) {
                return new MemoryObjectParcelable(in);
            }

            @Override
            public MemoryObjectParcelable[] newArray(int size) {
                return new MemoryObjectParcelable[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(android.os.Parcel parcel, int i) {
            parcel.writeString(obj.getClass().getCanonicalName());
            try {
                parcel.writeString(objectMapper.writeValueAsString(obj));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void run() throws Exception {
        Parcel tmp = Parcel.obtain();
        MemoryObjectParcelable var = new MemoryObjectParcelable(tmp);
    }

    @Override
    public String getDescription() {
        return "Memory corruption using parcelable";
    }
}
