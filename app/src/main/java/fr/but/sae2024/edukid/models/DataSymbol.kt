package fr.but.sae2024.edukid.models

class DataSymbol {

    private var pts : ArrayList<Point> = arrayListOf()
    private var nbTrait : ArrayList<ArrayList<Int>> = arrayListOf()

    fun getPts(): ArrayList<Point> {
        return pts
    }

    fun initPts(num: Int, width: Float, height: Float) {
        pts.clear()
        nbTrait.clear()
        for (i in 0..9) {
            nbTrait.add(ArrayList())
        }
        nbTrait[1].add(7)
        nbTrait[2].add(8)
        nbTrait[3].add(11)
        nbTrait[4].add(4)
        nbTrait[4].add(6)
        nbTrait[5].add(15)
        nbTrait[6].add(17)
        nbTrait[7].add(2)
        nbTrait[8].add(13)
        nbTrait[9].add(15)
        nbTrait[4][0] = 4
        nbTrait[4].add(6)
        when (num) {
            1 -> {
                pts.add(Point(0.33395004, 0.44072443))
                pts.add(Point(0.40888068, 0.38))
                pts.add(Point(0.5013876, 0.32))
                pts.add(Point(0.506938, 0.41268623))
                pts.add(Point(0.493062, 0.48991424))
                pts.add(Point(0.4949121, 0.55484486))
                pts.add(Point(0.48843667, 0.6276458))
                pts.add(Point(0.50416285, 0.69552773))
            }

            2 -> {
                pts.add(Point(0.35522664, 0.3693992))
                pts.add(Point(0.45143387, 0.31))
                pts.add(Point(0.59111935, 0.305))
                pts.add(Point(0.6475486, 0.37))
                pts.add(Point(0.5901942, 0.447611))
                pts.add(Point(0.4014801, 0.5833749))
                pts.add(Point(0.3052729, 0.688))
                pts.add(Point(0.480111, 0.689))
                pts.add(Point(0.65402406, 0.69))
            }

            3 -> {
                pts.add(Point(0.2562442, 0.30299294))
                pts.add(Point(0.40980574, 0.295))
                pts.add(Point(0.57816833, 0.296))
                pts.add(Point(0.7280296, 0.291))
                pts.add(Point(0.64014804, 0.36005312))
                pts.add(Point(0.53, 0.44))
                pts.add(Point(0.66, 0.49))
                pts.add(Point(0.70860314, 0.5563205))
                pts.add(Point(0.68, 0.6276458))
                pts.add(Point(0.597, 0.68))
                pts.add(Point(0.43310823, 0.70))
                pts.add(Point(0.275, 0.65))
            }

            4 -> {
                pts.add(Point(0.453284, 0.25921395))
                pts.add(Point(0.2987974, 0.43187025))
                pts.add(Point(0.2114431, 0.55795244))
                pts.add(Point(0.4874653, 0.5535312))
                pts.add(Point(0.8223867, 0.55910404))
                pts.add(Point(0.6762257, 0.44284824))
                pts.add(Point(0.63552266, 0.699787))
            }

            5 -> {
                pts.add(Point(0.65, 0.2938))
                pts.add(Point(0.5610657, 0.2939))
                pts.add(Point(0.41384888, 0.2997758))
                pts.add(Point(0.27035522, 0.3071867))
                pts.add(Point(0.26480103, 0.38064557))
                pts.add(Point(0.27035522, 0.46151534))
                pts.add(Point(0.38424683, 0.44721362))
                pts.add(Point(0.5203247, 0.4486763))
                pts.add(Point(0.625885, 0.46743107))
                pts.add(Point(0.70736694, 0.5192098))
                pts.add(Point(0.7314453, 0.578367))
                pts.add(Point(0.69348145, 0.6291706))
                pts.add(Point(0.6119995, 0.674026))
                pts.add(Point(0.4981079, 0.6957061))
                pts.add(Point(0.3703308, 0.6853699))
                pts.add(Point(0.28329468, 0.6553038))
            }

            6 -> {
                pts.add(Point(0.7169288, 0.33152303))
                pts.add(Point(0.5855689, 0.28676027))
                pts.add(Point(0.47363552, 0.2774142))
                pts.add(Point(0.36872804, 0.30071747))
                pts.add(Point(0.29417208, 0.34710174))
                pts.add(Point(0.2482701, 0.40041076))
                pts.add(Point(0.223469, 0.4945092))
                pts.add(Point(0.23161886, 0.563699))
                pts.add(Point(0.27139686, 0.63173715))
                pts.add(Point(0.36077705, 0.6857974))
                pts.add(Point(0.50530992, 0.714868))
                pts.add(Point(0.66217394, 0.69653599))
                pts.add(Point(0.75115633, 0.6222349))
                pts.add(Point(0.7419057, 0.5415636))
                pts.add(Point(0.66882515, 0.4890925))
                pts.add(Point(0.5319149, 0.481714))
                pts.add(Point(0.4449584, 0.5145092))
                pts.add(Point(0.38390377, 0.5582881))
            }

            7 -> {
                pts.add(Point(0.2750971, 0.27593854))
                pts.add(Point(0.75948197, 0.2808575))
                pts.add(Point(0.42235893, 0.7002789))
            }

            8 -> {
                pts.add(Point(0.46213693, 0.27905194))
                pts.add(Point(0.3894542, 0.29364687))
                pts.add(Point(0.31117485, 0.35119897))
                pts.add(Point(0.3572063, 0.4156376))
                pts.add(Point(0.61979645, 0.49335757))
                pts.add(Point(0.692877, 0.5646828))
                pts.add(Point(0.6697502, 0.6512569))
                pts.add(Point(0.51636445, 0.7032302))
                pts.add(Point(0.32967622, 0.6933923))
                pts.add(Point(0.223469, 0.59764))
                pts.add(Point(0.35469934, 0.50794663))
                pts.add(Point(0.61239594, 0.38612372))
                pts.add(Point(0.6445513, 0.31627417))
                pts.add(Point(0.56469014, 0.28544662))
            }

            9 -> {
                pts.add(Point(0.68270123, 0.32020935))
                pts.add(Point(0.56984276, 0.28807392))
                pts.add(Point(0.4588344, 0.28971165))
                pts.add(Point(0.35152635, 0.3174258))
                pts.add(Point(0.3028492, 0.37923715))
                pts.add(Point(0.3058002, 0.4461353))
                pts.add(Point(0.37187788, 0.49778464))
                pts.add(Point(0.48196116, 0.5192603))
                pts.add(Point(0.6258714, 0.4736816))
                pts.add(Point(0.7187068, 0.40924296))
                pts.add(Point(0.75683075, 0.48056817))
                pts.add(Point(0.7520814, 0.56074756))
                pts.add(Point(0.7104533, 0.6374837))
                pts.add(Point(0.58314524, 0.70080315))
                pts.add(Point(0.40923495, 0.70218166))
                pts.add(Point(0.26922664, 0.65011107))
            }
        }
        for (p in pts) {
            p.x = p.x * width
            p.y = p.y * height
        }
    }

    fun getNbTrait(): ArrayList<ArrayList<Int>> {
        return nbTrait
    }
}