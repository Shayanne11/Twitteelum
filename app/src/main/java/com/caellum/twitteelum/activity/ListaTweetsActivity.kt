package com.caellum.twitteelum.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.caellum.twitteelum.R
import com.caellum.twitteelum.adapter.TweetAdapter
import com.caellum.twitteelum.databinding.ActivityListaTweetsBinding
import com.caellum.twitteelum.db.TweetDao
import com.caellum.twitteelum.db.TwittelumDatabase
import com.caellum.twitteelum.modelo.Tweet
import com.caellum.twitteelum.repository.TweetRepository
import com.caellum.twitteelum.vm.TweetViewModel
import com.caellum.twitteelum.vm.ViewModelFactory

class ListaTweetsActivity : AppCompatActivity() {

    lateinit var binding: ActivityListaTweetsBinding

    private val viewModel: TweetViewModel by lazy {

         ViewModelProvider (this,ViewModelFactory)[TweetViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) { // create para criar a tela
        super.onCreate(savedInstanceState)// recria a tela ciclo da activity

        binding = ActivityListaTweetsBinding.inflate(layoutInflater)



        setContentView(binding.root)

        viewModel.busca().observe(this, observer())

        binding.listaTweets.setOnItemClickListener { adapter, view, position, id ->
            val tweet = adapter.getItemAtPosition(position) as Tweet
            perguntaSePrecisaDeletar(tweet)
        }

        binding.fabNovo.setOnClickListener {

           val intencao = Intent(this, TweetActivity::class.java)

            startActivity(intencao)

            //Toast.makeText(this, "clicou no botão", Toast.LENGTH_LONG).show()
           // Snackbar.make(it, "clicou no botão", Snackbar.LENGTH_LONG)
            //    .setAction("Desfazer") {}
             //   .show()
        }


    }
private fun perguntaSePrecisaDeletar(tweet: Tweet){
    val builder = AlertDialog.Builder(this)
    builder.setTitle("Deseja Deletar?")
    builder.setMessage("Tem certeza? vamos apagar o Tweet com conteudo: \n${tweet.conteudo}")
    builder.setPositiveButton("Sim"){_,_->
        viewModel.deleta(tweet)
    }
    builder.setNegativeButton("Não"){_,_-> }
    builder.setNeutralButton("Não sei"){_,_->}
    builder.setIcon(R.drawable.ic_alerta)
    builder.show()
}

    private fun observer(): Observer<List<Tweet>> {
        return Observer { tweets ->
            tweets?.let {
                binding.listaTweets.adapter = TweetAdapter(it)
            }
        }
    }

}