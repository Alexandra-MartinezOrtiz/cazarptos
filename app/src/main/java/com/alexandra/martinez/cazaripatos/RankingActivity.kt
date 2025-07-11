package com.alexandra.martinez.cazaripatos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RankingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        var jugadores = arrayListOf<Player>()
        jugadores.add(Player("alexandra.martinez",10))
        jugadores.add(Player("Jugador2",6))
        jugadores.add(Player("Jugador3",3))
        jugadores.add(Player("Jugador4",9))
        jugadores.sortByDescending { it.huntedDucks }

        val recyclerViewRanking: RecyclerView = findViewById(R.id.recyclerViewRanking)
        recyclerViewRanking.layoutManager = LinearLayoutManager(this)
        recyclerViewRanking.adapter = RankingAdapter(jugadores)
        recyclerViewRanking.setHasFixedSize(true)
    }
} 