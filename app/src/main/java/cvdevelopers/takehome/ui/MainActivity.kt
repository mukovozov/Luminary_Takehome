package cvdevelopers.takehome.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cvdevelopers.githubstalker.R
import cvdevelopers.takehome.ui.client_list.ClientListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = ClientListFragment.newInstance()

            // I didn't use any navigation framework, cause this app contains only 1 screen.
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }

}
