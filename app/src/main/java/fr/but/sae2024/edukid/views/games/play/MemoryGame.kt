package fr.but.sae2024.edukid.views.games.play

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.GridView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import fr.but.sae2024.edukid.R
import fr.but.sae2024.edukid.models.entities.games.Card
import fr.but.sae2024.edukid.utils.enums.ActivityName
import fr.but.sae2024.edukid.utils.managers.RouteManager
import fr.but.sae2024.edukid.views.games.adapters.MemoryAdapter
import timber.log.Timber


class MemoryGame : AppCompatActivity() {

    private val memoryViewModel by viewModels<MemoryViewModel>()

    private lateinit var sato0 : ScaleAnimation
    private lateinit var sato1 : ScaleAnimation

    private var selectedCards : ArrayList<Card> = arrayListOf()
    private var validatedCards : ArrayList<Card> = arrayListOf()

    private var hitCounteur : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_memory)
        Timber.tag("MemoryGame").i("Start to Create MemoryGame")

        val gameMemoryGrid: GridView = findViewById(R.id.gridview_memory)

        memoryViewModel.getData()

        memoryViewModel.listCardMemory.observe(this){

            if (it?.data() != null) {
                gameMemoryGrid.numColumns = it.data()!!.numberColumn
                Timber.tag("MemoryGame").e("ListCardMemory : $it")

                val cards = it.data()?.listCards
                memoryViewModel.setCards(cards!!)
                onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

                val adapter = MemoryAdapter(this, cards!!, it.data()!!.numberColumn)
                gameMemoryGrid.adapter = adapter

                // Pour être sûr que tous les items sont cliquables
                // Sinon le premier item n'est pas cliquable par un simple setOnClickListener dans adapter
                gameMemoryGrid.setOnItemClickListener { _, view, position, _ ->
                    val card = adapter.getItem(position) as Card
                    Timber.tag("MemoryGame").e("Card : $card")

                    if (!card.isHidden) {
                        return@setOnItemClickListener
                    }else{
                        hitCounteur++
                    }

                    this.createAnimation()

                    // Ajouter un écouteur d'animation pour dévoiler les cartes
                    sato0.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {}
                        override fun onAnimationEnd(animation: Animation?) {

                            card.isHidden = !card.isHidden
                            adapter.notifyDataSetChanged()
                            view.startAnimation(sato1)
                        }
                        override fun onAnimationRepeat(animation: Animation?) {}
                    })

                    sato1.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {}
                        override fun onAnimationEnd(animation: Animation?) {
                            testCard(card, adapter)
                        }
                        override fun onAnimationRepeat(animation: Animation?) {}
                    })

                    view.startAnimation(sato0)
                }
            }
        }
    }

    fun testCard(card: Card, adapter: MemoryAdapter) {
        val resultReturnedCard : String = memoryViewModel.onReturnedCard(card)

        when(resultReturnedCard){
            "WIN" -> {
                Toast.makeText(this@MemoryGame, "You win in " + hitCounteur/2 + " hits", Toast.LENGTH_SHORT).show()
                RouteManager.startActivity(
                    this@MemoryGame,
                    ActivityName.SubGameSelectionActivity,
                    false,
                    true
                )
            }
            "MATCH" -> {
                Timber.tag("MemoryGame").e("Match")
            }
            "NO_MATCH" -> {
                Timber.tag("MemoryGame").e("No Match")
            }
            "FIRST_CARD" -> {
                Timber.tag("MemoryGame").e("First Card")
            }
        }

        adapter.notifyDataSetChanged()
    }

    fun createAnimation() {

        // Animation pour dévoiler les cartes
        sato0 = ScaleAnimation(
            1f, 0f, 1f, 1f,
            Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT,
            0.5f
        )
        sato1 = ScaleAnimation(
            0f, 1f, 1f, 1f,
            Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT,
            0.5f
        )

        sato0.duration = 250
        sato1.duration = 250
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            RouteManager.startActivity(
                this@MemoryGame,
                ActivityName.SubGameSelectionActivity,
                false,
                true
            )
        }
    }

}

