package com.example.a7minuteworkout.chart

class BarData {
    var barTitle: String? = null
    var barValue = 0f
    var pinText: String? = null

    constructor(barTitle: String?, barValue: Float) {
        this.barTitle = barTitle
        this.barValue = barValue
    }

    constructor(barTitle: String?, barValue: Float, pinText: String?) {
        this.barTitle = barTitle
        this.barValue = barValue
        this.pinText = pinText
    }

    constructor() {}
}