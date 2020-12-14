package com.caellum.twitteelum.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.caellum.twitteelum.R
import com.caellum.twitteelum.databinding.ActivityTweetBinding
import com.caellum.twitteelum.extensions.decodificaParaBase64
import com.caellum.twitteelum.modelo.Tweet
import com.caellum.twitteelum.vm.TweetViewModel
import com.caellum.twitteelum.vm.ViewModelFactory
import java.io.File

class TweetActivity : AppCompatActivity() {

    private var localFoto: String? = null
    lateinit var viewModel: TweetViewModel
    private lateinit var binding: ActivityTweetBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTweetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelFactory)[TweetViewModel::class.java]
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //  val botaoPublicar =findViewById<Button>(R.id.publicar_Tweet)

        //  botaoPublicar.setOnClickListener {
        //     publicaTweet()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tweet, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_salvar -> {
            publicaTweet()
            true
        }
        R.id.menu_camera -> {
            tiraFoto()
            true
        }
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }


    private fun publicaTweet() {
        //val campoConteudo = findViewById<EditText>(R.id.conteudo_Tweet)
        //val conteudo = binding.conteudoTweet.text.toString()

        val tweet = criaTweet()

        //val database = TwittelumDatabase.getDatabase(this)
        //val tweetDao = database.getTweetDao()
        //val repository = TweetRepository(tweetDao)
        viewModel.salva(tweet)


        Toast.makeText(this,"$tweet salvo com sucesso", Toast.LENGTH_LONG).show()

        finish()

    }

    fun criaTweet():Tweet{

        val mansagemDoTweet: String = binding.conteudoTweet.text.toString()
        val foto: String? = binding.tweetFoto.tag as String?
        return Tweet(mansagemDoTweet, foto)
    }

    private fun tiraFoto() {

        val abrirCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val caminhoFoto = defineLocalDaFoto()
        abrirCamera.putExtra(
            MediaStore.EXTRA_OUTPUT,
            caminhoFoto
        )
       // extra_output é a chave, o local onde vai salvar é o caminhofoto
        startActivityForResult(abrirCamera, 123)
    }

    private fun defineLocalDaFoto(): Uri? {
        localFoto =
            "${getExternalFilesDir(Environment.DIRECTORY_PICTURES)}/${System.currentTimeMillis()}.jpg"
        val arquivo = File(localFoto)
        return FileProvider.getUriForFile(this, "br.com.twittelumapp.FileProvider", arquivo)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == 123) {
                if (resultCode == Activity.RESULT_OK)
                {
                    carregaFoto()
                }
            }
    }

    private fun carregaFoto() {
        val bitmap = BitmapFactory.decodeFile(localFoto)
        val bm = Bitmap.createScaledBitmap(bitmap, 300, 300, true)
        binding.tweetCard.visibility = View.VISIBLE
        binding.tweetFoto.setImageBitmap(bm)
        val fotoNaBase64 = bm.decodificaParaBase64()
        binding.tweetFoto.tag = fotoNaBase64
        binding.tweetFoto.scaleType = ImageView.ScaleType.FIT_XY

    }




}


