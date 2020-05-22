package com.exercise.savemyhero.domain

interface Mapper<in K, out T> {
    fun to(from: K): T

    fun to(from: List<K>): List<T> {
        return from.map { to(it) }
    }
}