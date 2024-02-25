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
                            memoryViewModel.onReturnedCard(card)
                        }
                        override fun onAnimationRepeat(animation: Animation?) {}
                    })

                    view.startAnimation(sato0)

                    //mettre la carte cliqué dans la liste des cartes selectionné
                    selectedCards.add(card)

                    //si 2 card sont selectionné alors on les compare, on verifie si elles sont identiques ou non et on vide la liste des cartes selectionnées
                    if (selectedCards.size == 2) {
                        //si les 2 cartes sont identiques alors on les ajoute dans la liste des cartes validées
                        if (selectedCards[0].value == selectedCards[1].value) {
                            validatedCards.add(selectedCards[0])
                            validatedCards.add(selectedCards[1])

                            //si toutes les cartes sont validées alors on a gagné
                            if (validatedCards.size == cards.size) {
                                //le nombre de hit pour gagner est divisé par 2 car on compte 2 hit pour chaque paire de carte
                                Timber.tag("MemoryGame").e("You win in " + hitCounteur/2 + " hits")
                                Toast.makeText(this, "You win in " + hitCounteur/2 + " hits", Toast.LENGTH_SHORT).show()
                                //redirection au menu memory
                                RouteManager.startActivity(
                                    this@MemoryGame,
                                    ActivityName.SubGameSelectionActivity,
                                    false,
                                    true
                                )
                            }

                        } else {
                            //si les 2 cartes ne sont pas identiques alors on les cache
                            /*ajouter par la suite les animations*/
                            selectedCards[0].isHidden = !selectedCards[0].isHidden
                            selectedCards[1].isHidden = !selectedCards[1].isHidden

                            adapter.notifyDataSetChanged()
                        }

                        //on supprime les cartes selectionnées pour pouvoir en selectionner d'autres
                        selectedCards.clear()

                    }
                }
            }
        }
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

