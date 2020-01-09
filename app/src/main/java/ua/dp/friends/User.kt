package ua.dp.friends

class User {

    var avatar: String? = null
    var gender: String? = null
    var email: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var titleName: String? = null
    var city: String? = null
    var phone: String? = null
    var age: String? = null
    var country: String? = null

    constructor(email: String?, gender: String?, phone: String?, avatar: String?, country: String?,
                firstName: String?, lastName: String?, titleName: String?, age: String?, city: String?) {

        this.avatar = avatar
        this.titleName = titleName
        this.firstName = firstName
        this.lastName = lastName
        this.gender = gender
        this.email = email
        this.phone = phone
        this.age = age
        this.city = city
        this.country = country
    }

    constructor()
}