package com.example.a7minuteworkout

class Time {
    var id: String? = null
    var tenBaiTap: String? = null
    var time = 0
    var date: String? = null

    constructor() {}
    constructor( id: String?,tenBaiTap: String?, time: Int, date: String?) {
        this.tenBaiTap = tenBaiTap
        this.time = time
        this.date = date
        this.id = id
    }
}