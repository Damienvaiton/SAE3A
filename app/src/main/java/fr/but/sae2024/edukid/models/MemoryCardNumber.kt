package fr.but.sae2024.edukid.models

class MemoryCardNumber(value: String?, private var drawableImage: Int) : MemoryCard(value) {

    constructor(memoryCardNumber: MemoryCardNumber) : this(memoryCardNumber.getValueCard(), memoryCardNumber.getDrawableImage())

    override fun getDrawableImage(): Int {
        return drawableImage
    }

    override fun getFont(): Int {
        return 0 // À remplacer par la logique appropriée si nécessaire
    }

    override fun setDrawableImage(drawableImage: Int) {
        this.drawableImage = drawableImage
    }

    override fun setFont(font: Int) {
        // Implémentez cette méthode si nécessaire
    }
}

