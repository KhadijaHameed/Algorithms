package com.example.algorithms

import android.annotation.SuppressLint
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var seeDesc: TextView
    private lateinit var btnBubbleSort: Button
    private lateinit var btnQuickSort: Button
    private lateinit var btnMergeSort: Button
    private lateinit var tvSortedArray: TextView
//    private val bubbleSortList = intArrayOf(8, 2, 5, 30, 26, 55)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this, R.color.dark_blue)

        setListeners()
    }


    var desc = " "
    var bubbleSortedText = " "
    var quickSortedText = " "
    var MergeSortedText = " "

    @SuppressLint("SetTextI18n")
    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
                R.id.bubble_sort -> {
                    tvSortedArray.text = ""
                    desc = " "
                    seeDesc.text = ""
                    seeDesc.text = "Click to see description  "
                    desc += "STEPS: \n "
                    desc += "-----------\n "
                    bubbleSortedText = " "

                    val list = bubbleSort(intArrayOf(8, 2, 5, 30, 26, 55, 46, 16))
                    bubbleSortedText += "["
                    desc += "\n["
                    for (k in list) {
                        desc += "$k,"
                        bubbleSortedText += "$k, "
                        Log.d("test", "bubbleSort $k")
                    }

                    desc += "]\n"
                    bubbleSortedText += "]"
                    tvSortedArray.text = bubbleSortedText
                    desc += "\n===SORTED==="
                }
                R.id.quick_sort -> {
                    tvSortedArray.text = ""
                    seeDesc.text = ""
                    desc = " "
                    seeDesc.text = "Click to see description  "
                    desc += "STEPS: \n "
                    desc += "-----------\n "
                    quickSortedText = " "

//                   val numbers = listOf(10, 2, 1, 16, 6, 8, 73, 99, 3, 16, 5, 9)
                   val numbers = listOf(8, 2, 5, 30, 26, 55, 46, 16)
                    desc += "$numbers \n "
                    println(numbers)
                    val ordered = quickSort(numbers)


                    desc += "\n\n$ordered \n"
                    desc += "\n===SORTED==="
                    Log.d("test", "quickSort $ordered")
                    quickSortedText = "$ordered \n "
                    tvSortedArray.text = quickSortedText
                }
                R.id.merge_sort -> {
                    tvSortedArray.text = ""
                    seeDesc.text = ""
                    desc = " "
                    seeDesc.text = "Click to see description  "
                    desc += "STEPS: \n "
                    desc += "-----------\n "
                    MergeSortedText = " "

                    val numbersm = mutableListOf(8, 2, 5, 30, 26, 55, 46, 16)
                    //val numbersm = mutableListOf(63, 50, 13, 85, 3, 2, 4, 9, 18, 7, 27)
                    desc += "$numbersm \n"
                    val sortedList_ = mergeSort(numbersm)
                    desc += "\n===SORTED==="
                    MergeSortedText = "$sortedList_ \n "
                    tvSortedArray.text = MergeSortedText

                }

                R.id.see_description -> {
                    seeDesc.text = " $desc"
                }

            }
        }

    }

    private fun setListeners() {
        btnBubbleSort = findViewById<Button>(R.id.bubble_sort)
        btnQuickSort = findViewById<Button>(R.id.quick_sort)
        btnMergeSort = findViewById<Button>(R.id.merge_sort)
        seeDesc = findViewById(R.id.see_description)
        tvSortedArray = findViewById(R.id.sorted_array)

        btnBubbleSort.setOnClickListener(this)
        btnQuickSort.setOnClickListener(this)
        btnMergeSort.setOnClickListener(this)
        seeDesc.setOnClickListener(this)
    }


    fun bubbleSort(arr: IntArray): IntArray {
        var swap = true
        while (swap) {
            swap = false
            for (i in 0 until arr.size - 1) {
                if (arr[i] > arr[i + 1]) {
                    desc += "\n if ${arr[i]} > ${arr[i + 1]}"
                    val temp = arr[i]
                    arr[i] = arr[i + 1]
                    arr[i + 1] = temp
                    desc += "\n Now, swap"
                    desc += "\n ${arr[i]},  ${arr[i + 1]} \n"
                    swap = true
                } else {
                    desc += "\n if ${arr[i]} > ${arr[i + 1]}"
                    desc += "\n No need to swap"
                    desc += "\n ${arr[i]},  ${arr[i + 1]} \n"
                }
            }
        }
        return arr
    }

    fun quickSort(items: List<Int>): List<Int> {

        if (items.count() < 2) {
            // desc += "\n $items" //alg alg 2 6 9 1 0
            return items
        }
        desc += "\n\nNow, Sort from these=> $items\n"
        val pivot = items[items.count() / 2]
        desc += "\n Now,I Chose Pivot= $pivot\n"

        val equal = items.filter { it == pivot }
        //desc += "equal: $equal\n"
//    println("pivot value is : "+equal)

        val less = items.filter { it < pivot }
        desc += "< pivot digits: $less \n"
//    println("Lesser values than pivot : "+less)

        val greater = items.filter { it > pivot }
        desc += "  > pivot  digits: $greater \n"
//    println("Greater values than pivot : "+greater)
        //  var quickreturn = "sort  ${quickSort(less) + equal + quickSort(greater)}"
        desc += "Sort elements::  ${quickSort(less) + equal + quickSort(greater)}"
        return quickSort(less) + equal + quickSort(greater)
    }

    fun mergeSort(list: List<Int>): List<Int> {
        if (list.size <= 1) {
            return list
        }

        //3, 2, 4,       9, 18, 7
        val middle = list.size / 2
        desc += "\n Again, Divide elements into two:"//$middle digits"
        var left = list.subList(0, middle)
        desc += "\nLEFT: $left"
        var right = list.subList(middle, list.size);
        desc += "       RIGHT: $right \n"

        return merge(mergeSort(left), mergeSort(right))
    }

    fun merge(left: List<Int>, right: List<Int>): List<Int> {
        var indexLeft = 0
        var indexRight = 0
        var newList: MutableList<Int> = mutableListOf()

        while (indexLeft < left.count() && indexRight < right.count()) {
            if (left[indexLeft] <= right[indexRight]) {
                newList.add(left[indexLeft])
                indexLeft++
            } else {
                newList.add(right[indexRight])
                indexRight++
            }
        }

        while (indexLeft < left.size) {
            newList.add(left[indexLeft])
            indexLeft++
        }
//        desc +="\n-->> $newList"
        while (indexRight < right.size) {
            newList.add(right[indexRight])
            indexRight++
        }
        desc += "\n $newList\n"
        return newList
    }
}