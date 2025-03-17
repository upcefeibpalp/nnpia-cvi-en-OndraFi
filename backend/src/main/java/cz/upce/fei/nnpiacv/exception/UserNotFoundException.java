package cz.upce.fei.nnpiacv.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User with ID " + id + " not found.");
    }
}