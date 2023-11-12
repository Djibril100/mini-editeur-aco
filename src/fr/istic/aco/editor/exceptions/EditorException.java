package fr.istic.aco.editor.exceptions;

import fr.istic.aco.editor.enums.ErrorType;

public class EditorException extends Exception {
	
	private final ErrorType errorType;

    public EditorException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

}
