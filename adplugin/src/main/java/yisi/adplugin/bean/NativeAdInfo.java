package yisi.adplugin.bean;

/**
 * Created with Android Studio
 * </p>
 * Authour:xiaxf
 * </p>
 * Date:16/6/20.
 */

public class NativeAdInfo {
    public static final int TYPE_BIND_VIEW = 1;//绑定View
    public static final int TYPE_BIND_NORMAL = 2;//不绑定View
    public String mImageUrl;
    public String mIconUrl;
    public String mTitle;
    public String mDesc;
    Object mExtra;
    public int type = TYPE_BIND_NORMAL;
    private int logoResouceId;

    public String random_size;
    public boolean isDownloadApp;

    public void setExtra(Object extra) {
        mExtra = extra;
    }

    public Object getExtra() {
        return mExtra;
    }

    public boolean isDownloadApp() {
        return isDownloadApp;
    }

    public void setDownloadApp(boolean downloadApp) {
        isDownloadApp = downloadApp;
    }

    public void setRandom_size(String random_size) {
        this.random_size = random_size;
    }

    public String getRandom_size() {
        return random_size;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getIconUrl() {
        return mIconUrl;
    }

    public void setIconUrl(String iconUrl) {
        mIconUrl = iconUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public void setLogoResouceId(int logoResouceId) {
        this.logoResouceId = logoResouceId;
    }

    public int getLogoResouceId() {
        return logoResouceId;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
