package homi.sybelblue.contraversev12;

import android.content.Context;
import android.content.Intent;

public class ContraverseUtils {

    public static int SFCODE_SIZE = SFCode.values().length;

    public static int numberFromCode(SFCode code) {
        switch (code) {
            case Success:
                return 0;
            case Fail:
                return 1;
            case SS:
                return 2;
            case SF:
                return 3;
            case FS:
                return 4;
            case FF:
                return 5;
        }

        throw new IllegalArgumentException();
    }

    public static SFCode codeFromNumber(int number) {
        switch (number) {
            case 0:
                return SFCode.Success;
            case 1:
                return SFCode.Fail;
            case 2:
                return SFCode.SS;
            case 3:
                return SFCode.SF;
            case 4:
                return SFCode.FS;
            case 5:
                return SFCode.FF;
        }

        throw new IllegalArgumentException();
    }

    public static void pushOldExtra(Context context, Intent currentIntent, Intent toNewIntent, int stringKey) {
        String key = context.getString(stringKey);
        toNewIntent.putExtra(key, currentIntent.getStringExtra(key));
    }
}
