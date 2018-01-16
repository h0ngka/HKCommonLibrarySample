package com.hongka.hkcommonlibrarysample.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hongka.hkcommonlibrarysample.R

/**
 * Created by jusung.kim@sk.com on 2018/01/08
 */

class KotlinActivity : AppCompatActivity() {

    companion object {
        fun makeIntent(context: Context) =
                Intent(context, KotlinActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        val set = hashSetOf(1, 7, 53)
        val list = arrayListOf(1, 7, 53)
        val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

        println(set.javaClass)
        println(list.javaClass)
        println(map.javaClass)




//        println(eval(Sum(Sum(Num(1), Num(3)), Num(4))))
//        for1()
    }

    interface Expr
    class Num(val value: Int): Expr
    class Sum(val left: Expr, val right: Expr): Expr

//    fun eval(e: Expr): Int {
//        if (e is Num) {
//            val n = e as Num;
//            return n.value
//        }
//        if (e is Sum) {
//            return eval(e.right) + eval(e.left)
//        }
//        throw IllegalArgumentException("Unkonwn expression")
//    }
//
//    fun eval(e: Expr): Int =
//        when(e) {
//            is Num -> e.value
//            is Sum -> eval(e.right) + eval(e.left)
//            else -> throw IllegalArgumentException("Unknown expression")
//        }
//
//    fun for1() {
//        val list = arrayListOf("10", "11", "1001")
//        for ((index, element) in list.withIndex()) {
//            println("$index : $element")
//        }
//    }
}