package com.caellum.twitteelum.adapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView;
import com.caellum.twitteelum.databinding.ItemTweetBinding
import com.caellum.twitteelum.extensions.Carregador

import com.caellum.twitteelum.modelo.Tweet;


public class TweetAdapter (private val tweets:List<Tweet>) : BaseAdapter() {
    override fun getCount(): Int {
        return tweets.size
    }

    override fun getItem(position: Int): Any {
        return tweets[position]
    }

    override fun getItemId(position: Int): Long {
        return tweets[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val tweet = tweets[position]
        val inflater = LayoutInflater.from(parent?.context)
        val binding = ItemTweetBinding.inflate(inflater, parent,false)
        binding.itemTweetText.text = tweet.conteudo
        tweet.foto?.let {
            binding.itemTweetFoto.visibility = View.VISIBLE
            binding.itemTweetFoto.setImageBitmap(Carregador.decodifica(it))
        }
        return binding.root

    }



}
