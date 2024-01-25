package fr.but.sae2024.edukid.models

abstract class MemoryCard {
    var value: String? = null

    private var hidden = true
    private var nbReturn = 0

    constructor(value: String?) {
        this.value = value
    }

    constructor(memoryCard: MemoryCard) {
        value = memoryCard.getValue()
    }

    //getter
    fun isHidden(): Boolean {
        return hidden
    }

    abstract fun getDrawableImage(): Int
    abstract fun getFont(): Int

    fun getValue(): String? {
        return value
    }

    //setter
    fun setHidden(hidden: Boolean) {
        if (hidden) {
            nbReturn++
        }
        this.hidden = hidden
    }

    abstract fun setDrawableImage(drawableImage: Int)
    abstract fun setFont(font: Int)

    fun setValue(value: String?) {
        this.value = value
    }

    fun getNbReturn(): Int {
        return nbReturn
    }
}