package cars

import cars.exceptions.BadRequestException
import cars.exceptions.GenericException
import cars.exceptions.NotFoundException
import grails.transaction.Transactional

//import javax.xml.bind.ValidationException

@Transactional
class CarsService {

    def createCar(Map param) {
        log.info("Parametors a crear ${param}")
        Motor nuevoMotor = new Motor(
                cilindros: param.cilindros,
                cilindrada: param.cilindrada,
                valvulas: param.valvulas,
                potencia: param.potencia
        ).addToAuto(new Auto(
                modelo: param.modelo,
                marca: param.marca,
                tipo: param.tipo,
                puertas: param.puertas,
                anio: param.anio,
                motor_id: param.motor_id))
        saveCar(nuevoMotor)
        log.info("Nuevo Auto con id: ${nuevoMotor.auto.id} creado")
        return nuevoMotor.auto
    }

    void saveCar(Motor newcar) {
        if (!newcar.save(flush: true)) {
            throw new GenericException("Problemas al guardar Auto.")
        }
        log.info("Se guardo el auto de forma correcta")
    }

    Map normalizedResponse(def findCar) {
        log.info("Normalizando response para Car: ${findCar.id}")

        Map responseMap = [
                Id     : findCar.id,
                modelo : findCar.modelo,
                tipo   : findCar.tipo,
                anio   : findCar.anio,
                puertas: findCar.puertas,
                Motor  :
                        [
                                cilindrada        : findCar.motor.cilindrada,
                                cilindros         : findCar.motor.cilindros,
                                valvulas_cilindros: findCar.motor.valvulas,
                                potencia          : findCar.motor.potencia
                        ]
        ]
        return responseMap
    }

    def getCar(Map param) {
        Auto findCar
        if (param.id) {
            Long id = Long.parseLong(param.id)
            findCar = Auto.findById(id)
            if (!(findCar)) {
                log.info("El auto con id ${id} no existe.")
                throw new NotFoundException("El Auto con id: ${id} no existe")
            }
        } else {
            throw new BadRequestException("El Id no puede ser nulo")
        }
        log.info("Existe el Auto Buscado con id:${findCar.id}")
        return findCar
    }

    void deleteCar(Map param) {
        log.info("Deleting card with id ${param.id}")

        def deleteAuto = getIdCar(param.id)
        deleteAuto.delete(flush: true)

        log.info("Se elimino correctamente el auto con id ${param.id}")
    }

    def getIdCar(id) {
        if (!(Auto.findById(id))) {
            log.info("Car with id ${id} not found")
            throw new NotFoundException("No Existe el Auto en la BD")
        }
        log.info("Existe el Auto ingresado")
        return Auto.findById(id)
    }

    public static void main(String[] args) {

        def map = [
                key1: 'value1',
                key2: 'value2'
        ]
        def array = [
                "value1",
                "value2",
                "value3",
                "value4",
                "value5",
        ]

        println("Tipo Mapa: ${map.getClass()}")
        println("Tipo Array: ${array.getClass()}")
    }
}
