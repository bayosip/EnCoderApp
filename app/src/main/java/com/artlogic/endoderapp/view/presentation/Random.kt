package com.artlogic.endoderapp.view.presentation

class Random {

    fun doStuff(){
        val s ="0000"
        val m = mutableMapOf<Char, Int>()
        s.forEach{
            m[it] = m[it]?.plus(1)?:1
        }
    }
}