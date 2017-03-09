package cars.exceptions
/**
 * Created by mauri on 08/03/2017.
 */

class NotFoundException extends GenericException {

    //def status = 404
    NotFoundException(message) {

        super(message)
    }

}