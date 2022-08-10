package com.example.algorithms

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
    private val list = bubbleSort(intArrayOf(2,3,45,85,33,12,69,5,1,11,43,55))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this,R.color.dark_blue)

        setListeners()
    }


    var desc = " "
    var bubbleSortedText = " "
    var quickSortedText = " "
    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
                R.id.bubble_sort ->{
//                    seeDesc.visibility= VISIBLE
                    seeDesc.text = "Click to see description  "
                    desc += "Steps: \n\n "
                    desc += " $list "

                    bubbleSortedText += "["
                    for (k in list){
                        print("$k ")
                        desc += "\n $k  \n"
                        bubbleSortedText += "$k, "
                        Log.d("test", "bubbleSort $k")
                    }
                    bubbleSortedText += "]"

                    tvSortedArray.text = bubbleSortedText
                    desc += " SORTED"
                }
                R.id.quick_sort ->{
//                    seeDesc.visibility= VISIBLE
                    seeDesc.text = "Click to see description  "

                    desc += "STEPS: \n "
                    val numbers = listOf<Int>(2, 6, 9,  1, 0,22,65,5)
                    desc += "$numbers \n "
                    println(numbers)
                    val ordered =  quickSort(numbers)
                    println(ordered)
                    desc += "$ordered \n"
                    desc += " SORTED"
                    Log.d("test", "quickSort $ordered")
                    quickSortedText += "$ordered \n "

                    tvSortedArray.text = quickSortedText
                }
                R.id.merge_sort ->{
//                    seeDesc.visibility= VISIBLE
                    seeDesc.text = "Click to see description  "
                    desc += "Steps: \n "
                    val numbersm = mutableListOf(38,27,43,3,9,82,10)
                    desc += "$numbersm \n"
                    val sortedList = mergeSort(numbersm)
                    desc += "$sortedList \n"
                    desc += " SORTED"
                    println("Unsorted: $numbersm")
                    println("Sorted: $sortedList")
                    Log.d("test", "mergeSort $sortedList")
                }
                R.id.see_description ->{
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


    fun bubbleSort(arr:IntArray):IntArray{
        var swap = true
        while(swap){
            swap = false
            for(i in 0 until arr.size-1){
                if(arr[i] > arr[i+1]){
                    val temp = arr[i]
                    arr[i] = arr[i+1]
                    arr[i + 1] = temp

                    swap = true
                }
            }
        }
        return arr
    }

    fun quickSort(items:List<Int>):List<Int>{

        if (items.count() < 2){
           // desc += "\n $items" //alg alg 2 6 9 1 0
            return items
        }
        val pivot = items[items.count()/2]
        desc += "I Chose Pivot= $pivot\n"

        val equal = items.filter { it == pivot }
        //desc += "equal: $equal\n"
//    println("pivot value is : "+equal)

        val less = items.filter { it < pivot }
        desc += "< pivot $less"
//    println("Lesser values than pivot : "+less)

        val greater = items.filter { it > pivot }
        desc += "  > pivot $greater \n"
//    println("Greater values than pivot : "+greater)
        var quickreturn = "sort  ${quickSort(less) + equal + quickSort(greater)}"
        desc += quickreturn
        return quickSort(less) + equal + quickSort(greater)
    }

    fun mergeSort(list: List<Int>): List<Int> {
        if (list.size <= 1) {
            return list
        }

        val middle = list.size / 2
        var left = list.subList(0,middle);
        var right = list.subList(middle,list.size);

        return merge(mergeSort(left), mergeSort(right))
    }
    fun merge(left: List<Int>, right: List<Int>): List<Int>  {
        var indexLeft = 0
        var indexRight = 0
        var newList : MutableList<Int> = mutableListOf()

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

        while (indexRight < right.size) {
            newList.add(right[indexRight])
            indexRight++
        }
        return newList;
    }
}