package nst.springboot.restexample01.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String message) {
        super(message);
    }

}
