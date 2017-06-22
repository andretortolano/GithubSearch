package com.example.andretortolano.githubsearch

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.andretortolano.githubsearch.views.SearchRepositoryView
import com.example.andretortolano.githubsearch.views.SearchUserView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        replaceFragment(SearchUserView::class.java)
        nav_view.setCheckedItem(R.id.nav_user)

        // TODO remove
        // val service: GithubService = GithubService()
        // service.getUser("andretortolano").subscribe { user -> Log.i("APPTAG", "user: ${user.id}, ${user.avatarUrl}") }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            }
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragmentClass: Class<*>? = null
        when (item.itemId) {
            R.id.nav_user -> {
                fragmentClass = SearchUserView::class.java
            }
            R.id.nav_repository -> {
                fragmentClass = SearchRepositoryView::class.java
            }
        }

        if (fragmentClass != null) {
            replaceFragment(fragmentClass)
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun replaceFragment(fragmentClass: Class<*>) {
        var fragment: Fragment? = null
        try {
            fragment = (fragmentClass.newInstance() as Fragment)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val oldFragment = supportFragmentManager.findFragmentByTag(fragmentClass.simpleName)
        if (oldFragment != null && !oldFragment.isDetached) {
            return
        }

        supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment, fragmentClass.simpleName).commit()
    }
}
