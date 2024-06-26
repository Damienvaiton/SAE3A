package fr.but.sae2024.edukid.models.responses

import fr.but.sae2024.edukid.utils.enums.BaseError


class Response<T> {
    private var error: ArrayList<BaseError?> = arrayListOf()
    private var data: T? = null

    fun success(): Boolean {
        return error.isEmpty() && data != null
    }

    fun failed(): Boolean {
        return error.isNotEmpty()
    }

    fun error(): ArrayList<BaseError?> {
        return error
    }

    fun data(): T? {
        return data
    }

    fun addError(error: BaseError) {
        this.error.add(error)
    }

    fun addData(data: T) {
        this.data = data
    }

    override fun toString(): String {
        return "Response<>(error=$error, data=$data)"
    }
}