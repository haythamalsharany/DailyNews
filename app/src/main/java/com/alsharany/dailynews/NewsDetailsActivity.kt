package com.alsharany.dailynews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alsharany.dailynews.models.News
import kotlinx.android.synthetic.main.fragment_news_details.*
import java.text.DateFormat

class NewsDetailsActivity : AppCompatActivity() {
    private lateinit var news: News
    var newsId=0
    private val newsDetailViewModel: NewsDetailsViewModel by lazy {
        ViewModelProviders.of(this).get(NewsDetailsViewModel::class.java)
    }
    lateinit var newsImageView: ImageView
    lateinit var neweTitleTextView: TextView
    lateinit var neweDateTextView: TextView
    lateinit var neweContentTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        newsImageView=findViewById(R.id.neswImag_IMGv)
        neweTitleTextView=findViewById(R.id.title_tv)
        neweDateTextView=findViewById(R.id.news_date)
        neweContentTextView=findViewById(R.id.news_content)
        news=News()
        val intent=getIntent().apply {
            newsId=this.getIntExtra("NEWSID",0)

        }


        newsDetailViewModel.loadNews(newsId)
    }

    override fun onStart() {
        super.onStart()
        newsDetailViewModel.newsDetailsLiveData.observe(
            this,
            Observer {
                this.news=it
                updateUI()
            }
        )
    }
    private fun updateUI() {
        neweTitleTextView.text=this.news.title
        neweDateTextView.text= DateFormat.getDateInstance(DateFormat.LONG)
            .format(this.news.date).toString()
        news_content.text=this.news.details
    }
}