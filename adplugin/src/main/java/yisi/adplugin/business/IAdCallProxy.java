package yisi.adplugin.business;

/**
 * Created with Android Studio
 * </p>
 * Authour:xiaxf
 * </p>
 * Date:16/6/17.
 */

public interface IAdCallProxy {
    void onAdRequest();

    void onAdPresent();

    void onAdClick();

    void onAdFailed(String message);

    void onAdSkip();

    void onAdLoad();
}
