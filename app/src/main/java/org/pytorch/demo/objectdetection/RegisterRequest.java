package org.pytorch.demo.objectdetection;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://qhdrmfdl123.dothome.co.kr/register.php";
//    private static final Response.Listener<String> listener;
    private Map<String, String>map;
    //private Map<String, String>parameters;

    public RegisterRequest(String userEmail, String userID, String userName, int userAge, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userEmail", userEmail);
        map.put("userID", userID);
        map.put("userName", userName);
        map.put("userAge", userAge + "");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
