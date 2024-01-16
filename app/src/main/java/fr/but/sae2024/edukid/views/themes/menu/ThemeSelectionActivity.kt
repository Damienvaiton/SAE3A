package fr.but.sae2024.edukid.views.themes.menu

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecyclerListener
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.utils.enums.ActivityName
import fr.but.sae2024.edukid.utils.managers.RouteManager
import fr.but.sae2024.edukid.views.themes.ThemeViewModel
import fr.but.sae2024.edukid.views.themes.adapters.ThemeSelectionAdapter
import timber.log.Timber

class ThemeSelectionActivity() : AppCompatActivity() {

    val themeViewModel by viewModels<ThemeViewModel>()
    private lateinit var gestureDetector: GestureDetectorCompat


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag("ThemeSelectionActivity").e("onCreate called")
        setContentView(R.layout.theme_selection_activity)

        val themeRv: RecyclerView = findViewById(R.id.rv_theme_selection_activity)

        gestureDetector = GestureDetectorCompat(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                val childView: View? = themeRv.findChildViewUnder(e.x, e.y)
                if (childView != null) {
                    val position = themeRv.getChildAdapterPosition(childView)
                    val theme = themeViewModel.listThemeLiveData.value?.get(position)
                    theme?.let {
                        themeViewModel.themeDefine(it, this@ThemeSelectionActivity)
                    }
                }
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                val childView: View? = themeRv.findChildViewUnder(e.x, e.y)
                if (childView != null) {
                    val position = themeRv.getChildAdapterPosition(childView)
                    val theme = themeViewModel.listThemeLiveData.value?.get(position)
                    theme?.let {
                        // Gestion des clics longs
                    }
                }
            }
        })

        themeRv.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return gestureDetector.onTouchEvent(e)
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })


        themeViewModel.listThemeLiveData.observe(this) {

            val listTheme = it
            val adapter = ThemeSelectionAdapter(listTheme)
            themeRv.adapter = adapter
            themeRv.layoutManager = LinearLayoutManager(this@ThemeSelectionActivity)
            themeRv.setHasFixedSize(true)
        }
        themeViewModel.getListTheme(applicationContext)
    }


    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }


    override fun onBackPressed() {
        super.onBackPressed()
        Timber.e("onBackPressed called")
        RouteManager.startActivity(this, ActivityName.UserSelectionActivity, false, true)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy called")
    }
}