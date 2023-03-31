package com.offline.form.builder

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import com.offline.form.builder.databinding.ActivityMainBinding
import com.offline.form.builder.ui.BaseTableFragment
import com.offline.form.builder.utils.ExcelUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private val EXCEL_SHEET_NAME = "Sheet1"

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.nav_home, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        binding.appBarMain.toolbar.setNavigationOnClickListener {
            val navHostFragment: NavHostFragment? =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as? NavHostFragment
            if (navHostFragment != null) {
                val fragment = navHostFragment.childFragmentManager.fragments.getOrNull(0)
                (fragment as? BaseTableFragment)?.let {
                    it.onBackPressed()
                    return@setNavigationOnClickListener
                }
            }

            navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            createExcelFileAndShare()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createExcelFileAndShare() {
        lifecycleScope.launch {
            val allAns =
                withContext(Dispatchers.IO) { OfflineFormApp.db.answersDao().getAllAnswers() }
            val isCreated = ExcelUtils().exportDataIntoWorkbook(
                this@MainActivity,
                "${System.currentTimeMillis()}.xls",
                allAns
            )
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    this@MainActivity,
                    if (isCreated) "File Created" else "Failed to created the file",
                    Toast.LENGTH_SHORT
                ).show()
            }
//            if (isCreated){
//                OfflineFormApp.db.answersDao().deleteAllData()
//            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (findNavController(R.id.nav_host_fragment_content_main).currentDestination?.id == R.id.nav_home){
            MaterialAlertDialogBuilder(this)
                .setTitle("Exit")
                .setCancelable(true)
                .setMessage("Do you want to exit the app?")
                .setPositiveButton("Yes") { _, _ ->
                    finish()
                }.setNegativeButton("Cancel") { d, _ ->
                    d.cancel()
                }.create().show()
        } else {
            super.onBackPressed()
        }
    }

}