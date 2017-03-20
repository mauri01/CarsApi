package cars

import grails.converters.JSON

class AutoController extends ErrorController {

    CarsService carsService
    CarsValidatorService carsValidatorService

    def post() {
        Map requestParameters = MapUtils.requestBodyToMap(request)
        log.info("se ingresaron parametros para crear auto")
        carsValidatorService.validateCar(requestParameters)
        carsValidatorService.validateMotor(requestParameters)
        def result = carsService.createCar(requestParameters)
        Map response = [
                response: carsService.normalizedResponse(result),
                status  : 201
        ]
        render(response as JSON)
    }

    def get() {
        Map requestParameters = getParams()
        log.info("id ingresada para buscar:${requestParameters.id}")
        def result = carsService.getCar(requestParameters)
        Map response = [
               response: carsService.normalizedResponse(result),
                status  : 200
        ]

       render(response as JSON)
    }

    def delete() {
        Map requestParameters = getParams()
        log.info("parametro ingresado para eliminar:${requestParameters.id}")
        carsService.deleteCar(requestParameters)
        Map response = [
                status: 200
        ]
        render(response as JSON)
    }

    def update(){
        Map requestParameters = getParams()
        Map requestBody = MapUtils.requestBodyToMap(request)
        log.info("Se ingereso Auto con id=${requestParameters.id} para actualizar")
        carsService.getIdCar(requestParameters.id)
        log.info("Ingresando a editar con los siguientes parametros=${requestBody} ")
        carsService.actualizarAuto(requestParameters.id,requestBody)
        def result = carsService.getCar(requestParameters)
        Map response = [
                response: carsService.normalizedResponse(result),
                status: 200
        ]
        render(response as JSON)
    }
}
