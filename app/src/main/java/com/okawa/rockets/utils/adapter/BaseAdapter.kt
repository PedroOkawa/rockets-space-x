package com.okawa.rockets.utils.adapter

import android.support.v7.widget.RecyclerView

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder> :  RecyclerView.Adapter<VH> {

    private val data = ArrayList<T>()
    protected var onItemClickListener: OnItemClickListener<T>? = null

    constructor(data: List<T>) {
        this.data.addAll(data)
    }

    constructor(): this(ArrayList<T>())

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: Collection<T>?) {
        this.data.clear()
        this.data.addAll(data?:return)
        notifyDataSetChanged()
    }

    fun add(item: T, position: Int) {
        data[position] = item
        notifyItemInserted(position)
    }

    fun getItem(position: Int): T {
        return data[position]
    }

    fun getItemPosition(item: T): Int {
        return data.indexOf(item)
    }

    fun remove(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun remove(item: T) {
        data.remove(item)
        notifyItemRemoved(getItemPosition(item))
    }

    fun reset() {
        data.clear()
        notifyDataSetChanged()
    }

    fun move(oldPosition: Int, newPosition: Int) {
        swap(data, oldPosition, newPosition)
    }

    private fun swap(data: ArrayList<T>, oldPosition: Int, newPosition: Int) {
        val item = data.removeAt(oldPosition)
        add(item, newPosition)
    }

    fun setOnItemClickListener(listener: (T) -> Unit) {
        this.onItemClickListener = object: OnItemClickListener<T> {
            override fun onClick(t: T) {
                listener.invoke(t)
            }
        }
    }

    interface OnItemClickListener<T> {

        fun onClick(t: T)

    }

}