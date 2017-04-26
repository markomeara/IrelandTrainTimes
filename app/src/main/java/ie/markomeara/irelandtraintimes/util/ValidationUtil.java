package ie.markomeara.irelandtraintimes.util;

import android.support.annotation.Nullable;

/**
 * Created by markomeara on 19/04/2017.
 */

public class ValidationUtil {

    private ValidationUtil() {
        throw new AssertionError();
    }

    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if(reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        } else {
            return reference;
        }
    }
}
