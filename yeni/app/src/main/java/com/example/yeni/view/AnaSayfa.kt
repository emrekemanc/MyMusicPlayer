package com.example.yeni.view

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.yeni.R
import com.example.yeni.adapter.RecyclerViewAdapter
import com.example.yeni.adapter.UserDao
import com.example.yeni.adapter.geriİleri
import com.example.yeni.database.AppDatabase
import com.example.yeni.databinding.ActivityAnaSayfaBinding
import com.example.yeni.model.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityAnaSayfaBinding
lateinit var db:AppDatabase
lateinit var userDao:UserDao
lateinit var play:MediaPlayer
lateinit var lis:List<User>
lateinit var userr: User
var composite=CompositeDisposable()
var a=true
var numara:Int=0





class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ana_sayfa)
        binding=ActivityAnaSayfaBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        play= MediaPlayer.create(this,R.raw.bip)
        binding.muzikGornum.visibility=View.GONE
        binding.muzikgorunum2.visibility=View.GONE
        room()
        recyclerView()

    }
    fun recyclerView(){
        composite.add(userDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handeler))
    }
    private fun handeler(liste:List<User>){
        binding.RecyclerView.layoutManager=LinearLayoutManager(this)
        val adapter= RecyclerViewAdapter(liste, binding,this )
        binding.RecyclerView.adapter=adapter
        lis= liste

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater=menuInflater
        inflater.inflate(R.menu.menum,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.menu1->{
                play.stop()
                val intent=Intent(this,KayitEklemeEkrani::class.java)

                startActivity(intent)

                true
            }else-> super.onOptionsItemSelected(item)

        }

    }
    fun baslatDurdur(view: View){
        if (a==true){
            play.pause()
            a=false
            binding.baslatDurdur.setImageResource(R.drawable.play)
        }else{

            play.start()
            a=true
            binding.baslatDurdur.setImageResource(R.drawable.pause)
        }
    }
    fun ileri(view: View){
        if (numara<lis.size-1){
            numara++
        }
       else if (numara>= lis.size-1){
            numara=0
        }
        geriİleri(this, binding).geriileri()

    }
    fun geri(view: View){
        if (numara>0){
            numara--
        }else if (numara<=0){
            numara= lis.size-1
        }
        geriİleri(this, binding).geriileri()

    }




    fun room(){
         db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "User"
        ).build()
       userDao = db.userDao()
    }
}