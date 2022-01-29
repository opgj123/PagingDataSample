package com.yhg.pagingdatasample.model

data class ContentReply(
    var id: Long = 0, // 댓글 고유 번호
    var message: String = "", // 댓글 작성 내용
    var writerName: String = "", // 댓글 작성자
    var thumbnail: String ="", // 댓글 작성자 이미지 주소
)
