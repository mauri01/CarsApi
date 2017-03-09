package cars

import cars.exceptions.BadRequestException
import cars.exceptions.GenericException
import cars.exceptions.NotFoundException
import grails.converters.JSON

class ErrorController {

    def index() {}

        def show() {
            Map response = [
                    status  : request.'javax.servlet.error.status_code'
            ]
            render(response as JSON)
        }

    def NotFoundException(NotFoundException e) {

        String className = this.getClass().simpleName

        response.status = 404
        Map returnMap = [
                response: [
                        message: e.message
                ],
                status  : 404
        ]

        log.error("${className} [exception:NotFoundException]: ${returnMap}")
        render(returnMap as JSON)
    }

    def BadRequestException(BadRequestException e) {

        String className = this.getClass().simpleName
        response.status = 400
        Map returnMap = [
                response: [
                        message: e.message
                ],
                status  : 400
        ]

        log.error("${className} [exception:BadRequestException]: ${returnMap}")
        render(returnMap as JSON)
    }

    def GenericException(GenericException e) {

        String className = this.getClass().simpleName
        response.status = 500
        Map returnMap = [
                response: [
                        message: e.message
                ],
                status  : 500
        ]

        log.error("${className} [exception:GenericException]: ${returnMap}")
        render(returnMap as JSON)
    }

    def	exception(Exception e) {

        log.error("Exception type: ${e}")
        throw new GenericException("Internal server error", "E500", [])
    }

}

