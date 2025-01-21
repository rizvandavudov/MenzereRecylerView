package com.example.menzererecylerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ManzaraAdapter(tumManzaralar: ArrayList<Manzara>) :
    RecyclerView.Adapter<ManzaraAdapter.ManzaraViewHolder>() {


    var manzaralar = tumManzaralar


    override fun getItemCount(): Int {
        Log.e("Recyclerview", "getItemCount Basladi")
        return manzaralar.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManzaraViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var tekSatirmanzara = inflater.inflate(R.layout.tek_setir_menzere, parent, false)
        Log.e("Recyclerview", "onCreateViewHolder Basladi")
        return ManzaraViewHolder(tekSatirmanzara)
    }


    override fun onBindViewHolder(holder: ManzaraViewHolder, position: Int) {
        var oanOlanManzara = manzaralar.get(position)
        holder.setData(oanOlanManzara, position)
        /*  holder.manzaraBaslik.text = manzaralar.get(position).baslik
          holder.manzaraAciklama.text = manzaralar.get(position).aciklama
          holder.manzaraResim.setImageResource(manzaralar.get(position).resim)
          Log.e("Recyclerview", "onBindViewHolder Basladi")*/
    }

    inner class ManzaraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        // findViewById ilə XML elementlərini bağlayırıq
        var tekSatirManzara: CardView = itemView.findViewById(R.id.tekSatirManzara)
        var manzaraBaslik: TextView = itemView.findViewById(R.id.tvManzaraBaslik)
        var manzaraAciklama: TextView = itemView.findViewById(R.id.tvManzaraAciklama)
        var manzaraResim: ImageView = itemView.findViewById(R.id.imgManzara)
        var btnKopyala: ImageView = itemView.findViewById(R.id.imgKopyala)
        var btnSil: ImageView = itemView.findViewById(R.id.imgSil)


        init {
            Log.e("Recyclerview", "Manzara View Holder Basladi")

        }

        fun setData(oanOlanManzara: Manzara, position: Int) {
            manzaraBaslik.text = oanOlanManzara.baslik
            manzaraAciklama.text = oanOlanManzara.aciklama
            manzaraResim.setImageResource(oanOlanManzara.resim)

            btnKopyala.setOnClickListener {
                manzaralar.add(position, oanOlanManzara)
                notifyItemInserted(position)
                notifyItemRangeChanged(position, manzaralar.size)
            }

            btnSil.setOnClickListener {
                Log.e(
                    "Rizvan",
                    "Silinmeden evvel Item pos :" + position + " List size :" + manzaralar.size
                )
                manzaralar.removeAt(position)
                Log.e(
                    "Rizvan",
                    "Silinmeden sonra Item pos :" + position + " List size :" + manzaralar.size
                )
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, manzaralar.size)
            }
        }
    }

}