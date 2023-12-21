package fr.but.sae2024.edukid.models

import timber.log.Timber
import kotlin.math.pow
import kotlin.math.sqrt

class Symbol {

    private var points: ArrayList<Point>? = null
    private var tolerance = 0f
    private var lastId = -1
    private var lastIdCustom = -1
    private var isPastByTheMiddle = false


    constructor() {
        tolerance = 30f
    }

    constructor(tolerance: Int) {
        points = ArrayList()
        this.tolerance = tolerance.toFloat()
    }

    constructor(points: ArrayList<Point>?) {
        this.points = points
        tolerance = 10f
    }

    constructor(p: ArrayList<Point>?, t: Float) {
        points = p
        tolerance = t
    }

    private fun findIdOfNearestPoint(point: Point): Int {
        var idNearestPoint: Int
        if (points!!.isEmpty()) {
            return -1
        } else {
            var dist = sqrt(
                (point.x - points!![0].x).pow(2.0) + Math.pow(
                    point.y - points!![0].y,
                    2.0
                )
            )
            idNearestPoint = 0

            for (i in points!!.indices) {
                if (sqrt(
                        (point.x - points!![i].x).pow(2.0) + Math.pow(point.y - points!![i].y, 2.0)
                    ) <= dist
                ) {
                    idNearestPoint = i
                    dist = sqrt(
                        (point.x - points!![i].x).pow(2.0) + Math.pow(point.y - points!![i].y, 2.0)
                    )
                }
            }
        }

        return idNearestPoint
    }

    private fun findIdOfNearestPointExept(point: Point, exception: Int): Int {
        var idSecondNearestPoint = 0
        if (points!!.isEmpty() || points!!.size == 1) {
            return -1
        } else {
            var dist = sqrt(
                (point.x - points!![0].x).pow(2.0) + (point.y - points!![0].y).pow(2.0)
            )
            for (i in points!!.indices) {
                if (sqrt(
                        (point.x - points!![i].x).pow(2.0) + Math.pow(point.y - points!![i].y, 2.0)
                    ) <= dist && i != exception
                ) {
                    idSecondNearestPoint = i
                    dist = sqrt(
                        (point.x - points!![i].x).pow(2.0) + Math.pow(point.y - points!![i].y, 2.0)
                    )
                }
            }
        }
        return idSecondNearestPoint
    }

    private fun findIdOfNearestPointExept(point: Point, exception: Int, secondException: Int): Int {
        var idSecondNearestPoint = 0
        if (points!!.isEmpty() || points!!.size == 1) {
            return -1
        } else {
            var dist = sqrt(
                (point.x - points!![0].x).pow(2.0) + (point.y - points!![0].y).pow(2.0)
            )
            for (i in points!!.indices) {
                if (sqrt(
                        (point.x - points!![i].x).pow(2.0) + (point.y - points!![i].y).pow(2.0)
                    ) <= dist && i != exception && i != secondException
                ) {
                    idSecondNearestPoint = i
                    dist = sqrt(
                        (point.x - points!![i].x).pow(2.0) + (point.y - points!![i].y).pow(2.0)
                    )
                }
            }
        }
        return idSecondNearestPoint
    }

    private fun getDistanceBetweenTwoPoints(p1: Point, p2: Point): Double {
        return sqrt((p1.x - p2.x).pow(2.0) + (p1.y - p2.y).pow(2.0))
    }


    private fun isInArea(point: Point, p1: Point, p2: Point): Boolean {
        val inter: Point
        val m: Double
        val b: Double
        val m2: Double
        val b2: Double
        val x: Double
        if (p2.x - p1.x == 0.0) {
            m = 0.0
            m2 = 0.0
        } else {
            m = (p2.y - p1.y) / (p2.x - p1.x)
            m2 = 1 / (m * -1)
        }
        b = p1.y - m * p1.x
        b2 = point.y - m2 * point.x
        x = if (m - m2 == 0.0) {
            b2 - b
        } else {
            (b2 - b) / (m - m2)
        }
        inter = Point(x, m * x + b)
        if (getDistanceBetweenTwoPoints(inter, point) <= tolerance) {
            if (getDistanceBetweenTwoPoints(point, p1) < getDistanceBetweenTwoPoints(
                    p1,
                    p2
                ) + tolerance && getDistanceBetweenTwoPoints(
                    point,
                    p2
                ) < getDistanceBetweenTwoPoints(p1, p2) + tolerance
            ) {
                return true
            }
        }
        return false
    }

    fun isInSymbol(point: Point): Boolean {
        val idOfNearestPoints = ArrayList<Int>()
        idOfNearestPoints.add(findIdOfNearestPoint(point))
        idOfNearestPoints.add(findIdOfNearestPointExept(point, idOfNearestPoints[0]))
        idOfNearestPoints.add(
            findIdOfNearestPointExept(
                point, idOfNearestPoints[0],
                idOfNearestPoints[1]
            )
        )
        Timber.tag("pts").e("%s", idOfNearestPoints[0])
        Timber.tag("pts").e("%s", idOfNearestPoints[1])
        Timber.tag("pts").e("%s", idOfNearestPoints[2])
        if (lastId == -1) {
            lastId = idOfNearestPoints[0]
        } else if (lastId != idOfNearestPoints[0] - 1 && lastId != idOfNearestPoints[0] && lastId != idOfNearestPoints[1] - 1 && lastId != idOfNearestPoints[1]) {
            return false
        }
        if (idOfNearestPoints[0] == points!!.size / 2) {
            isPastByTheMiddle = true
        }
        lastId = idOfNearestPoints[0]
        if (idOfNearestPoints[0] == -1) {
            return false
        }
        for (id in idOfNearestPoints) {
            if (id >= 1) {
                if (isInArea(point, points!![id - 1], points!![id])) {
                    return true
                }
            }
            if (id + 1 < points!!.size) {
                if (isInArea(point, points!![id], points!![id + 1])) {
                    return true
                }
            }
        }
        return false
    }

    fun isInArea(point: Point, p1: Point, p2: Point, tol: Float): Boolean {
        val inter: Point
        val m: Double
        val b: Double
        val m2: Double
        val b2: Double
        val x: Double
        if (p2.x - p1.x == 0.0) {
            m = 0.0
            m2 = 0.0
        } else {
            m = (p2.y - p1.y) / (p2.x - p1.x)
            m2 = 1 / (m * -1)
        }
        b = p1.y - m * p1.x
        b2 = point.y - m2 * point.x
        x = if (m - m2 == 0.0) {
            b2 - b
        } else {
            (b2 - b) / (m - m2)
        }
        inter = Point(x, m * x + b)
        if (getDistanceBetweenTwoPoints(inter, point) <= tol) {
            if (getDistanceBetweenTwoPoints(point, p1) < getDistanceBetweenTwoPoints(
                    p1,
                    p2
                ) + tol && getDistanceBetweenTwoPoints(point, p2) < getDistanceBetweenTwoPoints(
                    p1,
                    p2
                ) + tol
            ) {
                return true
            }
        }
        return false
    }

    fun isInSymbol(point: Point, tol: Float): Boolean {
        val idOfNearestPoints = ArrayList<Int>()
        idOfNearestPoints.add(findIdOfNearestPoint(point))
        idOfNearestPoints.add(findIdOfNearestPointExept(point, idOfNearestPoints[0]))
        idOfNearestPoints.add(
            findIdOfNearestPointExept(
                point, idOfNearestPoints[0],
                idOfNearestPoints[1]
            )
        )
        if (lastIdCustom == -1) {
            lastIdCustom = idOfNearestPoints[0]
        } else if (lastIdCustom != idOfNearestPoints[0] - 1 && lastIdCustom != idOfNearestPoints[0] && lastIdCustom != idOfNearestPoints[1] - 1 && lastIdCustom != idOfNearestPoints[1]) {
            return false
        }
        lastIdCustom = idOfNearestPoints[0]
        if (idOfNearestPoints[0] == points!!.size / 2) {
            isPastByTheMiddle = true
        }
        if (idOfNearestPoints[0] == -1) {
            return false
        }
        for (id in idOfNearestPoints) {
            if (id >= 1) {
                if (isInArea(point, points!![id - 1], points!![id], tol)) {
                    return true
                }
            }
            if (id + 1 < points!!.size) {
                if (isInArea(point, points!![id], points!![id + 1], tol)) {
                    return true
                }
            }
        }
        return false
    }

    fun clearAllLastId() {
        lastId = -1
        lastIdCustom = -1
        isPastByTheMiddle = false
    }

    fun getFirstPoint(): Point? {
        return points!![0]
    }

    fun getLastPoint(): Point? {
        return points!![points!!.size - 1]
    }

    fun isPastByTheMiddle(): Boolean {
        return isPastByTheMiddle
    }

    fun getTolerance(): Float {
        return tolerance
    }

    fun getPoints(): ArrayList<Point>? {
        return points
    }

    fun getLastId(): Int {
        return lastId
    }

    fun getLastIdCustom(): Int {
        return lastIdCustom
    }

}