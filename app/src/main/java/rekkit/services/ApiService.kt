package rekkit.services

import akme.*
import kategory.*
import rekkit.api.RedditApi
import rekkit.models.States
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiService {

    private val redditApi: RedditApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        redditApi = retrofit.create(RedditApi::class.java)
    }

    fun getNews(limit: Int, before: Option<String> = Option.None): Service<ListKW<States.NewsItemState>> =
            redditApi.getTop(
                    before.getOrElse { "" },
                    limit.toString()).toService().map { ListKW(it.data.children).map { it.data.toNewsItemState() } }

}