package nst.springboot.restexample01.exception;

public class EducationalTitleAlreadyExistsException extends RuntimeException {
    public EducationalTitleAlreadyExistsException(String message) {
        super(message);
    }

}
