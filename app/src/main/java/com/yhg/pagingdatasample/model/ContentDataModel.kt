package com.yhg.pagingdatasample.model

import com.yhg.pagingdatasample.model.type.PagingDataType

sealed class ContentDataModel(val type: PagingDataType){
    data class ContentModel(val data: Content) : ContentDataModel(PagingDataType.HEADER)
    data class ReplyModel(val data : ContentReply) : ContentDataModel(PagingDataType.ITEM)
}
