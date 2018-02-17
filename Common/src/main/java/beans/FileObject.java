package beans;

import java.io.File;
import java.io.Serializable;

public class FileObject implements Serializable{


    private byte [] fileByte;
    private String partName;
    private int byteAmount;
    private File locationToSave;



    public File getLocationToSave() {
        return locationToSave;
    }

    public void setLocationToSave(File locationToSave) {
        this.locationToSave = locationToSave;
    }

    public FileObject(byte[] fileByte, String partName, int byteAmount , File locationToSave) {
        this.fileByte = fileByte;
        this.partName = partName;
        this.byteAmount = byteAmount;
        this.locationToSave = locationToSave;
    }


    public byte[] getFileByte() {
        return fileByte;
    }

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getByteAmount() {
        return byteAmount;
    }

    public void setByteAmount(int byteAmount) {
        this.byteAmount = byteAmount;
    }



}
