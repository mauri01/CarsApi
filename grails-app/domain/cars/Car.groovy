package cars

class Auto {
    String marca
    String modelo
    String tipo
    String anio
    String puertas
    Motor motor

//    static belongsTo = [motor : Motor]

    static constraints = {
        marca (nullable: false)
        modelo (nullable: false)
        tipo (nullable: false)
        anio (nullable: false)
        puertas (nullable: false)
    }
}
