package com.example.myfitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    private lateinit var status_text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerlayout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        //shows the upper left lateral menu
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener{

            it.isChecked = true

            when(it.itemId)
            {
                R.id.nav_MealManagement -> ReplaceFragment(MealManagement(), it.title.toString())
                R.id.nav_Statistics -> ReplaceFragment(StatisticsFragment(), it.title.toString())
                R.id.nav_TrainingPlan -> ReplaceFragment(TrainingPlan(), it.title.toString())
            }

            true
        }
    }

    private fun ReplaceFragment(fragment: Fragment,title:String)
    {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nav_user_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item))
        {
            //navigation drawer item selected
            return true
        }
        else
        {
            //options menu item selected
            return when (item.itemId) {
                R.id.UserSettings -> {
                    Toast.makeText(this, "User Settings", Toast.LENGTH_LONG).show()
                    return true
                }

                R.id.About -> {
                    Toast.makeText(this, "About", Toast.LENGTH_LONG).show()
                    return true
                }

                else -> return super.onOptionsItemSelected(item)
            }
        }
    }
}