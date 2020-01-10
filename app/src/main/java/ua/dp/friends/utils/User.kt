package ua.dp.friends.utils

class User {

    var large: String? = null
    var medium: String? = null
    var thumbnail: String? = null
    var gender: String? = null
    var email: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var titleName: String? = null
    //var city: String? = null
    var phone: String? = null
    var position: Int = 0
    //var age: String? = null
    //var country: String? = null

    constructor(position: Int, email: String?, gender: String?, phone: String?, large: String?, medium: String?, thumbnail: String?,/* country: String?,*/
                firstName: String?, lastName: String?, titleName: String?/*, age: String?, city: String?*/) {

        this.position = position
        this.large = large
        this.medium = medium
        this.thumbnail = thumbnail
        this.titleName = titleName
        this.firstName = firstName
        this.lastName = lastName
        this.gender = gender
        this.email = email
        this.phone = phone
        //this.age = age
        //this.city = city
        //this.country = country
    }

    constructor()
}