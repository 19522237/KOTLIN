package com.example.a7minuteworkout

class historyModel (
    private var id:Int,
    private var name: String,
    private var image: Int,
    private var time: String,
    )
{
    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getImage(): Int {
        return image
    }

    fun setImage(image: Int) {
        this.image = image
    }
    fun getTime():String{
        return time
    }
    fun setTime(time: String){
        this.time = time
    }
}
