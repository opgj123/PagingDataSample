package com.yhg.pagingdatasample.ui.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.yhg.pagingdatasample.model.ContentDataModel

object DIFFS {
    val CONTENT_DIFF = object :DiffUtil.ItemCallback<ContentDataModel>(){
        override fun areItemsTheSame(oldItem: ContentDataModel, newItem: ContentDataModel): Boolean =
            (oldItem is ContentDataModel.ReplyModel
                    && newItem is ContentDataModel.ReplyModel
                    && oldItem.data.id == newItem.data.id)

        override fun areContentsTheSame(oldItem: ContentDataModel, newItem: ContentDataModel): Boolean =
            oldItem == newItem
    }
}