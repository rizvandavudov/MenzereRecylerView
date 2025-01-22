package com.example.menzererecylerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {

    var tumManzaralar = ArrayList<Manzara>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerViewManzara = findViewById<RecyclerView>(R.id.recyclerViewManzara)
        veriKaynaginiDoldur()

        var myAdapter = ManzaraAdapter(tumManzaralar)
        recyclerViewManzara.adapter = myAdapter

        var linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewManzara.layoutManager = linearLayoutManager

        val swipeGesture = object : SwipeGesture(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {

                    ItemTouchHelper.LEFT -> {
                        myAdapter.deleteItem(viewHolder.adapterPosition)
                    }

                    ItemTouchHelper.RIGHT -> {
                        val position = viewHolder.adapterPosition
                        val archiveItem = tumManzaralar[position]
                        myAdapter.addItem(position + 1, archiveItem.copy())
                        myAdapter.notifyItemInserted(position + 1)
                    }
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeGesture)
        itemTouchHelper.attachToRecyclerView(recyclerViewManzara)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.anamenu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val recyclerViewManzara = findViewById<RecyclerView>(R.id.recyclerViewManzara)
        var id = item.itemId

        when (id) {

            R.id.menulinearViewHorizontal -> {
                var linearLayoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                recyclerViewManzara.layoutManager = linearLayoutManager
            }

            R.id.menulinearViewVertical -> {

                var menulinearViewVertical =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                recyclerViewManzara.layoutManager = menulinearViewVertical
            }

            R.id.menuGrid -> {
                var menuGrid = GridLayoutManager(this, 2)
                recyclerViewManzara.layoutManager = menuGrid
            }

            R.id.menuStaggeredHorzontal -> {
                var menuStaggeredHorzontal =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
                recyclerViewManzara.layoutManager = menuStaggeredHorzontal
            }

            R.id.menuStaggeredVertical -> {
                var menuStaggeredVertical =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                recyclerViewManzara.layoutManager = menuStaggeredVertical
            }
        }


        return super.onOptionsItemSelected(item)
    }

    fun veriKaynaginiDoldur(): ArrayList<Manzara> {
        var tumREsimler = arrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.l,
            R.drawable.m,
            R.drawable.n,
            R.drawable.v,
            R.drawable.x,

            )
        for (i in 0..tumREsimler.size - 1) {
            var eklenecekManzara = Manzara("Manzara" + i, "Aciklama" + i, tumREsimler[i])
            tumManzaralar.add(eklenecekManzara)
        }
        return tumManzaralar
    }
}