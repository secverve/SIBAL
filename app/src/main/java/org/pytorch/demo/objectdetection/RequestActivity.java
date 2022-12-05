package org.pytorch.demo.objectdetection;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestActivity<ExitText> extends AppCompatActivity {

    private EditText nameText, emailText;
    private ImageButton backImgBtn, myImgBtn, removeBtn;
    private Button manBtn, womanBtn, signupBtn;
    private Spinner year, month, day;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);

        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);

        // 뒤로가기 버튼
        backImgBtn = findViewById(R.id.backImgBtn);
        backImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        // 이미지 등록
        mBitmap = null;
        myImgBtn = findViewById(R.id.myImgBtn);
        myImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final CharSequence[] options = { "앨범에서 가져오기", "사진 찍기", "뒤로 가기" };
                AlertDialog.Builder builder = new AlertDialog.Builder(RequestActivity.this);
                builder.setTitle("이미지 선택");

                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("사진 찍기")) {
                            Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(takePicture, 0);
                        }
                        else if (options[item].equals("앨범에서 가져오기")) {
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto , 1);

                        }
                        else if (options[item].equals("뒤로 가기")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });

        //  이름 지우기
        removeBtn = findViewById(R.id.removeBtn);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameText.setText(null);
            }
        });

        // 성별
        manBtn = findViewById(R.id.manBtn);
        womanBtn = findViewById(R.id.womanBtn);

        manBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manBtn.setBackgroundColor(Color.LTGRAY);
                womanBtn.setBackgroundColor(Color.TRANSPARENT);

            }
        });

        womanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                womanBtn.setBackgroundColor(Color.LTGRAY);
                manBtn.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        // 스피너 뷰 구현 (생년월일)
        year = findViewById(R.id.year);
        String[] year_list = getResources().getStringArray(R.array.생년);
        @SuppressLint("ResourceType") ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                year_list
        );
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(adapter1);

        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                year.setText
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        month = findViewById(R.id.month);
        String[] month_list = getResources().getStringArray(R.array.월);
        @SuppressLint("ResourceType") ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                month_list
        );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(adapter2);

        month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        day = findViewById(R.id.day);
        String[] day_list = getResources().getStringArray(R.array.일);
        @SuppressLint("ResourceType") ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                day_list
        );
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(adapter3);

        day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // 회원 가입 버튼
        signupBtn = findViewById(R.id.signupBtn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = emailText.getText().toString();
                String userName = nameText.getText().toString();

                if (userEmail.equals("") || userName.equals("")){
                    Toast.makeText( getApplicationContext(), "아이디나 이름을 다시 입력해주세요.", Toast.LENGTH_SHORT ).show();
                    return;
                }
                else{
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject( response );
                                boolean success = jsonObject.getBoolean( "success" );
                                //회원가입 성공시
                                if(success) {
                                    Toast.makeText( getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT ).show();
                                    Intent intent = new Intent( getApplicationContext(), LoginActivity.class );
                                    startActivity( intent );

                                    //회원가입 실패시
                                } else {
                                    Toast.makeText( getApplicationContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT ).show();
                                    return;
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };

                    RegisterRequest registerRequest = new RegisterRequest(userEmail, "id", userName, 23, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    queue.add(registerRequest);
                }
            }
        });
    }
}
