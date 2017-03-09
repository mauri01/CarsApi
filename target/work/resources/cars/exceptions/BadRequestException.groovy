package cars.exceptions
/**
 * Created by mauri on 08/03/2017.
 */

class BadRequestException extends GenericException {

    //def status = 404

    BadRequestException(message) {
        super(message)
    }
}