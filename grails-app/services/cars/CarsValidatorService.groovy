package cars

import cars.exceptions.BadRequestException
import grails.transaction.Transactional

@Transactional
class CarsValidatorService {

    void validateCar(Map requestParameters) {
        log.info("Validando parametros de Auto")
        if (!(requestParameters &&
                requestParameters.marca &&
                requestParameters.modelo &&
                requestParameters.puertas &&
                requestParameters.puertas &&
                requestParameters.anio)) {
            throw new BadRequestException("Faltan parametros de Car.")
        }
    }

    void validateMotor(Map requestParameters) {
        log.info("Validando parametros de Motor")
        if (!(requestParameters &&
                requestParameters.cilindrada &&
                requestParameters.cilindros &&
                requestParameters.valvulas &&
                requestParameters.potencia)) {
            throw new BadRequestException("Faltan parametros de Motor.")
        }
    }
}
