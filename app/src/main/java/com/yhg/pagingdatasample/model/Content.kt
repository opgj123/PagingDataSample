package com.yhg.pagingdatasample.model

import java.io.Serializable

data class Content(
    var id: Long = 0, // 컨텐츠 고유 번호
    var description: String = "", // 컨텐츠 설명 내용
    var thumbnail: String = "" // 컨텐츠 이미지 주소
) : Serializable
