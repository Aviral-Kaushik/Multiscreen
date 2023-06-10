package com.Multiscreen;

public class Word {

    private final String mDefaultTranslation;
    private final String mHindiTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDER;
    private static final int NO_IMAGE_PROVIDER = -1;
    private final int mAudioResourceId;

    public Word(String defaultTranslation, String hindiTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mHindiTranslation = hindiTranslation;
        mAudioResourceId = audioResourceId;

    }

    public Word(String defaultTranslation, String hindiTranslation, int imageResourceId, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mHindiTranslation = hindiTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getHindiTranslation() {
        return  mHindiTranslation;
    }

    public int getResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDER;
    }

    public int getAudioResourceId() {
        return  mAudioResourceId;
    }



}
