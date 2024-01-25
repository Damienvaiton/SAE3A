package fr.but.sae2024.edukid.models

class MemoryCardLetter(value: String?, font: Int) : MemoryCard(value) {
    private var font = 0

    init {
        this.font = font
    }

    constructor(memoryCardLetter: MemoryCardLetter) : this(memoryCardLetter.getValueCard(), memoryCardLetter.getFont())

    override fun getDrawableImage(): Int {
        return 0 // À remplacer par l'ID de l'image appropriée
    }

    override fun getFont(): Int {
        return font
    }

    override fun setDrawableImage(drawableImage: Int) {
    }

    override fun setFont(font: Int) {
        this.font = font
    }
}