package ua.dp.friends

class User {

    var gender: String? = null
    var email: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var titleName: String? = null
    //var city: String? = null
    var phone: String? = null

    constructor(email: String?, gender: String?, phone: String?, firstName: String?, lastName: String?, titleName: String?) {

        this.titleName = titleName
        this.firstName = firstName
        this.lastName = lastName
        this.gender = gender
        this.email = email
        this.phone = phone
    }

    constructor()
}