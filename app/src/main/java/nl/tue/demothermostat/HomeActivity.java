package nl.tue.demothermostat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottom;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;
    String activeFrag = "home";
    HomeFragment homeFragment = new HomeFragment();
    HeatingFragment heatingFragment = new HeatingFragment();
    ScheduleFragment scheduleFragment = new ScheduleFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        fm = getSupportFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.frag_container, homeFragment, "home");
        fragmentTransaction.commit();

        bottom = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                bottomBarNav(item.getItemId());
                return true;
            }
        });
    }


    private void bottomBarNav(int id) {
        fm = getSupportFragmentManager();
        switch (id) {
            case R.id.action_home:
                fm.beginTransaction().replace(R.id.frag_container, homeFragment).commit();
                break;
            case R.id.action_heating:
                fm.beginTransaction().replace(R.id.frag_container, heatingFragment).commit();
                break;
            case R.id.action_schedule:
                fm.beginTransaction().replace(R.id.frag_container, scheduleFragment).commit();
                break;
        }
    }

    public void switchToFragment1() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frag_container, new HomeFragment()).commit();
    }

    public void switchToFragment2() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frag_container, new HeatingFragment()).commit();
    }

    public void switchToFragment3() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frag_container, new ScheduleFragment()).commit();
    }

}
