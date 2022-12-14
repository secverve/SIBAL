package org.pytorch.demo.objectdetection;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.Account;
import com.kakao.sdk.user.model.AgeRange;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private EditText loginEmail, loginName;
    private Button loginBtn, signupBtn;
    private ImageButton kakaoLoginBtn, googleBtn;
    private static final int RC_SIGN_IN = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Log.d("KeyHash", getKeyHash());

        // Email
        loginEmail = findViewById( R.id.loginEmail );
        loginName = findViewById( R.id.loginName );

        signupBtn = findViewById( R.id.signupBtn );
        signupBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), RequestActivity.class );
                startActivity(intent);
            }
        });

        // check database --- signup
        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = loginEmail.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean( "success" );

                            if(success) {//????????? ?????????
                                String userEmail = jsonObject.getString( "userEmail" );
                                String userID = jsonObject.getString( "userID" );
                                String userName = jsonObject.getString( "userName" );
                                String userAge = jsonObject.getString( "userAge" );

                                Toast.makeText(getApplicationContext(), "????????? ??????", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                                intent.putExtra( "userEmail", userEmail );
                                intent.putExtra( "userID", userID );
                                intent.putExtra( "userName", userName );
                                intent.putExtra( "userAge", userAge );

                                startActivity(intent);

                            } else {//????????? ?????????
                                Toast.makeText( getApplicationContext(), "????????? ??????", Toast.LENGTH_SHORT ).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userEmail, responseListener);
                RequestQueue queue = Volley.newRequestQueue( LoginActivity.this );
                queue.add(loginRequest);
            }
        });

        // check login -- kakao
        kakaoLoginBtn = findViewById(R.id.kakaoLoginBtn);
        kakaoLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)) {
                    kakao_login();
                }else {
                    kakao_accountLogin();
                }
            }
        });

        // check login -- google
        googleBtn = findViewById(R.id.googleLoginBtn);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestId()
                .requestEmail()
                .requestProfile()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(LoginActivity.this, gso);

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    // ????????? ?????? ?????? ?????????
    public void kakao_login(){
        String TAG = "login()";
        UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this,(oAuthToken, error) -> {
            if (error != null) {
                Log.e(TAG, "????????? ??????", error);
            } else if (oAuthToken != null) {
                Log.i(TAG, "????????? ??????(??????) : " + oAuthToken.getAccessToken());
                getUserInfo();
            }
            return null;
        });
    }

    // ????????? ?????? ?????? ????????? method()
    public void kakao_accountLogin(){
        String TAG = "accountLogin()";
        UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this,(oAuthToken, error) -> {
            if (error != null) {
                Log.e(TAG, "????????? ??????", error);
            } else if (oAuthToken != null) {
                Log.i(TAG, "????????? ??????(??????) : " + oAuthToken.getAccessToken());
                getUserInfo();
            }
            return null;
        });
    }

    public void getUserInfo(){
        String TAG = "getUserInfo()";
        UserApiClient.getInstance().me((user, meError) -> {
            if (meError != null) {
                Log.e(TAG, "????????? ?????? ?????? ??????", meError);
            } else {
                Log.i(TAG, user.toString());

                {
                    Log.i(TAG, "????????? ?????? ?????? ??????" +
                            "\n????????????: "+user.getId() +
                            "\n?????????: "+user.getKakaoAccount().getEmail());
                }
                Account user1 = user.getKakaoAccount();
                System.out.println("????????? ??????" + user1);

                String personName = user1.getProfile().getNickname();
                String personEmail = user.getKakaoAccount().getEmail().toString();
                String personId = user.getId().toString();
//                String personAge = user.getKakaoAccount().getAgeRange().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );
                            //???????????? ?????????
                            if(success) {
                                Toast.makeText( getApplicationContext(), "??????????????? ?????????????????????.", Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent( getApplicationContext(), MainActivity.class );

                                intent.putExtra( "userEmail", personEmail );
                                intent.putExtra( "userID", personId );
                                intent.putExtra( "userName", personName );
                                intent.putExtra( "userAge", 99 );

                                startActivity( intent );

                                //???????????? ?????????
                            } else {
                                Toast.makeText( getApplicationContext(), "??????????????? ?????????????????????.", Toast.LENGTH_SHORT ).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(personEmail, personId, personName, 99, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(registerRequest);
            }
            return null;
        });
    }

    public String getKeyHash(){
        try{
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            if(packageInfo == null) return null;
            for(Signature signature: packageInfo.signatures){
                try{
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
                }catch (NoSuchAlgorithmException e){
                    Log.w("getKeyHash", "Unable to get MessageDigest. signature="+signature, e);
                }
            }
        }catch(PackageManager.NameNotFoundException e){
            Log.w("getPackageInfo", "Unable to getPackageInfo");
        }
        return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount acct = task.getResult(ApiException.class);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );
                            //???????????? ?????????
                            if(success) {
                                Toast.makeText( getApplicationContext(), "??????????????? ?????????????????????.", Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent( getApplicationContext(), MainActivity.class );

                                intent.putExtra( "userEmail", personEmail );
                                intent.putExtra( "userID", personId );
                                intent.putExtra( "userName", personName );
                                intent.putExtra( "userAge", 99 );

                                startActivity( intent );

                                //???????????? ?????????
                            } else {
                                Toast.makeText( getApplicationContext(), "??????????????? ?????????????????????.", Toast.LENGTH_SHORT ).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(personEmail, personId.toString(), personName, 99, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(registerRequest);

            }
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }
}
