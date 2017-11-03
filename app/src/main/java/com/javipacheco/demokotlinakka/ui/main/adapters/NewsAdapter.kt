package com.javipacheco.demokotlinakka.ui.main.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.javipacheco.demokotlinakka.R
import com.javipacheco.demokotlinakka.models.Events
import kategory.ListKW
import kategory.getOrElse
import kotlinx.android.synthetic.main.news_row.view.*

class NewsAdapter(val items: ListKW<Events.RedditNewsDataEvent>, val onClick: (String) -> Unit): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun populate(item: Events.RedditNewsDataEvent) {
            itemView.news_title.text = item.title
            itemView.news_author.text = item.author
            itemView.news_comments.text = item.num_comments.toString()
            item.imageUrl.map { url ->
                Glide
                        .with(itemView.context)
                        .load(url)
                        .error(R.drawable.ic_error_image)
                        .centerCrop()
                        .into(itemView.news_photo)
            }.getOrElse {
                itemView.news_photo.setImageResource(R.drawable.ic_missing_image)
            }

            itemView.cv.setOnClickListener { v ->
                onClick(item.url)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.populate(items.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(
                parent?.getContext()).inflate(R.layout.news_row,
                parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

}