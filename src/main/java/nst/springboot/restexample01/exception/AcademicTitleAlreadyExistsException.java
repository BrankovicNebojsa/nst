package nst.springboot.restexample01.exception;

public class AcademicTitleAlreadyExistsException extends RuntimeException {

    public AcademicTitleAlreadyExistsException(String message) {
        super(message);
    }

}
