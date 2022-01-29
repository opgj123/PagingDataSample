package com.yhg.pagingdatasample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yhg.pagingdatasample.databinding.ItemContentBinding
import com.yhg.pagingdatasample.databinding.ItemReplyBinding
import com.yhg.pagingdatasample.model.ContentDataModel
import com.yhg.pagingdatasample.ui.adapter.diff.DIFFS

class MainAdapter() : PagingDataAdapter<ContentDataModel, RecyclerView.ViewHolder>(DIFFS.CONTENT_DIFF){

    companion object{
        const val CONTENT = 0
        const val REPLY = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if(viewType == CONTENT)
            ContentViewHolder(ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        else
            ReplyViewHolder(ItemReplyBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ContentViewHolder -> {
                val content = (getItem(position) as? ContentDataModel.ContentModel)?.data ?: return
                val context = holder.itemView.context
                with(holder.binding){
                    tvDescription.text = content.description
                    Glide.with(context)
                        .load(content.thumbnail)
                        .into(imgThumbnail)
                }
            }
            is ReplyViewHolder -> {
                val reply = (getItem(position) as? ContentDataModel.ReplyModel)?.data ?: return
                val context = holder.itemView.context
                with(holder.binding){
                    tvWriterName.text = reply.writerName
                    tvReply.text = reply.message
                    Glide.with(context)
                        .load(reply.thumbnail)
                        .circleCrop()
                        .into(imgThumbnail)
                }
            }
        }
    }

    class ContentViewHolder(val binding: ItemContentBinding) : RecyclerView.ViewHolder(binding.root)
    class ReplyViewHolder(val binding: ItemReplyBinding) : RecyclerView.ViewHolder(binding.root)


    // 화면 상단의 컨텐츠 내용과 화면 하단의 댓글 목록 구분
    override fun getItemViewType(position: Int): Int =
        when(getItem(position)){
            is ContentDataModel.ContentModel -> CONTENT
            is ContentDataModel.ReplyModel -> REPLY
            else -> throw IllegalStateException("Unknown view")
        }
}