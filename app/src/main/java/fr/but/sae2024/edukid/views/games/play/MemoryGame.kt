package fr.but.sae2024.edukid.views.games.play

import android.os.Bundle
import android.widget.GridView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.views.games.adapters.MemoryAdapter
import timber.log.Timber

class MemoryGame : AppCompatActivity() {

    private val memoryViewModel by viewModels<MemoryViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_memory)
        Timber.tag("MemoryGame").i("Start to Create MemoryGame")

        val gameMemoryGrid: GridView = findViewById(R.id.gridview_memory)
        memoryViewModel.getData()

        memoryViewModel.listCardMemory.observe(this){

            /*gameMemoryGrid.numColumns= it.data()./*num*/*/

            Timber.tag("MemoryGame").e("ListCardMemory : $it")

            val cards = it.data()?.listCards


            val adapter = MemoryAdapter(this, cards!!)
            gameMemoryGrid.adapter = adapter

        }



    }

}

