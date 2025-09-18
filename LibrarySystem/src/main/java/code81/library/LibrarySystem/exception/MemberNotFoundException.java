package code81.library.LibrarySystem.exception;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(String membershipNumber) {
        super(membershipNumber);
    }
}
