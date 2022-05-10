package com.microservice.kotlinmicroservice.chapter03.model

import com.fasterxml.jackson.annotation.JsonInclude

//@JsonInclude(JsonInclude.Include.NON_NULL)  // null 아닌 값만 직렬화 (application.yml에서 전역적으로 설정)
data class Customer (var id: Int = 0, var name: String = "", var telephone: Telephone? = null) {
    // 내부 클래스
    data class Telephone(var countryCode: String = "", var telephoneNumber: String = "")
}