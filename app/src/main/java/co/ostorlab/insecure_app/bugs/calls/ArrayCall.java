package co.ostorlab.insecure_app.bugs.calls;

import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.util.ArrayList;

import co.ostorlab.insecure_app.BugRule;

public class ArrayCall extends BugRule {

    private static final String TAG = ArrayCall.class.toString();

    @Override
    public void run() throws Exception {

        int[] ages = new int[5];
        handle_array(ages, 5, 0);
        handle_array(ages, 6, 1);
        handle_array(ages, 7, 2);

        ArrayList al = new ArrayList();
        al.add(899);
        add_element(al,12);
        add_element(al,19);
        add_element(al,999);
        add_element(al,"Une chaîne de caractères !");
        add_element(al, 12.20f);
        add_element(al, 'd');
        Log.i(TAG, String.format("Message: %s", al.get(2)));
    }

    private ArrayList add_element(ArrayList array, Object element)
    {
        array.add(element);
        return array;
    }

    private int[] handle_array(int[] array, int element, int position)
    {
        array[position] = element;
        return array;
    }

    @Override
    public String getDescription() {
        return "The application uses an ArrayList";
    }
}
