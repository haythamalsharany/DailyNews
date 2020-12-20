package com.alsharany.dailynews


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alsharany.dailynews.models.News
import java.util.*


private val TAP_INDEX="IndexOfTap"
class GeneralNewsList : Fragment() {
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var typeViewModel: TypeViewModel
    private lateinit var categoryViewModel: CategoryViewModel
    lateinit var newsRecycelerView:RecyclerView
    lateinit var test:TextView
    var tabIndex:Int = 0
    private var adapter = NewsAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tabIndex=  it.getInt(TAP_INDEX)
            MainActivity.categoryId=tabIndex

        }
        newsViewModel =
            ViewModelProviders.of(this).get(NewsViewModel::class.java)
      typeViewModel = ViewModelProviders.of(this).get(TypeViewModel::class.java)

        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_general_news_list, container, false)

        newsRecycelerView=view.findViewById(R.id.news_recycler) as RecyclerView
        newsRecycelerView.layoutManager=LinearLayoutManager(context)
        newsRecycelerView.adapter=adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.newsLiveData.observe(
            this,
            Observer {
                newsRecycelerView.adapter=NewsAdapter(it)

            }
        )
    }

    companion object {

        @JvmStatic
        fun newInstance(tapIndex:Int) =
            GeneralNewsList().apply {
                arguments = Bundle().apply {

                    putInt(TAP_INDEX,tapIndex)

                }
            }
    }
    private inner class NewsHolder(view: View) : RecyclerView.ViewHolder(view) ,View.OnClickListener {
        val magButton = view.findViewById(R.id.news_image) as ImageButton
        val titleTextView = view.findViewById(R.id.title) as TextView
        val contentTextView = view.findViewById(R.id.content_part) as TextView
        val dateTextView = view.findViewById(R.id.date) as TextView

        var news=News()
        fun bindData(news:News){
            this.news=news
            titleTextView.text=this.news.title
            contentTextView.text=this.news.details.substring(0,20)
            dateTextView.text=setDateAndTime(this.news.date)
        }
        fun setDateAndTime(dateTime: Date):String {
            val calendar = Calendar.getInstance()
            calendar.time = dateTime

            val stringDate = "${calendar.get(Calendar.YEAR)}-" +
                    "${calendar.get(Calendar.MONTH)}-" +
                    "${calendar.get(Calendar.DAY_OF_MONTH)}"+
                    "${calendar.get(Calendar.HOUR_OF_DAY)}"+
                    "${calendar.get(Calendar.MINUTE)}"
            return stringDate
        }


        override fun onClick(v: View?) {

        }
    }
    private inner class NewsAdapter(val newsList:List<News>):RecyclerView.Adapter<NewsHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
            var view: View
            view = layoutInflater.inflate(
                R.layout.news_list,
                parent, false
            )
            return NewsHolder(view)

        }

        override fun onBindViewHolder(holder: NewsHolder, position: Int) {
            val news=newsList[position]
            holder.bindData(news)

        }

        override fun getItemCount(): Int {
            return newsList.size
        }

    }

    }