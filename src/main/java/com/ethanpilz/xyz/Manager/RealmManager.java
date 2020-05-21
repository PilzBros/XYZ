package com.ethanpilz.xyz.Manager;

import com.ethanpilz.xyz.XYZ;

public class RealmManager {

    boolean areRealmsLocked;

    public RealmManager(XYZ xyz){
        areRealmsLocked = false;
    }

    public void lockRealms() {
        this.areRealmsLocked = true;

    }

    public void unlockRealms(){
        this.areRealmsLocked = false;

    }

    public boolean areRealmsLocked() { return this.areRealmsLocked; }

}
