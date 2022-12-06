package org.pytorch.demo.objectdetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Creditmain extends AppCompatActivity {

    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit);

        fragment1 = new Frag1();
        fragment2 = new frag2();
        fragment3 = new frag3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
        // 초기화면 설정
        BottomNavigationView bottomNavigationView = findViewById(R.id.creditinfo);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.tab_credit:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                                return true;

                            case R.id.tab_trade:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                                return true;

                            case R.id.tab_add:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                                return true;
                        }
                        return false;
                    }
                });
    }
}