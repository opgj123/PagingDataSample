package com.yhg.pagingdatasample.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import androidx.paging.cachedIn
import androidx.paging.insertHeaderItem
import androidx.paging.map
import com.yhg.pagingdatasample.model.Content
import com.yhg.pagingdatasample.model.ContentDataModel
import com.yhg.pagingdatasample.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val mainRepository: MainRepository
) : AndroidViewModel(context as Application){

    private val contentData = MutableLiveData<Content>()

    init{
        contentData.value = Content(description = "컨텐츠 상세 내용입니다", thumbnail = "https://static-cse.canva.com/blob/758489/1600w-rssvAb9JL4I.jpg")
    }

    val contentDataList = contentData.switchMap {
        mainRepository.getContentList(it.id).flow
            .map{ pagingData ->
                pagingData.map {
                    replyData -> ContentDataModel.ReplyModel(replyData) as ContentDataModel
                }
                    .insertHeaderItem(
                        item = ContentDataModel.ContentModel(it)
                    )
            }
            .cachedIn(viewModelScope)
            .asLiveData(Dispatchers.IO)
    }
}