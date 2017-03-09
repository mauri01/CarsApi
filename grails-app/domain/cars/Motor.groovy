package cars

class Motor {

    String cilindrada
    String cilindros
    String valvulas
    String potencia

    static hasMany = [auto : Auto]

    static constraints = {
    }
}
