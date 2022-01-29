package com.yhg.pagingdatasample.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yhg.pagingdatasample.model.ContentReply
import com.yhg.pagingdatasample.network.MainService

class MainRepository(
    private val mainService: MainService
) {


    /* dummy Data List */
    private val dummyDataList: List<ContentReply> =
        mutableListOf<ContentReply>().apply {
            for (index in 1..20)
                add(
                    ContentReply(
                        id = index.toLong(),
                        message = "댓글 내용입니다$index",
                        writerName = "작성자이름$index",
                        thumbnail = "https://static-cse.canva.com/blob/758489/1600w-rssvAb9JL4I.jpg"
                    )
                )
        }

    fun getContentList(contentId : Long) = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false),
        initialKey = 1,
        pagingSourceFactory = {
            object : PagingSource<Int, ContentReply>() {
                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ContentReply> =
                    try {
                        val page = params.key ?: 1
                        val messageList = dummyDataList
                        LoadResult.Page(
                            data = messageList,
                            prevKey = if (page <= 1) null else page - 1,
//                            nextKey = if (messageList.isNotEmpty()) page + 1 else null
                            nextKey = if (page < 5) page + 1 else null // 5페이지까지만 나오도록 설정
                        )
                    } catch (e: Exception) {
                        LoadResult.Error(e)
                    }

                override fun getRefreshKey(state: PagingState<Int, ContentReply>): Int = 1
            }
        }
    )
}