package com.example.yeni.adapter


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.yeni.databinding.ActivityAnaSayfaBinding
import com.example.yeni.databinding.RecyclerViewBinding

import com.example.yeni.model.User
import com.example.yeni.view.*
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class RecyclerViewAdapter(val itemler: List<User>, val binding: ActivityAnaSayfaBinding, val context: Context ): RecyclerView.Adapter<RecyclerViewAdapter.itemHolder>() {

    class itemHolder(val binding: RecyclerViewBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemHolder {
       val binding=RecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return itemHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemler.size
    }

    override fun onBindViewHolder(holder: itemHolder, position: Int) {
        holder.binding.sarkiAdii.text=itemler.get(position).muzikAdi
        val byt=itemler.get(position).muzikGorsel
        val bitmap= BitmapFactory.decodeByteArray(byt,0,byt.size)

        holder.binding.recyclerViewGorsel.setImageBitmap(Bitmap.createScaledBitmap(bitmap,50,50,true))
        holder.itemView.setOnClickListener {
            numara=position
            geriİleri(context,binding).geriİleri(itemler.get(numara))
            binding.muzikGornum.visibility=View.VISIBLE
            binding.muzikgorunum2.visibility=View.VISIBLE
        }
        holder.itemView.setOnLongClickListener{
            Snackbar.make(it,"Silmek istediğiniz şarkı "+itemler.get(position).muzikAdi, Snackbar.LENGTH_LONG).setAction("Sil"){
                Toast.makeText(context,itemler.get(position).muzikAdi+" silindi", Toast.LENGTH_LONG).show()
              if (play.isPlaying ){  if (numara<lis.size-1){
                    numara++
                }
                else if (numara== lis.size-1){
                    numara=0
                }
                geriİleri(context,binding).geriileri()}

                composite.add(userDao.sil(lis[position].id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe())
            }.show()
            return@setOnLongClickListener true
        }



    }




}